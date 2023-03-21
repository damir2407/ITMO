package commands;

import collection_works.CollectionManager;
import utility.Result;

/**
 * Command 'save'. Saves the collection to a file.
 */
public class SaveCommand implements ServerCommand {
    private CollectionManager collectionManager;
    private final String name = "save";

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
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
            collectionManager.saveCollection();
        } catch (Exception e) {
        }
        return null;
    }
}
