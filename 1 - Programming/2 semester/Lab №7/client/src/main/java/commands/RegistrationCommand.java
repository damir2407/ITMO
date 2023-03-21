package commands;

import ask_works.PollInterface;
import client_works.ClientData;
import messenger.Messenger;
import print_works.PrintInterface;
import request_structure.Request;
import request_structure.RequestInterface;

public class RegistrationCommand implements AskCommand {
    private Messenger messenger;
    private PrintInterface printMachine;
    private PollInterface poll;
    private ClientData clientData;


    public RegistrationCommand(Messenger messenger, PrintInterface printMachine, PollInterface poll, ClientData clientData) {
        this.messenger = messenger;
        this.printMachine = printMachine;
        this.poll = poll;
        this.clientData = clientData;

    }


    @Override
    public RequestInterface prepare(String argument, String userLogin) {
        if (!argument.isEmpty()) {
            printMachine.println(messenger.argumentErrorMessage("reg", false));
            return null;
        }

        if (clientData.getLogin() != null) {
            printMachine.println(messenger.canNotAuthAgain());
            return null;
        } else {
            String login = poll.claimLogin();
            String password = poll.claimPassword();
            clientData.setLogin(login);
            return new Request("reg", login, password);
        }
    }

}
