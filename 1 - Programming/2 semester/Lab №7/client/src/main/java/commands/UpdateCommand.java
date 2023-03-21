package commands;

import data.SpaceMarine;
import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintInterface;
import request_structure.Request;
import request_structure.RequestInterface;

public class UpdateCommand implements AskCommand {
    private Messenger messenger;
    private PrintInterface printInterface;
    private Repeater repeater;

    public UpdateCommand(Messenger messenger, PrintInterface printInterface, Repeater repeater) {
        this.messenger = messenger;
        this.printInterface = printInterface;
        this.repeater = repeater;
    }

    @Override
    public RequestInterface prepare(String argument, String userLogin) {
        if (argument.isEmpty()) {
            printInterface.println(messenger.argumentErrorMessage("update", true));
            return null;
        }


        Integer id;
        id = Integer.parseInt(argument);
        SpaceMarine spaceMarine = repeater.repeatFields(id);

        return new Request("update", id, spaceMarine, userLogin);
    }
}
