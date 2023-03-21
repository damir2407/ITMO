package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;

/**
 * Command 'group_counting_by_category'. Group the elements of the collection
 * by the value of the category field, display the number of elements in each group
 */
public class GroupCountingByCategoryCommand implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "group_counting_by_category";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public GroupCountingByCategoryCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
        this.messenger = messenger;
        this.collectionManager = collectionManager;
        this.printMachine = printMachine;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        if (!argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        printMachine.println(messenger.getCountingByCategory(collectionManager.getCountingCategory()));
        return true;
    }
}

