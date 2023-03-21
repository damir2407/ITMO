package client_works;

import messenger.Messenger;
import print_works.PrintInterface;
import utility.Answer;
import utility.AnswerInterface;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientReceiver extends Thread {
    private DatagramChannel datagramChannel;
    private SocketAddress serverAddress;
    private ByteBuffer byteBuffer;
    private PrintInterface printMachine;
    private Messenger messenger;
    private ClientData clientData;


    public ClientReceiver(DatagramChannel datagramChannel, SocketAddress serverAddress, PrintInterface printMachine
            , Messenger messenger, ClientData clientData) {
        this.datagramChannel = datagramChannel;
        this.serverAddress = serverAddress;
        this.byteBuffer = ByteBuffer.allocate(16384);
        this.printMachine = printMachine;
        this.messenger = messenger;
        this.clientData = clientData;
    }


    @Override
    public void run() {
        try {
            datagramChannel.connect(serverAddress);
        } catch (IOException e) {
            printMachine.printErr("Ошибка!");
        }
        while (!isInterrupted()) {
            receive();
        }
    }


    public void receive() {
        try {
            byteBuffer.clear();

            datagramChannel.receive(byteBuffer);
            byteBuffer.flip();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            AnswerInterface answer = (Answer) objectInputStream.readObject();

            if (!answer.isOK()) {
                printMachine.println("Получен ответ от сервера (Ошибка) \n" + answer.getErrorMessage());
            } else printMachine.println("Получен ответ от сервера: \n" + answer.getObject());

            if (!answer.isOK()) {
                if (answer.getErrorMessage().equals(messenger.notSuccessfullyRegUser()) ||
                        answer.getErrorMessage().equals(messenger.notSuccessfullyAuthMessage())
                        || answer.getErrorMessage().equals(messenger.incorrectPasswordMessage())) {
                    clientData.setLogin(null);
                }
            }


            byteBuffer.clear();
            datagramChannel.disconnect();
        } catch (PortUnreachableException e) {
            printMachine.println("Сервер недоступен!");
        } catch (IOException | ClassNotFoundException e) {
            printMachine.println("Ошибка!");
        }
    }
}
