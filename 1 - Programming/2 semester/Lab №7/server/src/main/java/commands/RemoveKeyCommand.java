package commands;


import collection_works.CollectionManager;
import messenger.Messenger;
import server_works.DataBase;
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
    private DataBase dataBase;

    public RemoveKeyCommand(CollectionManager collectionManager, Messenger messenger, DataBase dataBase) {
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
        try {
            if (collectionManager.size() == 0) {
                return new Error(messenger.collectionIsEmptyMessage());
            }
            Integer key = Integer.parseInt(String.valueOf(args[0]));
            String userLogin = (String) args[1];

            if (collectionManager.getByKey(key) == false) {
                return new Error(messenger.itemNotFoundMessage());
            }


            if (collectionManager.checkBeforeDelete(key, userLogin) instanceof Success)
                dataBase.removeElement(key);
            else return new Error(messenger.notEnoughRights());

            collectionManager.updateCollection();

            return new Success<String>(messenger.successfullyDeleteMessage());
        } catch (NumberFormatException e) {
            return new Error(messenger.numberFormatArgumentMessage());
        }
    }
}
