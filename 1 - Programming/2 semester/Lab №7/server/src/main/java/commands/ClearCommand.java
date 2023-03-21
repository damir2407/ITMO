package commands;

import collection_works.CollectionManager;
import messenger.Messenger;
import server_works.DataBase;
import utility.Result;
import utility.Success;


/**
 * Command 'clear'. Clears the collection.
 */
public class ClearCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "clear";
    private Messenger messenger;
    private DataBase dataBase;

    public ClearCommand(CollectionManager collectionManager, Messenger messenger, DataBase dataBase) {
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
        String userLogin = (String) args[1];

        for (Integer i : dataBase.getInformation().keySet()) {
            if (collectionManager.checkBeforeDelete(i, userLogin) instanceof Success)
                dataBase.removeElement(i);
        }
        collectionManager.updateCollection();
        return new Success<String>(messenger.successfullyClearedMessage());
    }


}

