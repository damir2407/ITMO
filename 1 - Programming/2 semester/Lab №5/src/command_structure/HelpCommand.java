package command_structure;

import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'help' for display commands description
 */
public class HelpCommand implements CommonCommand {
    private final String name = "help";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public HelpCommand(Messenger messenger, PrintKeeper printMachine) {
        this.messenger = messenger;
        this.printMachine = printMachine;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        if (!argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        printMachine.println(messenger.getCommandsDescription());
        return true;
    }
}
