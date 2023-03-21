package messenger;

/**
 * class for English messages
 */
public class EnglishMessenger extends AbstractMessenger {
    public EnglishMessenger() {
        setCommandsDescription();
    }

    public void setCommandsDescription() {
        commandsDescription.put("help", "display help for available commands");
        commandsDescription.put("info", "output information about the collection (type, initialization date, number of items, etc.) to the standard output stream.)");
        commandsDescription.put("show", "output to the standard output stream all elements of the collection in the string representation");
        commandsDescription.put("insert null {element}", "add a new element with the specified key");
        commandsDescription.put("update id {element}", "update the value of a collection element whose id is equal to the specified one");
        commandsDescription.put("remove_key null", "remove an item from the collection by its key");
        commandsDescription.put("clear", "clear collection");
        commandsDescription.put("save", "save collection to file");
        commandsDescription.put("execute_script file_name", "read and execute the script from the specified file. The script contains commands in the same form as the user enters them interactively.");
        commandsDescription.put("exit", "end the program (without saving to a file)");
        commandsDescription.put("remove_greater {element}", "remove all items from the collection that exceed the specified value");
        commandsDescription.put("replace_if_lowe null {element}", "replace the value by key if the new value is less than the old one");
        commandsDescription.put("remove_lower_key null", "remove from the collection all items whose key is less than the specified one");
        commandsDescription.put("average_of_height", "output the average value of the height field for all items in the collection");
        commandsDescription.put("group_counting_by_category", "group collection items by the value of the category field, output the number of items in each group");
        commandsDescription.put("print_field_descending_heart_count", "print the values of the heartCount field of all elements in descending order");
    }

    @Override
    public String getCollectionInfo(String type, int size, String lastInitialization, String lastSave) {
        return "Collection information:\n" + "Collection type:" + type + "\nThe number of items in the collection: " + size +
                "\nLast initialization date: " + lastInitialization + "\nLast save date: " + lastSave;
    }

    @Override
    public String notEnoughRights(){
        return "You do not have sufficient rights to modify the object";
    }


    @Override
    public String successfullyExecuteCommandMessage(String commandName) {
        return "Command " + commandName + " is successfully compiled!";
    }

    @Override
    public String successfullyRegUser() {
        return "User successfully registered in data base";
    }

    @Override
    public String notSuccessfullyAuthMessage() {
        return "there is no such user in the database";
    }

    @Override
    public String successfullyAuthMessage() {
        return "you are successfully logged in";
    }

    @Override
    public String incorrectPasswordMessage() {
        return "The password is incorrect";
    }

    @Override
    public String executionBan() {
        return "Unauthorized users are not allowed to execute commands!";
    }


    @Override
    public String notSuccessfullyRegUser() {
        return "This user is already register";
    }

    @Override
    public String canNotAuthAgain() {
        return "You have been already authorized in base!";
    }

    @Override
    public String inputLoginMessage() {
        return "Input login:";
    }


    @Override
    public String inputPasswordMessage() {
        return "Input password:";
    }


    @Override
    public String collectionIsEmptyMessage() {
        return "The collection is empty!";
    }

    @Override
    public String itemNotFoundMessage() {
        return "There is no soldier with this parameter!";
    }

    @Override
    public String commandNotFoundMessage(String s1, String s2) {
        return "Command '" + s1 + "" + s2 + "' not found. Type 'help' for help.";
    }

    @Override
    public String getCountingByCategory(int[] array) {
        return "Number of elements in the SCOUT group: " + array[0] +
                "\nNumber of items in DREADNOUGHT group: " + array[1] +
                "\nNumber of items in the AGGRESSOR group:" + array[2] +
                "\nNumber of elements in the SUPPRESSOR group:" + array[3] +
                "\nNumber of items in the CHAPLAIN group:" + array[4];
    }

    @Override
    public String recursionMessage() {
        return "Scripts cannot be called recursively!";
    }

    @Override
    public String incorrectScriptFileMessage() {
        return "Check the script for the correctness of the entered data!";
    }

    @Override
    public String scriptFileDoesntExistMessage() {
        return "Script file not found!";
    }

    @Override
    public String noSuchElementInputMessage() {
        return "No user input detected";
    }

    @Override
    public String incorrectFileMessage() {
        return "An error occurred while reading fields from the file! Data from the file is not loaded";
    }

    @Override
    public String collectionSuccessfullyMessage() {
        return "Collection loaded successfully! Added " + "\n" + useHelpMessage();
    }

    @Override
    public String averageOfHeightMessage(float f) {
        return "The average value of the height field of all elements: " + f;
    }

    @Override
    public String useHelpMessage() {
        return "Type help to display a list of available commands";
    }

    @Override
    public String illegalStateMessage() {
        return "Unexpected error";
    }

    @Override
    public String noSuchElementInFileMessage() {
        return "The boot file is empty!";
    }

    @Override
    public String securityErrorMessage() {
        return "Insufficient permissions to read / write the file!\nWarning! You can't save this collection anymore";
    }

    @Override
    public String fileNotFoundMessage() {
        return "The path to the json file must be passed through the My_Path environment variable!\nWarning! You can't save this collection anymore";
    }

    @Override
    public String canNotSaveFile() {
        return "Collection could not be saved due to invalid path";
    }

    @Override
    public String jsonSyntaxMessage() {
        return "JSON syntax error! The data from the file was not loaded!";
    }

    @Override
    public String inputOutputMessage() {
        return "Error! The boot file is a directory or cannot be opened!";
    }

    @Override
    public String successfullySave() {
        return "Collection successfully saved to file!";
    }

    @Override
    public String nameInputMessage() {
        return "Enter the soldier's name: ";
    }

    @Override
    public String xCoordinateInputMessage() {
        return "Enter the X coordinate:";
    }

    @Override
    public String yCoordinateInputMessage() {
        return "Enter the Y coordinate:";
    }

    @Override
    public String healthInputMessage() {
        return "Enter health:";
    }

    @Override
    public String heartCountInputMessage() {
        return "Enter the number of hearts:";
    }

    @Override
    public String heightInputMessage() {
        return "Enter height:";
    }

    @Override
    public String categoryInputMessage() {
        return "Enter a category:";
    }

    @Override
    public String listOfAvailableCategoriesMessage(String line) {
        return "List of available categories:" + line;
    }

    @Override
    public String chapterNameMessage() {
        return "Enter the chapter name:";
    }

    @Override
    public String chapterWorldMessage() {
        return "Enter the name of the world:";
    }

    @Override
    public String argumentErrorMessage(String command, boolean t) {
        if (!t) return "Use the command" + command + " without the argument!";
        else return "Use the command" + command + " with the argument!";
    }

    @Override
    public String successfullyClearedMessage() {
        return "Collection cleared";
    }

    @Override
    public String successfullyExitMessage() {
        return "Program termination...";
    }

    @Override
    public String hasNotBeenInitializationYetMessage() {
        return "Has not been initialized yet.";
    }

    @Override
    public String hasNotBeenSaveYetMessage() {
        return "There was no save yet.";
    }

    @Override
    public String successfullyAddMessage() {
        return "Soldier added successfully!";
    }

    @Override
    public String numberFormatArgumentMessage() {
        return "The key parameter must be represented by a number!";
    }

    @Override
    public String successfullyDeleteMessage() {
        return "The soldier(s) were successfully deleted";
    }

    @Override
    public String notSuccessfullyDeleteMessage() {
        return "No elements exceeding the specified one!";
    }

    @Override
    public String notSuccessfullyDeleteByKeyMessage() {
        return "Elements whose key is less than the specified one - no!";
    }

    @Override
    public String successfullyReplaceMessage() {
        return "Replacement was successful!";
    }

    @Override
    public String notSuccessfullyReplaceMessage() {
        return "Element cannot be replaced!";
    }

    @Override
    public String successfullyUpdatedMessage() {
        return "Collection item successfully updated!";
    }

    @Override
    public String idFormatErrorMessage() {
        return "The id parameter must be represented by a number!";
    }

    @Override
    public String incorrectChapterNameMessage() {
        return "The chapterName field cannot be null, the string cannot be empty";
    }

    @Override
    public String incorrectChapterWorldMessage() {
        return "The chapterWorld field cannot be null";
    }

    @Override
    public String incorrectCoordinatesMessage() {
        return "The coordinates field cannot be null";
    }

    @Override
    public String incorrectXCoordinateMessage() {
        return "Error! The field must be of the Float type, the maximum field value is 610, the field cannot be null";
    }

    @Override
    public String incorrectYCoordinateMessage() {
        return "Error! The field must be of type Double, the field cannot be null";
    }

    @Override
    public String incorrectNameMessage() {
        return "The name field cannot be null, the string cannot be empty";
    }

    @Override
    public String incorrectCreationDateMessage() {
        return "The CreationDate field cannot be null";
    }

    @Override
    public String incorrectHealthMessage() {
        return "Error! The health field must be of the float type, cannot be null, and the field value must be greater than 0";
    }

    @Override
    public String incorrectIdMessage() {
        return "The id field cannot be null, the field value must be greater than 0";
    }

    @Override
    public String incorrectIdUniquenessMessage() {
        return "The id field must be unique!";
    }

    @Override
    public String incorrectHeartCountMessage() {
        return "Error! The heartCount field must be of type Integer, cannot be null, The value of the field must be greater than 0, the maximum value of the field: 3";
    }

    @Override
    public String incorrectHeightMessage() {
        return "The height parameter must be of the float type";
    }

    @Override
    public String incorrectCategoryMessage() {
        return "The category field cannot be null";
    }

    @Override
    public String categoryDoesNotExist() {
        return "There is no such category! The field cannot be null";
    }
}
