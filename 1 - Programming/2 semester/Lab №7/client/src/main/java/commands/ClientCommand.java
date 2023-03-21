package commands;

import utility.Result;

public interface ClientCommand {

    Result<Object> execute(String args);
}
