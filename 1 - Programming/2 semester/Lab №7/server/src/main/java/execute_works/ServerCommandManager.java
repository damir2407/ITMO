package execute_works;

import collection_works.CollectionManager;
import commands.*;
import messenger.Messenger;
import server_works.DataBase;
import server_works.Server;

import java.util.HashMap;

public class ServerCommandManager implements ServerCommandInterface {


    private HashMap<String, ServerCommand> allCommands = new HashMap<>();
    private ServerCommand clearCommand;
    private ServerCommand helpCommand;
    private ServerCommand infoCommand;
    private ServerCommand showCommand;
    private ServerCommand insertCommand;
    private ServerCommand removeByKeyCommand;
    private ServerCommand updateByIdCommand;
    private ServerCommand averageOfHeightCommand;
    private ServerCommand removeLowerKeyCommand;
    private ServerCommand printFieldDescendingHeartCountCommand;
    private ServerCommand groupCountingByCategoryCommand;
    private ServerCommand removeGreaterCommand;
    private ServerCommand replaceIfLoweCommand;
    private ServerCommand registrationCommand;
    private ServerCommand authorizationCommand;
    private CollectionManager collectionManager;
    private Messenger messenger;
    private DataBase dataBase;

    public ServerCommandManager(CollectionManager collectionManager, DataBase dataBase) {
        this.collectionManager = collectionManager;
        this.dataBase = dataBase;
    }

    @Override
    public void instantiateCommands() {
        this.helpCommand = new HelpCommand(messenger);
        this.registrationCommand = new RegistrationCommand(messenger, dataBase);
        this.authorizationCommand = new AuthorizationCommand(messenger, dataBase);
        this.clearCommand = new ClearCommand(collectionManager, messenger, dataBase);
        this.infoCommand = new InfoCommand(collectionManager, messenger);
        this.showCommand = new ShowCommand(collectionManager, messenger);
        this.insertCommand = new InsertCommand(collectionManager, messenger, dataBase);
        this.removeByKeyCommand = new RemoveKeyCommand(collectionManager, messenger, dataBase);
        this.updateByIdCommand = new UpdateCommand(collectionManager, messenger, dataBase);
        this.averageOfHeightCommand = new AverageOfHeightCommand(collectionManager, messenger);
        this.removeLowerKeyCommand = new RemoveLowerKey(collectionManager, messenger, dataBase);
        this.printFieldDescendingHeartCountCommand = new PrintFieldDescendingHeartCountCommand(collectionManager, messenger);
        this.groupCountingByCategoryCommand = new GroupCountingByCategoryCommand(collectionManager, messenger);
        this.removeGreaterCommand = new RemoveGreaterCommand(collectionManager, messenger, dataBase);
        this.replaceIfLoweCommand = new ReplaceIfLoweCommand(collectionManager, messenger, dataBase);
    }

    @Override
    public void putCommands() {
        allCommands.put("reg", this.registrationCommand);
        allCommands.put("auth", this.authorizationCommand);
        allCommands.put("help", this.helpCommand);
        allCommands.put("clear", this.clearCommand);
        allCommands.put("info", this.infoCommand);
        allCommands.put("show", this.showCommand);
        allCommands.put("insert", this.insertCommand);
        allCommands.put("remove_key", this.removeByKeyCommand);
        allCommands.put("update", this.updateByIdCommand);
        allCommands.put("average_of_height", this.averageOfHeightCommand);
        allCommands.put("remove_lower_key", this.removeLowerKeyCommand);
        allCommands.put("print_field_descending_heart_count", this.printFieldDescendingHeartCountCommand);
        allCommands.put("group_counting_by_category", this.groupCountingByCategoryCommand);
        allCommands.put("remove_greater", this.removeGreaterCommand);
        allCommands.put("replace_if_lowe", this.replaceIfLoweCommand);
    }


    @Override
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public HashMap<String, ServerCommand> getAllCommands() {
        return allCommands;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}

