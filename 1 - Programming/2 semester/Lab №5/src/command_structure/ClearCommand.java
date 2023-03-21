package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "clear";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public ClearCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
        this.collectionManager = collectionManager;
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
        collectionManager.clearCollection();
        printMachine.println(messenger.successfullyClearedMessage());
        return true;
    }
}

