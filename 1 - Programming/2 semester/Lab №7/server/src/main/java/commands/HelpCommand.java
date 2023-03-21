package commands;


import messenger.Messenger;
import utility.Result;
import utility.Success;

public class HelpCommand implements ServerCommand {

    private final String name = "help";
    private Messenger messenger;

    public HelpCommand(Messenger messenger) {
        this.messenger = messenger;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */
    @Override
    public Result<Object> execute(Object... args) {
        return new Success<String>(messenger.getCommandsDescription());
    }
}

