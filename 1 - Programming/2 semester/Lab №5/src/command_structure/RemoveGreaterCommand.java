package command_structure;

import base.*;
import data.SpaceMarine;
import messenger.Messenger;
import messenger.PrintKeeper;

/**
 * Command 'remove_greater'. Removes elements greater than user entered.
 */
public class RemoveGreaterCommand implements AskCommand {
    private CollectionKeeper collectionManager;
    private final String name = "remove_greater {element}";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public RemoveGreaterCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
    public boolean execute(String argument, Repeater repeater) {
        if (!argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, false));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        SpaceMarine marine = repeater.repeatFields(null);
        if (collectionManager.removeGreater(marine) > 0) {
            printMachine.println(messenger.successfullyDeleteMessage());
        } else
            printMachine.println(messenger.notSuccessfullyDeleteMessage());
        return true;
    }
}
