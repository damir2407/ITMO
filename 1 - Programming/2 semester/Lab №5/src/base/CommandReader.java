package base;


import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * class for read commands
 */
public class CommandReader implements Reader {
    private Scanner userScanner;

    public CommandReader(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @param commandExecutor executes the read command
     */
    public void interactiveMode(Executor commandExecutor) {
        try {
            String[] userCommand = {"", ""};
            while (!userCommand[0].equals("exit") || !userCommand[1].isEmpty()) {
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                commandExecutor.pickCommand(userCommand);
            }
        }catch (NoSuchElementException e){

        }
    }

}
