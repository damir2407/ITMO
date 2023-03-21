package execute_works;

import collection_works.CollectionManager;
import commands.*;
import messenger.Messenger;

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
    private CollectionManager collectionManager;
    private Messenger messenger;

    public ServerCommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void instantiateCommands() {
        this.helpCommand = new HelpCommand(messenger);
        this.clearCommand = new ClearCommand(collectionManager, messenger);
        this.infoCommand = new InfoCommand(collectionManager, messenger);
        this.showCommand = new ShowCommand(collectionManager, messenger);
        this.insertCommand = new InsertCommand(collectionManager, messenger);
        this.removeByKeyCommand = new RemoveKeyCommand(collectionManager, messenger);
        this.updateByIdCommand = new UpdateCommand(collectionManager, messenger);
        this.averageOfHeightCommand = new AverageOfHeightCommand(collectionManager, messenger);
        this.removeLowerKeyCommand = new RemoveLowerKey(collectionManager, messenger);
        this.printFieldDescendingHeartCountCommand = new PrintFieldDescendingHeartCountCommand(collectionManager, messenger);
        this.groupCountingByCategoryCommand = new GroupCountingByCategoryCommand(collectionManager, messenger);
        this.removeGreaterCommand = new RemoveGreaterCommand(collectionManager, messenger);
        this.replaceIfLoweCommand = new ReplaceIfLoweCommand(collectionManager, messenger);
    }

    @Override
    public void putCommands() {
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

