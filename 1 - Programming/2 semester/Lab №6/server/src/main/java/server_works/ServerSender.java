package server_works;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.Answer;
import utility.AnswerInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSender implements ServerSendInterface {
    private final static int HIGH_CONST = 6000;
    private DatagramSocket datagramSocket;
    private static final Logger logger = LogManager.getLogger(ServerSender.class);

    public ServerSender(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    @Override
    public void send(AnswerInterface answer, InetAddress address, int port) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(answer);
            objectOutputStream.flush();

            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();

            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address, port);

            if (bytes.length > HIGH_CONST) {
                logger.info("Размер превышает допустимый, начинается отправка частями");
                sendInParts(answer, address, port);
            }

            if (!answer.isOK()) {
                logger.error("Отправка ответа клиенту " + datagramPacket.getAddress() + ":" + datagramPacket.getPort()
                        + "\n[\n" + answer.getErrorMessage() + "\n]");
            } else
                logger.info("Отправка ответа клиенту " + datagramPacket.getAddress() + ":" + datagramPacket.getPort()
                        + "\n[\n" + answer.getObject() + "\n]");

            datagramSocket.send(datagramPacket);

            objectOutputStream.close();
            byteArrayOutputStream.close();

        } catch (IOException e) {
            logger.error("Ошибка!",e);
        }
    }

    public void sendInParts(AnswerInterface answer, InetAddress inetAddress, int port) {
        int numberOfResponses = answer.toString().getBytes().length / HIGH_CONST;
        AnswerInterface answerToSend;
        for (int i = 1; i <= numberOfResponses; i++) {
            if (i == 1) {
                answerToSend = new Answer(String.valueOf(answer.getObject()).substring(1, HIGH_CONST));
            } else
                answerToSend = new Answer(String.valueOf(answer.getObject()).substring(i * HIGH_CONST, (i + 1) * HIGH_CONST));
            send(answerToSend, inetAddress, port);
        }
    }


}
