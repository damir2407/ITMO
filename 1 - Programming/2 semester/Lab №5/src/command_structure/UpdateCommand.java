package command_structure;

import base.*;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'update'. Updates the information about selected marine.
 */
public class UpdateCommand implements AskCommand {
    private CollectionKeeper collectionManager;
    private final String name = "update id {element}";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public UpdateCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
    public boolean execute(String argument, Repeater repeater)  {
        if (argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage(name, true));
            return false;
        }
        if (collectionManager.size() == 0) {
            printMachine.println(messenger.collectionIsEmptyMessage());
            return false;
        }
        Integer id = Integer.parseInt(argument);
        Integer key;
        key = collectionManager.getKeyById(id);
        if (key == null) {
            printMachine.println(messenger.itemNotFoundMessage());
            return false;
        }

        collectionManager.removeById(id);

        collectionManager.insertToCollection(key, repeater.repeatFields(id));
        printMachine.println(messenger.successfullyUpdatedMessage());
        return true;
    }
}

