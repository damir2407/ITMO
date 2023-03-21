package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'remove_lower_key'. Removes elements lower than user entered.
 */
public class RemoveLowerKey implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "remove_lower_key null";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public RemoveLowerKey(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
        if (argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, true));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        Integer key = Integer.parseInt(argument);
        collectionManager.sortCollection();
        if (collectionManager.removeByKeyIfLower(key) > 0) {
            printMachine.println(messenger.successfullyDeleteMessage());
        } else
            printMachine.println(messenger.notSuccessfullyDeleteByKeyMessage());
        return true;
    }
}
