package command_structure;

import base.*;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'insert'. Adds a new element to collection.
 */
public class InsertCommand implements AskCommand {
    private CollectionKeeper collectionManager;
    private final String name = "insert null {element}";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public InsertCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
        Integer key;
        if (argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, true));
            return false;
        }
        key = Integer.parseInt(argument);
        collectionManager.insertToCollection(key, repeater.repeatFields(null));
        printMachine.println(messenger.successfullyAddMessage());
        return true;

    }
}