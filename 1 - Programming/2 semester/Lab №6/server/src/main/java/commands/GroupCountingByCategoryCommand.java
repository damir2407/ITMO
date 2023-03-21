package commands;

import collection_works.CollectionManager;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;

/**
 * Command 'group_counting_by_category'. Group the elements of the collection
 * by the value of the category field, display the number of elements in each group
 */
public class GroupCountingByCategoryCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "group_counting_by_category";
    private Messenger messenger;

    public GroupCountingByCategoryCommand(CollectionManager collectionManager, Messenger messenger) {
        this.messenger = messenger;
        this.collectionManager = collectionManager;
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
        return new Success<String>(messenger.getCountingByCategory(collectionManager.getCountingCategory()));
    }
}

