package commands;

import collection_works.CollectionManager;
import data.SpaceMarine;
import messenger.Messenger;
import server_works.DataBase;
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
    private DataBase dataBase;

    public UpdateCommand(CollectionManager collectionManager, Messenger messenger, DataBase dataBase) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.dataBase = dataBase;

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
        String userLogin = (String) args[2];
        Integer key;
        key = collectionManager.getKeyById(id);
        if (key == null) {
            return new Error(messenger.itemNotFoundMessage());
        }

        if (!dataBase.update(key, spaceMarine, userLogin)) {
            return new Error(messenger.notEnoughRights());
        }

        collectionManager.updateCollection();

        return new Success<String>(messenger.successfullyUpdatedMessage());
    }
}

