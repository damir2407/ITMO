package base;

import command_structure.*;
import messenger.Messenger;
import messenger.PrintKeeper;

import java.util.HashMap;


/**
 * Class for working with our commands.
 */

public class CommandManager implements CommandKeeper {
    private HashMap<String, Converter<String, Boolean>> allCommands = new HashMap<>();
    private CommonCommand clearCommand;
    private CommonCommand exitCommand;
    private CommonCommand helpCommand;
    private CommonCommand infoCommand;
    private CommonCommand showCommand;
    private CommonCommand saveCommand;
    private AskCommand insertCommand;
    private CommonCommand removeByKeyCommand;
    private AskCommand updateByIdCommand;
    private CommonCommand averageOfHeightCommand;
    private CommonCommand removeLowerKeyCommand;
    private CommonCommand printFieldDescendingHeartCountCommand;
    private CommonCommand groupCountingByCategoryCommand;
    private AskCommand removeGreaterCommand;
    private AskCommand replaceIfLoweCommand;
    private CommonCommand executeScriptCommand;
    private Repeater repeater;


    public CommandManager(Repeater repeater) {
        this.repeater = repeater;
    }

    public void createCommands(CollectionKeeper collectionManager, Messenger messenger, PrintKeeper printMachine) {

        this.clearCommand = new ClearCommand(collectionManager, messenger, printMachine);
        this.exitCommand = new ExitCommand(messenger, printMachine);
        this.helpCommand = new HelpCommand(messenger, printMachine);
        this.infoCommand = new InfoCommand(collectionManager, messenger, printMachine);
        this.showCommand = new ShowCommand(collectionManager, messenger, printMachine);
        this.saveCommand = new SaveCommand(collectionManager, messenger, printMachine);
        this.insertCommand = new InsertCommand(collectionManager, messenger, printMachine);
        this.removeByKeyCommand = new RemoveKeyCommand(collectionManager, messenger, printMachine);
        this.updateByIdCommand = new UpdateCommand(collectionManager, messenger, printMachine);
        this.averageOfHeightCommand = new AverageOfHeightCommand(collectionManager, messenger, printMachine);
        this.removeLowerKeyCommand = new RemoveLowerKey(collectionManager, messenger, printMachine);
        this.printFieldDescendingHeartCountCommand = new PrintFieldDescendingHeartCountCommand(collectionManager, messenger, printMachine);
        this.groupCountingByCategoryCommand = new GroupCountingByCategoryCommand(collectionManager, messenger, printMachine);
        this.removeGreaterCommand = new RemoveGreaterCommand(collectionManager, messenger, printMachine);
        this.replaceIfLoweCommand = new ReplaceIfLoweCommand(collectionManager, messenger, printMachine);
        this.executeScriptCommand = new ExecuteScriptCommand(messenger, printMachine);
    }


    /**
     * method puts links to the execution of methods
     */
    public void putCommands() {
        allCommands.put("help", this::help);
        allCommands.put("clear", this::clear);
        allCommands.put("exit", this::exit);
        allCommands.put("info", this::info);
        allCommands.put("show", this::show);
        allCommands.put("save", this::save);
        allCommands.put("insert", this::insert);
        allCommands.put("remove_key", this::removeByKey);
        allCommands.put("update", this::updateById);
        allCommands.put("average_of_height", this::averageOfHeight);
        allCommands.put("remove_lower_key", this::removeLower);
        allCommands.put("print_field_descending_heart_count", this::printFieldHeart);
        allCommands.put("group_counting_by_category", this::groupCountingByCategory);
        allCommands.put("remove_greater", this::removeGreater);
        allCommands.put("replace_if_lowe", this::replaceIfLowe);
        allCommands.put("execute_script", this::executeScript);
    }


    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */

    @Override
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Prints info about the all commands.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean help(String argument) {
        return helpCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }


    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean insert(String argument) {
        return insertCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean removeByKey(String argument) {
        return removeByKeyCommand.execute(argument);
    }


    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean updateById(String argument) {
        return updateByIdCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean averageOfHeight(String argument) {
        return averageOfHeightCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean removeLower(String argument) {
        return removeLowerKeyCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean printFieldHeart(String argument) {
        return printFieldDescendingHeartCountCommand.execute(argument);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean groupCountingByCategory(String argument) {
        return groupCountingByCategoryCommand.execute(argument);
    }


    @Override
    public HashMap<String, Converter<String, Boolean>> getAllCommands() {
        return allCommands;
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean removeGreater(String argument) {
        return removeGreaterCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean replaceIfLowe(String argument) {
        return replaceIfLoweCommand.execute(argument, repeater);
    }

    /**
     * Executes needed command.
     *
     * @param argument Its argument.
     * @return Command exit status.
     */
    @Override
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }
}
