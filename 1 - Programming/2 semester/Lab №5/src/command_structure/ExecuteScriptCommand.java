package command_structure;

import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'execute_script'. Executes scripts from a file. Actually only checks argument and prints messages.
 */
public class ExecuteScriptCommand implements CommonCommand {
    private final String name = "execute_script file_name";
    private Messenger messenger;
    private PrintKeeper printKeeper;


    public ExecuteScriptCommand(Messenger messenger, PrintKeeper printKeeper) {
        this.messenger = messenger;
        this.printKeeper = printKeeper;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        if (argument.isEmpty()) {
            printKeeper.println(messenger.argumentErrorMessage(name, true));
            return false;
        }
        return true;
    }
}