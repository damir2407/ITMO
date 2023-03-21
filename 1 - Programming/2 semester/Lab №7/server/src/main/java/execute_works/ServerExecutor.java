package execute_works;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import request_structure.Request;
import request_structure.RequestInterface;
import server_works.Server;
import server_works.ServerSender;
import utility.*;
import utility.Error;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;

public class ServerExecutor extends Thread {
    private DatagramPacket datagramPacket;
    private ServerSender serverSender;
    private Server server;
    private static final Logger logger = LogManager.getLogger(ServerExecutor.class);


    public ServerExecutor(ServerSender serverSender, Server server) {
        this.serverSender = serverSender;
        this.server = server;
    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            giveAnswer(datagramPacket);
        }
    }


    public void setDatagramPacket(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }


    public void giveAnswer(DatagramPacket datagramPacket) {
        try {
            if (datagramPacket == null) return;
            this.datagramPacket = datagramPacket;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            RequestInterface request = (Request) objectInputStream.readObject();


            if (!request.getCommand().equals("language")) {
                Result<Object> result = (server.getServerCommandManager().getAllCommands().get(request.getCommand()).execute(request.getArgs()));
                if (result instanceof Error) {
                    serverSender.abstractServerSender.send(new Answer().error(((Error) result).getErrorMessage()), datagramPacket.getAddress(), datagramPacket.getPort());
                }
                if (result instanceof Success) {
                    serverSender.abstractServerSender.send(new Answer().ok(((Success<?>) result).getObject()), datagramPacket.getAddress(), datagramPacket.getPort());
                }
            }
            this.datagramPacket = null;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Произошла ошибка", e);
        }
    }


}