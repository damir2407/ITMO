package commands;

import data.SpaceMarine;
import input_fields_works.Repeater;
import messenger.Messenger;
import print_works.PrintInterface;
import request_structure.Request;
import request_structure.RequestInterface;

public class ReplaceIfLoweCommand implements AskCommand {

    private Messenger messenger;
    private PrintInterface printInterface;
    private Repeater repeater;

    public ReplaceIfLoweCommand(Messenger messenger, PrintInterface printInterface, Repeater repeater) {
        this.messenger = messenger;
        this.printInterface = printInterface;
        this.repeater = repeater;
    }


    @Override
    public RequestInterface prepare(String argument,String userLogin) {
        if (argument.isEmpty()) {
            printInterface.println(messenger.argumentErrorMessage("replace_if_lowe", true));
            return null;
        }

        Integer key;
        key = Integer.parseInt(argument);
        SpaceMarine spaceMarine = repeater.repeatFields(null);

        return new Request("replace_if_lowe", key, spaceMarine,userLogin);
    }
}
