package server_works;

import commands.LanguageManager;
import execute_works.ServerExecutor;
import messenger.Messenger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request_structure.Request;
import request_structure.RequestInterface;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

public class ServerReceiver implements Runnable {
    private DatagramSocket socket;
    private ServerSender serverSender;
    private static final Logger logger = LogManager.getLogger(ServerReceiver.class);
    private Map<Integer, Messenger> clientBase = new HashMap<>();
    private LanguageManager languageManager;
    private Server server;
    private ForkJoinPool executor = new ForkJoinPool();

    public ServerReceiver(DatagramSocket socket, ServerSender serverSender, LanguageManager languageManager, Server server) {
        this.socket = socket;
        this.serverSender = serverSender;
        this.languageManager = languageManager;
        this.server = server;
    }


    AbstractServerReceiver abstractServerReceiver = new AbstractServerReceiver() {

        @Override
        public void run() {
            executor.execute(ServerReceiver.this);
        }
    };


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {

                ServerExecutor serverExecutor = new ServerExecutor(serverSender, server);
                serverExecutor.start();


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

                serverExecutor.setDatagramPacket(datagramPacket);


            } catch (IOException | ClassNotFoundException e) {
                logger.error("Ошибка!", e);
            }
        }
    }

}
