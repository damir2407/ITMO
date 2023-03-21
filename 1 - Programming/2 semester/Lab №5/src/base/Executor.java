package base;

/**
 * interface for CommandExecutor
 */

public interface Executor {


    int pickCommand(String[] userCommand);

    int scriptMode(String argument);

}
