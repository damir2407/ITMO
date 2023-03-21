package command_works;


import ask_works.PollInterface;
import client_works.ClientData;
import commands.*;

import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintInterface;


import java.util.HashMap;


/**
 * Class for working with our commands.
 */

public class ClientCommandManager implements ClientCommandInterface {
    private HashMap<String, ClientCommand> clientCommands = new HashMap<>();
    private HashMap<String, Boolean> availableCommands = new HashMap<>();
    private HashMap<String, AskCommand> askCommands = new HashMap<>();
    private ClientCommand exitCommand;
    private ClientCommand executeScriptCommand;
    private AskCommand insertCommand;
    private AskCommand updateCommand;
    private AskCommand removeGreaterCommand;
    private AskCommand replaceIfLoweCommand;
    private AskCommand registrationCommand;
    private AskCommand authorizationCommand;
    private Messenger messenger;
    private PrintInterface printInterface;
    private Repeater repeater;
    private PollInterface poll;
    private ClientData clientData;

    public ClientCommandManager(Messenger messenger, PrintInterface printInterface, Repeater repeater
            , PollInterface poll, ClientData clientData) {
        this.messenger = messenger;
        this.printInterface = printInterface;
        this.repeater = repeater;
        this.poll = poll;
        this.clientData = clientData;

    }

    @Override
    public void pushCommands() {
        this.exitCommand = new ClientExitCommand(messenger);
        this.executeScriptCommand = new ExecuteScriptCommand(messenger);
        this.insertCommand = new InsertCommand(messenger, printInterface, repeater);
        this.updateCommand = new UpdateCommand(messenger, printInterface, repeater);
        this.registrationCommand = new RegistrationCommand(messenger, printInterface, poll, clientData);
        this.authorizationCommand = new AuthorizationCommand(messenger, printInterface, poll, clientData);
        this.removeGreaterCommand = new RemoveGreaterCommand(messenger, printInterface, repeater);
        this.replaceIfLoweCommand = new ReplaceIfLoweCommand(messenger, printInterface, repeater);
    }


    @Override
    public void clientCommands() {
        clientCommands.put("exit", this.exitCommand);
        clientCommands.put("execute_script", this.executeScriptCommand);
    }

    @Override
    public void mapOfAvailableCommands() {
        availableCommands.put("help", true);
        availableCommands.put("info", true);
        availableCommands.put("show", true);
        availableCommands.put("remove_key", false);
        availableCommands.put("clear", true);
        availableCommands.put("remove_lower_key", false);
        availableCommands.put("average_of_height", true);
        availableCommands.put("group_counting_by_category", true);
        availableCommands.put("print_field_descending_heart_count", true);
    }

    @Override
    public void mapOfAskCommands() {
        askCommands.put("auth", this.authorizationCommand);
        askCommands.put("reg", this.registrationCommand);
        askCommands.put("insert", this.insertCommand);
        askCommands.put("update", this.updateCommand);
        askCommands.put("remove_greater", this.removeGreaterCommand);
        askCommands.put("replace_if_lowe", this.replaceIfLoweCommand);
    }


    @Override
    public HashMap<String, ClientCommand> getClientCommands() {
        return clientCommands;
    }

    @Override
    public HashMap<String, Boolean> getAvailableCommands() {
        return availableCommands;
    }


    @Override
    public HashMap<String, AskCommand> getAskCommands() {
        return askCommands;
    }
}
