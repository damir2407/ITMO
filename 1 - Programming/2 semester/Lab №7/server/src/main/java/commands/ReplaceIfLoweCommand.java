package commands;

import collection_works.CollectionManager;
import data.SpaceMarine;
import messenger.Messenger;
import server_works.DataBase;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'replace_if_lowe'. Replace element if lower
 */
public class ReplaceIfLoweCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "replace_if_lowe null {element}";
    private Messenger messenger;
    private DataBase dataBase;

    public ReplaceIfLoweCommand(CollectionManager collectionManager, Messenger messenger, DataBase dataBase) {
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
            collectionManager.sortCollection();


            if (collectionManager.size() == 0) {
                return new Error(messenger.collectionIsEmptyMessage());
            }
            Integer key = (Integer) args[0];
            SpaceMarine spaceMarine = (SpaceMarine) args[1];
            String userLogin = (String) args[2];

            if (!collectionManager.getByKey(key)) {
                return new Error(messenger.itemNotFoundMessage());
            }
            if (!dataBase.replaceIfLowe(key, spaceMarine, userLogin)) {
                return new Error(messenger.notEnoughRights());
            }

            collectionManager.updateCollection();
            return new Success<String>(messenger.successfullyReplaceMessage());

        } catch (NumberFormatException e) {
            return new Error(messenger.numberFormatArgumentMessage());
        }
    }
}
