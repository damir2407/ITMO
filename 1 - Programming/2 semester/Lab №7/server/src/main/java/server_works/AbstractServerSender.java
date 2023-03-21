package server_works;

import utility.AnswerInterface;

import java.net.InetAddress;

public interface AbstractServerSender  {
    void send(AnswerInterface answer, InetAddress inetAddress, int port);
}
