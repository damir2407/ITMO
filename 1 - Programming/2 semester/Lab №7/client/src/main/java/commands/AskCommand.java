package commands;

import request_structure.RequestInterface;

public interface AskCommand {

    RequestInterface prepare(String argument, String userLogin);
}
