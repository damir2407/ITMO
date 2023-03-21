package command_works;


import commands.AskCommand;
import commands.ClientCommand;

import java.util.HashMap;

/**
 * interface for command manager
 */

public interface ClientCommandInterface {


    HashMap<String, ClientCommand> getClientCommands();


    HashMap<String, Boolean> getAvailableCommands();

    void mapOfAvailableCommands();

    void clientCommands();

    void mapOfAskCommands();

     void pushCommands();

    HashMap<String, AskCommand> getAskCommands();
    
}
