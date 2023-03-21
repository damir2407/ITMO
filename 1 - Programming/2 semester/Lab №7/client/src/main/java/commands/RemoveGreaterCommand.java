package commands;

import data.SpaceMarine;
import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintInterface;
import request_structure.Request;
import request_structure.RequestInterface;

public class RemoveGreaterCommand implements AskCommand {

    private Messenger messenger;
    private PrintInterface printInterface;
    private Repeater repeater;


    public RemoveGreaterCommand(Messenger messenger, PrintInterface printInterface, Repeater repeater) {
        this.messenger = messenger;
        this.printInterface = printInterface;
        this.repeater = repeater;
    }

    @Override
    public RequestInterface prepare(String argument, String userLogin) {
        if (!argument.isEmpty()) {
            printInterface.println(messenger.argumentErrorMessage("remove_greater", false));
            return null;
        }


        SpaceMarine spaceMarine = repeater.repeatFields(null);

        return new Request("remove_greater", spaceMarine, userLogin);
    }


}
