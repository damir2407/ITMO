package commands;

import collection_works.CollectionManager;
import data.SpaceMarine;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "remove_greater {element}";
    private Messenger messenger;

    public RemoveGreaterCommand(CollectionManager collectionManager, Messenger messenger) {
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
        SpaceMarine spaceMarine = (SpaceMarine) args[0];
        if (collectionManager.size() == 0) {
            return new Error(messenger.collectionIsEmptyMessage());
        }
        if (collectionManager.removeGreater(spaceMarine) > 0) {
            return new Success<String>(messenger.successfullyDeleteMessage());
        } else
            return new Error(messenger.notSuccessfullyDeleteMessage());
    }
}
