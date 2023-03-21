package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'average_of_height'.
 * print the average value of the height field for all elements of the collection
 */
public class AverageOfHeightCommand implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "average_of_height";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public AverageOfHeightCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
        if (!argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        printMachine.println(messenger.averageOfHeightMessage(collectionManager.getAverageOfHeight()));
        return true;
    }


}

