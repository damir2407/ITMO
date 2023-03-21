package execute_works;


import request_structure.RequestInterface;
import utility.AnswerInterface;
import utility.Result;

import java.net.DatagramPacket;

public interface ServerExecuteInterface {

    AnswerInterface giveAnswer(DatagramPacket datagramPacket);

    Result<Object> pickCommand(RequestInterface request);

    void setServerCommandManager(ServerCommandInterface serverCommandManager);
}
