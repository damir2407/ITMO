package commands;

import collection_works.CollectionManager;
import data.SpaceMarine;
import messenger.Messenger;
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

    public ReplaceIfLoweCommand(CollectionManager collectionManager, Messenger messenger) {
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
            collectionManager.sortCollection();


            if (collectionManager.size() == 0) {
                return new Error(messenger.collectionIsEmptyMessage());
            }
            Integer key = (Integer) args[0];
            SpaceMarine spaceMarine = (SpaceMarine) args[1];
            if (!collectionManager.getByKey(key)) {
                return new Error(messenger.itemNotFoundMessage());
            }
            if (collectionManager.replaceIfLowe(key, spaceMarine)) {
                return new Success<String>(messenger.successfullyReplaceMessage());
            } else return new Success<String>(messenger.notSuccessfullyReplaceMessage());
        } catch (NumberFormatException e) {
            return new Error(messenger.numberFormatArgumentMessage());
        }
    }
}
