package commands;

import collection_works.CollectionManager;
import data.SpaceMarine;
import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "update id {element}";
    private Messenger messenger;

    public UpdateCommand(CollectionManager collectionManager, Messenger messenger) {
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
        Integer id = (Integer) args[0];
        SpaceMarine spaceMarine = (SpaceMarine) args[1];
        Integer key;
        key = collectionManager.getKeyById(id);
        if (key == null) {
            return new Error(messenger.itemNotFoundMessage());
        }

        collectionManager.removeById(id);

        collectionManager.insertToCollection(key, spaceMarine);
        return new Success<String>(messenger.successfullyUpdatedMessage());
    }
}

