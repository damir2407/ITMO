package messenger;

import data.SpaceMarine;

import java.util.Map;
import java.util.NavigableMap;

public interface Messenger {
    /**
     * @param marinesCollection
     * @return show info about fields
     */
    String getMarineFieldsInformation(Map<Integer, SpaceMarine> marinesCollection);


    /**
     * @return get description for help
     */
    String getCommandsDescription();

    /**
     * @param type               collection
     * @param size               collection
     * @param lastInitialization of collection
     * @param lastSave           of collection
     * @param path               of collection
     * @return for info commands
     */
    String getCollectionInfo(String type, int size, String lastInitialization, String lastSave);

    /**
     * @return collection is empty message
     */
    String collectionIsEmptyMessage();

    /**
     * @param array of count categorys
     * @return
     */
    String getCountingByCategory(int[] array);

    /**
     * @return item not found message
     */
    String itemNotFoundMessage();

    /**
     * @param s1 command
     * @param s2 argument
     * @return commandNotFound message
     */
    String commandNotFoundMessage(String s1, String s2);

    /**
     * @return recursion message
     */
    String recursionMessage();

    /**
     * @param f float
     * @return averageOfHeight command message
     */
    String averageOfHeightMessage(float f);

    /**
     * @return incorrectScriptMessage
     */
    String incorrectScriptFileMessage();

    /**
     * @return scriptFileDoesntExistMessage
     */
    String scriptFileDoesntExistMessage();

    /**
     * @return noSuchElementInputMessage
     */
    String noSuchElementInputMessage();

    /**
     * @return incorrectFileMessage
     */
    String incorrectFileMessage();

    /**
     * @param size size of collection
     * @return collectionSuccessfullyMessage
     */
    String collectionSuccessfullyMessage();

    /**
     * @return useHelpMessage
     */
    String useHelpMessage();

    /**
     * @return illegalStateMessage
     */
    String illegalStateMessage();

    String canNotSaveFile();

    String notSuccessfullyAuthMessage();

    String successfullyAuthMessage();

    String incorrectPasswordMessage();

    String executionBan();


    String notEnoughRights();
    /**
     * @return noSuchElementInFileMessage
     */
    String noSuchElementInFileMessage();

    /**
     * @return securityErrorMessage
     */
    String securityErrorMessage();

    /**
     * @return fileNotFoundMessage
     */
    String fileNotFoundMessage();

    /**
     * @return jsonSyntaxMessage
     */
    String jsonSyntaxMessage();

    /**
     * @return inputOutputMessage
     */
    String inputOutputMessage();

    /**
     * @return successfullySave
     */
    String successfullySave();

    /**
     * @return nameInputMessage
     */
    String nameInputMessage();

    /**
     * @return xCoordinateInputMessage
     */
    String xCoordinateInputMessage();

    /**
     * @return yCoordinateInputMessage
     */
    String yCoordinateInputMessage();

    /**
     * @return healthInputMessage
     */
    String healthInputMessage();

    /**
     * @return heartCountInputMessage
     */
    String heartCountInputMessage();

    /**
     * @return heightInputMessage
     */
    String heightInputMessage();

    /**
     * @return categoryInputMessage
     */
    String categoryInputMessage();

    /**
     * @param line line of available categorys
     * @return listOfAvailableCategoriesMessage
     */
    String listOfAvailableCategoriesMessage(String line);

    /**
     * @return chapterNameMessage
     */
    String chapterNameMessage();

    /**
     * @return chapterWorldMessage
     */
    String chapterWorldMessage();

    /**
     * @param command name
     * @param t       status
     * @return argumentErrorMessage
     */
    String argumentErrorMessage(String command, boolean t);

    String inputLoginMessage();

    String inputPasswordMessage();

    String notSuccessfullyRegUser();

    String successfullyRegUser();

    String canNotAuthAgain();

    String successfullyExecuteCommandMessage(String commandName);

    /**
     * @return successfullyClearedMessage
     */
    String successfullyClearedMessage();

    /**
     * @return successfullyExitMessage
     */
    String successfullyExitMessage();

    /**
     * @return hasNotBeenInitializationYetMessage
     */
    String hasNotBeenInitializationYetMessage();

    /**
     * @return hasNotBeenSaveYetMessage
     */
    String hasNotBeenSaveYetMessage();

    /**
     * @return successfullyAddMessage
     */
    String successfullyAddMessage();

    /**
     * @return numberFormatErrorKeyMessage
     */
    String numberFormatArgumentMessage();

    /**
     * @return successfullyDeleteMessage
     */
    String successfullyDeleteMessage();

    /**
     * @return notSuccessfullyDeleteMessage
     */
    String notSuccessfullyDeleteMessage();

    /**
     * @return notSuccessfullyDeleteByKeyMessage
     */
    String notSuccessfullyDeleteByKeyMessage();

    /**
     * @return successfullyReplaceMessage
     */
    String successfullyReplaceMessage();

    /**
     * @return notSuccessfullyReplaceMessage
     */
    String notSuccessfullyReplaceMessage();

    /**
     * @return successfullyUpdatedMessage
     */
    String successfullyUpdatedMessage();

    /**
     * @return idFormatErrorMessage
     */
    String idFormatErrorMessage();

    /**
     * @return incorrectChapterNameMessage
     */
    String incorrectChapterNameMessage();

    /**
     * @return incorrectChapterWorldMessage
     */
    String incorrectChapterWorldMessage();

    /**
     * @return incorrectCoordinatesMessage
     */
    String incorrectCoordinatesMessage();

    /**
     * @return incorrectXCoordinateMessage
     */
    String incorrectXCoordinateMessage();

    /**
     * @return incorrectYCoordinateMessage
     */
    String incorrectYCoordinateMessage();

    /**
     * @return incorrectNameMessage
     */
    String incorrectNameMessage();

    /**
     * @return incorrectCreationDateMessage
     */
    String incorrectCreationDateMessage();

    /**
     * @return incorrectHealthMessage
     */
    String incorrectHealthMessage();

    /**
     * @return incorrectIdMessage
     */
    String incorrectIdMessage();

    /**
     * @return incorrectIdUniquenessMessage
     */
    String incorrectIdUniquenessMessage();

    /**
     * @return incorrectHeartCountMessage
     */
    String incorrectHeartCountMessage();

    /**
     * @return incorrectHeightMessage
     */
    String incorrectHeightMessage();

    /**
     * @return incorrectCategoryMessage
     */
    String incorrectCategoryMessage();

    /**
     * @return categoryDoesNotExist
     */
    String categoryDoesNotExist();
}
