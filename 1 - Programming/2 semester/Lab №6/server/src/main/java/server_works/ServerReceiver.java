package server_works;

import commands.LanguageManager;
import execute_works.ServerExecuteInterface;
import messenger.Messenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request_structure.Request;
import request_structure.RequestInterface;
import utility.AnswerInterface;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class ServerReceiver extends Thread {
    private DatagramSocket socket;
    private ServerSendInterface serverSender;
    private ServerExecuteInterface serverExecutor;
    private static final Logger logger = LogManager.getLogger(ServerReceiver.class);
    private Map<Integer, Messenger> clientBase = new HashMap<>();
    private LanguageManager languageManager;
    private Server server;

    public ServerReceiver(DatagramSocket socket, ServerSendInterface serverSender, ServerExecuteInterface serverExecutor, LanguageManager languageManager, Server server) {
        this.socket = socket;
        this.serverSender = serverSender;
        this.serverExecutor = serverExecutor;
        this.languageManager = languageManager;
        this.server = server;

    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                byte[] bytes = new byte[16384];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
                socket.receive(datagramPacket);

                logger.info("Получен запрос от " + datagramPacket.getAddress() + ":" +
                        datagramPacket.getPort());

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                RequestInterface request = (Request) objectInputStream.readObject();

                if (request.getCommand().equals("language")) {
                    clientBase.put(datagramPacket.getPort(), languageManager.getMapOfLanguages().get(request.getArgs()[0]));
                    server.getCollectionManager().setMessenger(languageManager.getMapOfLanguages().get(request.getArgs()[0]));
                    server.getCollectionManager().getFileFieldsChecker().getFieldsValidation().setMessenger(languageManager.getMapOfLanguages().get(request.getArgs()[0]));
                    server.getCollectionManager().convertToCollection(datagramPacket.getAddress(), datagramPacket.getPort());
                } else {
                    server.getServerCommandManager().setMessenger(clientBase.get(datagramPacket.getPort()));
                    server.getServerCommandManager().instantiateCommands();
                    server.getServerCommandManager().putCommands();
                }

                AnswerInterface answer = serverExecutor.giveAnswer(datagramPacket);
                if (answer != null) serverSender.send(answer, datagramPacket.getAddress(), datagramPacket.getPort());
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Ошибка!", e);
            }
        }
    }

    public Map<Integer, Messenger> getClientBase() {
        return clientBase;
    }
}
