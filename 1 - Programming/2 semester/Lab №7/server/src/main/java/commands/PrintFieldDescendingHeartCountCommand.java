package commands;

import collection_works.CollectionManager;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'print_field_descending_heart_count'. print the values of the heartCount field of all elements in descending order
 */
public class PrintFieldDescendingHeartCountCommand implements ServerCommand {

    private CollectionManager collectionManager;
    private final String name = "print_field_descending_heart_count";
    private Messenger messenger;

    public PrintFieldDescendingHeartCountCommand(CollectionManager collectionManager, Messenger messenger) {
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
        return new Success<String>(collectionManager.getSortedHeartCounts());

    }
}

