package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'print_field_descending_heart_count'. print the values of the heartCount field of all elements in descending order
 */
public class PrintFieldDescendingHeartCountCommand implements CommonCommand {

    private CollectionKeeper collectionManager;
    private final String name = "print_field_descending_heart_count";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public PrintFieldDescendingHeartCountCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.printMachine = printMachine;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument) {
        collectionManager.sortCollection();
        if (!argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        printMachine.println(collectionManager.getSortedHeartCounts());
        return true;

    }
}

