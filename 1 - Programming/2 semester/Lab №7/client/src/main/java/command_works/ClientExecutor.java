package command_works;

import ask_works.PollInterface;
import client_works.ClientData;
import client_works.ClientSendInterface;
import messenger.Messenger;
import print_works.PrintInterface;
import request_structure.Request;
import request_structure.RequestInterface;
import utility.Error;
import utility.Result;
import utility.Success;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ClientExecutor implements ClientExecuteInterface {

    private ClientCommandInterface clientCommandManager;
    private PollInterface poll;
    private Messenger messenger;
    private PrintInterface printMachine;
    private ClientSendInterface clientSender;
    private ClientData clientData;


    public ClientExecutor(ClientCommandInterface clientCommandManager, PollInterface poll, Messenger messenger, PrintInterface printMachine,
                          ClientSendInterface clientSender, ClientData clientData) {
        this.clientCommandManager = clientCommandManager;
        this.poll = poll;
        this.messenger = messenger;
        this.printMachine = printMachine;
        this.clientSender = clientSender;
        this.clientData = clientData;
    }


    /**
     * method for choose command
     *
     * @param userCommand to read information
     */
    @Override
    public void pickCommand(String[] userCommand) {
        try {
            //Проверка на клиентскую команду
            if (clientCommandManager.getClientCommands().containsKey(userCommand[0].trim()) && userCommand[0].trim().equals("execute_script")) {
                Result<Object> result = (clientCommandManager.getClientCommands().get(userCommand[0]).execute(userCommand[1]));
                if (result instanceof Error)
                    printMachine.println(((Error) result).getErrorMessage());
                else {
                    scriptMode(userCommand[1]);
                }
            } else if (clientCommandManager.getClientCommands().containsKey(userCommand[0].trim())) {
                Result<Object> result = (clientCommandManager.getClientCommands().get(userCommand[0]).execute(userCommand[1]));
                if (result instanceof Error) {
                    printMachine.println(((Error) result).getErrorMessage());
                }
                if (result instanceof Success) {
                    printMachine.println(((Success<?>) result).getObject());
                }

                //проверка на сервернуюкоманду
            } else if (clientCommandManager.getAvailableCommands().containsKey(userCommand[0])) {
                if (clientCommandManager.getAvailableCommands().get(userCommand[0]).equals(userCommand[1].isEmpty())) {
                    RequestInterface request = new Request(userCommand[0], userCommand[1], clientData.getLogin());
                    if (clientData.getLogin() != null) {
                        clientSender.send(request);
                    } else printMachine.println(messenger.executionBan());
                } else
                    printMachine.println(messenger.argumentErrorMessage(userCommand[0], !clientCommandManager.getAvailableCommands().get(userCommand[0])));
            } else if (clientCommandManager.getAskCommands().containsKey(userCommand[0])) {
                RequestInterface request = (clientCommandManager.getAskCommands().get(userCommand[0]).prepare(userCommand[1], clientData.getLogin()));
                if (request != null) {
                    if (clientData.getLogin() != null) {
                        clientSender.send(request);
                    } else printMachine.println(messenger.executionBan());
                }
            } else printMachine.println(messenger.commandNotFoundMessage(userCommand[0], userCommand[1]));
        } catch (NumberFormatException e) {
            printMachine.println(messenger.numberFormatArgumentMessage());
        }
    }


    /**
     * method for work with execute_command
     *
     * @param argument script direction
     * @return command execute status
     */
    @Override
    public void scriptMode(String argument) {
        boolean t = false;
        List<String> scriptStack = new ArrayList<>();
        String[] userCommand = {"", ""};
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
                    pickCommand(userCommand);
                }


            } while (scriptScanner.hasNextLine());
            poll.setUserScanner(tmpScanner);
            poll.setUserMode();

        } catch (FileNotFoundException exception) {
            printMachine.printErr(messenger.scriptFileDoesntExistMessage());

        } catch (NoSuchElementException exception) {
            printMachine.printErr(messenger.noSuchElementInputMessage());
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }


    }
}

