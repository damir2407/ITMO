package commands;

import collection_works.CollectionManager;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;

/**
 * Command 'show'. Shows information about all elements of the collection.
 */
public class ShowCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "show";
    private Messenger messenger;

    public ShowCommand(CollectionManager collectionManager, Messenger messenger) {
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
        collectionManager.sortCollection();

        if (collectionManager.size() == 0) {
            return new Error(messenger.collectionIsEmptyMessage());
        }
        return new Success<String>(messenger.getMarineFieldsInformation(collectionManager.getMarinesCollection()));
    }
}


