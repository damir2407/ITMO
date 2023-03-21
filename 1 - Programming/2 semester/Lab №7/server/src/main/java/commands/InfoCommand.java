package commands;

import collection_works.CollectionManager;
import messenger.Messenger;
import utility.Result;
import utility.Success;

import java.util.Date;

/**
 * Command 'info'. Prints information about the collection.
 */
public class InfoCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "info";
    private Messenger messenger;

    public InfoCommand(CollectionManager collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public Result<Object> execute(Object... args) {
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

        return new Success<String>(messenger.getCollectionInfo(collectionManager.getType(), collectionManager.size(), lastInitializationField, lastSaveField));

    }
}