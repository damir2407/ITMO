package commands;

import collection_works.CollectionManager;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'average_of_height'.
 * print the average value of the height field for all elements of the collection
 */
public class AverageOfHeightCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "average_of_height";
    private Messenger messenger;

    public AverageOfHeightCommand(CollectionManager collectionManager, Messenger messenger) {
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
        if (collectionManager.size() == 0) {
            return new Error(messenger.collectionIsEmptyMessage());
        }
        return new Success<String>(messenger.averageOfHeightMessage(collectionManager.getAverageOfHeight()));
    }


}

