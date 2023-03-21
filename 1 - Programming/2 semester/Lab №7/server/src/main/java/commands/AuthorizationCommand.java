package commands;

import messenger.Messenger;
import server_works.DataBase;
import utility.Error;
import utility.Result;
import utility.Success;

public class AuthorizationCommand implements ServerCommand {

    private Messenger messenger;
    private DataBase dataBase;

    public AuthorizationCommand(Messenger messenger, DataBase dataBase) {
        this.messenger = messenger;
        this.dataBase = dataBase;
    }


    /**
     * Executes the command.
     *
     * @return Command exit status.
     */

    @Override
    public Result<Object> execute(Object... args) {
        String login = (String) args[0];
        String password = (String) args[1];

        if (dataBase.checkUser(login)) {
            return new Error(messenger.notSuccessfullyAuthMessage());
        } else {
            if (dataBase.authorizationUser(login, password)) {
                return new Success<String>(messenger.successfullyAuthMessage());
            } else return new Error(messenger.incorrectPasswordMessage());
        }
    }
}
