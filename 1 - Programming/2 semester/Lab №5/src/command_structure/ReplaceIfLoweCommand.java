package command_structure;

import base.*;
import data.SpaceMarine;
import messenger.Messenger;
import messenger.PrintKeeper;


/**
 * Command 'replace_if_lowe'. Replace element if lower
 */
public class ReplaceIfLoweCommand implements AskCommand {
    private CollectionKeeper collectionManager;
    private final String name = "replace_if_lowe null {element}";
    private Messenger messenger;
    private PrintKeeper printMachine;

    public ReplaceIfLoweCommand(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {
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
        collectionManager.sortCollection();
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
        SpaceMarine spaceMarine = repeater.repeatFields(null);
        if (collectionManager.replaceIfLowe(key, spaceMarine)) {
            printMachine.println(messenger.successfullyReplaceMessage());
        } else printMachine.println(messenger.notSuccessfullyReplaceMessage());
        return true;
    }
}
