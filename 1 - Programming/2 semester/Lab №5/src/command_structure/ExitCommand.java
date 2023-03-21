package command_structure;

import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'exit'. Checks for wrong arguments then do nothing.
 */
public class ExitCommand implements CommonCommand {
    private final String name = "exit";
    private Messenger messenger;
    private PrintKeeper printKeeper;


    public ExitCommand(Messenger messenger, PrintKeeper printKeeper) {
        this.messenger = messenger;
        this.printKeeper = printKeeper;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument)  {
        if (!argument.isEmpty()) {
            printKeeper.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        return true;
    }
}
