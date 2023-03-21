package command_works;


import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * class for read commands
 */
public class ClientReader implements ClientReadInterface {
    private Scanner userScanner;

    public ClientReader(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @param clientExecutor executes the read command
     */
    @Override
    public void interactiveMode(ClientExecuteInterface clientExecutor) {
        try {
            String[] userCommand = {"", ""};
            while (!userCommand[0].equals("exit") || !userCommand[1].isEmpty()) {
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                clientExecutor.pickCommand(userCommand);
            }
        } catch (NoSuchElementException e) {

        }
    }

}
