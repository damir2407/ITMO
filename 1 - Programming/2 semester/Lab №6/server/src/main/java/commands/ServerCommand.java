package commands;

import utility.Result;

public interface ServerCommand{

    Result<Object> execute(Object... args);
}
