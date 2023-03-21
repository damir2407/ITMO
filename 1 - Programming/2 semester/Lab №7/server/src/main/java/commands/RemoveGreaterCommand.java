package commands;

import collection_works.CollectionManager;
import data.SpaceMarine;
import messenger.Messenger;
import server_works.DataBase;
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
    private DataBase dataBase;

    public RemoveGreaterCommand(CollectionManager collectionManager, Messenger messenger, DataBase dataBase) {
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
        SpaceMarine spaceMarine = (SpaceMarine) args[0];
        String userLogin = (String) args[1];
        if (collectionManager.size() == 0) {
            return new Error(messenger.collectionIsEmptyMessage());
        }
        dataBase.removeGreater(spaceMarine, userLogin);
        collectionManager.updateCollection();
        return new Success<String>(messenger.successfullyDeleteMessage());

    }
}
