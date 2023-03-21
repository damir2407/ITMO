package server_works;

import collection_works.CollectionManager;
import collection_works.TreeMapManager;
import commands.LanguageManager;
import execute_works.*;
import file_works.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.ServerFieldsValidation;
import utility.ServerValidator;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Server {
    private final int SERVER_PORT = 2407;
    private DatagramSocket socket;
    private InetAddress inetAddress;
    private CollectionManager collectionManager;
    private static final Logger logger = LogManager.getLogger(Server.class);
    private ServerCommandInterface serverCommandManager;
    private ServerExecuteInterface severExecutor;


    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }


    public void startServer() {
        try {
            this.inetAddress = InetAddress.getByName("localhost");
            this.socket = new DatagramSocket(SERVER_PORT);

            LanguageManager languageManager = new LanguageManager();
            ServerSendInterface serverSender = new ServerSender(socket);
            ServerReadInterface serverReadInterface = new ServerReader();

            ServerValidator fieldsValidation = new ServerFieldsValidation();
            LoadCheck fileFieldsChecker = new LoadFieldsChecker(fieldsValidation);
            Loader fileManager = new FileManager(System.getenv("My_Path"));

            this.collectionManager = new TreeMapManager(fileManager, serverSender, fileFieldsChecker);
            this.serverCommandManager = new ServerCommandManager(collectionManager);

            ServerExecuteInterface serverExecutor = new ServerExecutor(languageManager, serverSender, serverReadInterface, serverCommandManager, this);
            ServerReceiver serverReceiver = new ServerReceiver(socket, serverSender, serverExecutor, languageManager, this);

            serverReadInterface.setCollectionManager(collectionManager);

            serverReceiver.setDaemon(true);
            serverReceiver.start();
            serverReadInterface.read();


        } catch (UnknownHostException | SocketException e) {
            logger.error("Ошибка на сервере", e);
        }


    }

    public ServerCommandInterface getServerCommandManager() {
        return serverCommandManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public ServerExecuteInterface getSeverExecutor() {
        return severExecutor;
    }
}
