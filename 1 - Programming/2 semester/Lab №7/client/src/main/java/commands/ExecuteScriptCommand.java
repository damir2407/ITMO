package commands;

import messenger.Messenger;
import utility.Error;
import utility.Result;
import utility.Success;


/**
 * Command 'execute_script'. Executes scripts from a file. Actually only checks argument and prints messages.
 */
public class ExecuteScriptCommand implements ClientCommand {
    private final String name = "execute_script file_name";
    private Messenger messenger;

    public ExecuteScriptCommand(Messenger messenger) {
        this.messenger = messenger;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public Result<Object> execute(String args) {
        if (args.isEmpty()) {
            return new Error(messenger.argumentErrorMessage(name, true));
        }
        return new Success<String>(messenger.successfullyExecuteCommandMessage("execute_command"));
    }
}