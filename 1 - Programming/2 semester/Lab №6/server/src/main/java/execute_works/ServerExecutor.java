package execute_works;

import commands.LanguageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request_structure.Request;
import request_structure.RequestInterface;
import server_works.Server;
import server_works.ServerSendInterface;
import utility.*;
import utility.Error;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class ServerExecutor implements ServerExecuteInterface {
    private ServerCommandInterface serverCommandManager;
    private LanguageManager languageManager;
    private DatagramPacket datagramPacket;
    private ServerSendInterface serverSender;
    private ServerReadInterface serverReader;
    private Server server;
    private static final Logger logger = LogManager.getLogger(ServerExecutor.class);


    public ServerExecutor(LanguageManager languageManager, ServerSendInterface serverSender, ServerReadInterface serverReader, ServerCommandInterface serverCommandManager, Server server) {
        this.languageManager = languageManager;
        this.serverSender = serverSender;
        this.serverReader = serverReader;
        this.serverCommandManager = serverCommandManager;
        this.server = server;
    }


    @Override
    public AnswerInterface giveAnswer(DatagramPacket datagramPacket) {
        try {
            this.datagramPacket = datagramPacket;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            RequestInterface request = (Request) objectInputStream.readObject();


            if (!request.getCommand().equals("language")) {
                Result<Object> result = pickCommand(request);
                if (result instanceof Error) {
                    return new Answer().error(((Error) result).getErrorMessage());
                }
                if (result instanceof Success) {
                    return new Answer().ok(((Success<?>) result).getObject());
                }
            } else return null;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Произошла ошибка", e);
        }
        return null;
    }

    @Override
    public Result<Object> pickCommand(RequestInterface request) {
        Result<Object> result = (server.getServerCommandManager().getAllCommands().get(request.getCommand()).execute(request.getArgs()));
        return result;
    }


    @Override
    public void setServerCommandManager(ServerCommandInterface serverCommandManager) {
        this.serverCommandManager = serverCommandManager;
    }
}