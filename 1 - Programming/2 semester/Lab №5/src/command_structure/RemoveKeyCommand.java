package command_structure;

import base.CollectionKeeper;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'remove_key'. Removes element by key.
 */
public class RemoveKeyCommand implements CommonCommand {
    private CollectionKeeper collectionManager;
    private final String name = "remove_key null";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public RemoveKeyCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
    public boolean execute(String argument)  {
        if (argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, true));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        Integer key = Integer.parseInt(argument);
        if (collectionManager.getByKey(key) == false) {
            printMachine.println(messenger.itemNotFoundMessage());
            return false;
        }
        collectionManager.removeByKey(key);
        printMachine.println(messenger.successfullyDeleteMessage());
        return true;
    }
}
