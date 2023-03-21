package client_works;

import print_works.PrintInterface;
import request_structure.RequestInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSender implements ClientSendInterface {
    private DatagramChannel datagramChannel;
    private SocketAddress serverAddress;
    private ByteBuffer byteBuffer;
    private PrintInterface printMachine;


    public ClientSender(DatagramChannel datagramChannel, SocketAddress serverAddress, PrintInterface printMachine) {
        this.datagramChannel = datagramChannel;
        this.serverAddress = serverAddress;
        this.byteBuffer = ByteBuffer.allocate(16384);
        this.printMachine = printMachine;
    }


    @Override
    public void send(RequestInterface request) {
        try {
            byteBuffer.clear();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(request);
            byteBuffer.put(byteArrayOutputStream.toByteArray());
            byteBuffer.flip();
            objectOutputStream.flush();
            byteArrayOutputStream.flush();

            datagramChannel.send(byteBuffer, serverAddress);
            objectOutputStream.close();
            byteArrayOutputStream.close();

        } catch (IOException e) {
            printMachine.println("Ошибка!");
        }

    }


}
