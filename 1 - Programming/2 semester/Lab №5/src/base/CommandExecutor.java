package base;

import messenger.Messenger;
import messenger.PrintKeeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * class for execute commands
 */
public class CommandExecutor implements Executor {

    private CommandKeeper commandManager;
    private PollKeeper poll;
    private Messenger messenger;
    private PrintKeeper printMachine;


    public CommandExecutor(CommandKeeper commandManager, PollKeeper poll, Messenger messenger, PrintKeeper printMachine) {
        this.commandManager = commandManager;
        this.poll = poll;
        this.messenger = messenger;
        this.printMachine = printMachine;
    }


    /**
     * method for choose command
     *
     * @param userCommand to read information
     * @return command execute status
     */
    @Override
    public int pickCommand(String[] userCommand) {
        try {
            if (userCommand[0].equals("execute_script")) {
                if (!commandManager.getAllCommands().get(userCommand[0]).convert(userCommand[1])) return 1;
                else return scriptMode(userCommand[1]);
            }

            if (!commandManager.getAllCommands().get(userCommand[0]).convert(userCommand[1])) return 0;
        } catch (NullPointerException e) {
            if (userCommand[0] != "")
                printMachine.println(messenger.commandNotFoundMessage(userCommand[0], userCommand[1]));
        } catch (NumberFormatException e) {
            printMachine.println(messenger.numberFormatArgumentMessage());
        }
        return 0;
    }

    /**
     * method for work with execute_command
     *
     * @param argument script direction
     * @return command execute status
     */
    @Override
    public int scriptMode(String argument) {
        boolean t = false;
        List<String> scriptStack = new ArrayList<>();
        String[] userCommand = {"", ""};
        int commandStatus = 0;
        scriptStack.add(argument);

        try (Scanner scriptScanner = new Scanner(new File(argument))) {

            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = poll.getUserScanner();
            poll.setUserScanner(scriptScanner);
            poll.setFileMode(true);
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                printMachine.println(String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) {
                            t = true;
                        }
                    }
                }
                if (t == true) {
                    t = false;
                    printMachine.println(messenger.recursionMessage());
                } else {
                    commandStatus = pickCommand(userCommand);
                }


            } while (commandStatus == 0 && scriptScanner.hasNextLine());
            poll.setUserScanner(tmpScanner);
            poll.setUserMode();


            if (commandStatus == 1 && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty())) {
                printMachine.println(messenger.incorrectScriptFileMessage());
            }
            return commandStatus;
        } catch (FileNotFoundException exception) {
            printMachine.printErr(messenger.scriptFileDoesntExistMessage());

        } catch (NoSuchElementException exception) {
            printMachine.printErr(messenger.noSuchElementInputMessage());
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return 0;


    }
}