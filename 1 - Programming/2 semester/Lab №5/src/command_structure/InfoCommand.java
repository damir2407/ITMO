package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;

import java.util.Date;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "info";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public InfoCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
        String lastInitializationField;
        Date lastInitialization = collectionManager.getLastInitialization();
        if (lastInitialization == null) {
            lastInitializationField = messenger.hasNotBeenInitializationYetMessage();
        } else {
            lastInitializationField = lastInitialization.toString();
        }

        String lastSaveField;
        Date lastSave = collectionManager.getLastSave();
        if (lastSave == null) {
            lastSaveField = messenger.hasNotBeenSaveYetMessage();
        } else {
            lastSaveField = lastSave.toString();
        }

        printMachine.println(messenger.getCollectionInfo(collectionManager.getType(), collectionManager.size(), lastInitializationField, lastSaveField, collectionManager.getPath()));
        return true;

    }
}