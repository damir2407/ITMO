package server_works;

import utility.AnswerInterface;

import java.net.InetAddress;

public interface ServerSendInterface {

     void send(AnswerInterface answer, InetAddress address, int port);
}
