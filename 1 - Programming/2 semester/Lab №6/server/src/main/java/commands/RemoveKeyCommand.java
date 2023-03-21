package commands;


import collection_works.CollectionManager;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'remove_key'. Removes element by key.
 */
public class RemoveKeyCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "remove_key null";
    private Messenger messenger;

    public RemoveKeyCommand(CollectionManager collectionManager, Messenger messenger) {
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
        try {
            if (collectionManager.size() == 0) {
                return new Error(messenger.collectionIsEmptyMessage());
            }
            Integer key = Integer.parseInt(String.valueOf(args[0]));
            if (collectionManager.getByKey(key) == false) {
                return new Error(messenger.itemNotFoundMessage());
            }
            collectionManager.removeByKey(key);
            return new Success<String>(messenger.successfullyDeleteMessage());
        } catch (NumberFormatException e) {
            return new Error(messenger.numberFormatArgumentMessage());
        }
    }
}
