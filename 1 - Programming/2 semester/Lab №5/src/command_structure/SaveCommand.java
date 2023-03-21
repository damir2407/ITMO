package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "save";
    private Messenger messenger;
    private PrintKeeper printKeeper;

    public SaveCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printKeeper) {
        this.collectionManager = collectionManager;
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
        if (!argument.isEmpty()) {
            printKeeper.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        collectionManager.saveCollection();
        return true;

    }
}
