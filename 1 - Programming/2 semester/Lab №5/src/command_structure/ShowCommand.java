package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;

/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class ShowCommand implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "show";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public ShowCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
        this.collectionManager = collectionManager;
        this.printMachine = printMachine;
        this.messenger = messenger;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        collectionManager.sortCollection();
        if (!argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        printMachine.println(messenger.getMarineFieldsInformation(collectionManager.getMarinesCollection()));
        return true;
    }
}


