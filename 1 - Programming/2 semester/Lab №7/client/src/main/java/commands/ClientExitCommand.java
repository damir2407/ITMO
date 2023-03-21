package commands;

import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'exit'. Checks for wrong arguments then do nothing.
 */
public class ClientExitCommand implements ClientCommand {
    private final String name = "exit";
    private Messenger messenger;


    public ClientExitCommand(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public Result<Object> execute(String args) {
        if (!args.isEmpty()) {
            return new Error(messenger.argumentErrorMessage(name, false));
        }
        return new Success<String>(messenger.successfullyExitMessage());
    }
}
