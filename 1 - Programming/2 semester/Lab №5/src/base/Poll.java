package base;

import data.AstartesCategory;
import messenger.Messenger;
import messenger.PrintKeeper;
import validate.*;

import java.util.Scanner;

/**
 * Asks a user a marine's value.
 */

public class Poll implements PollKeeper {

    private Scanner userScanner;
    private boolean fileMode;
    private Validator fieldsValidation;
    private Messenger messenger;
    private PrintKeeper printMachine;

    public Poll(Scanner userScanner, Validator fieldsValidation, Messenger messenger, PrintKeeper printMachine) {
        this.userScanner = userScanner;
        this.fileMode = false;
        this.messenger = messenger;
        this.fieldsValidation = fieldsValidation;
        this.printMachine = printMachine;
    }

    /**
     * Asks a user the marine's name.
     *
     * @return Marine's name.
     */
    @Override
    public String claimName() {
        String name;
        printMachine.println(messenger.nameInputMessage());
        name = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(name);
        if (!(fieldsValidation.finalCheckName(name).isOK()))
            printMachine.println(fieldsValidation.finalCheckName(name).getErrorMessage());
        else name = (String) fieldsValidation.finalCheckName(name).getObject();
        return name;
    }

    /**
     * Sets a scanner to scan user input.
     *
     * @param userScanner Scanner to set.
     */
    @Override
    public void setUserScanner(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    @Override
    public Scanner getUserScanner() {
        return userScanner;
    }

    /**
     * Sets marine asker mode to 'User Mode'.
     */
    @Override
    public void setUserMode() {
        fileMode = false;
    }

    /**
     * Sets marine asker mode to 'File Mode'.
     */
    @Override
    public void setFileMode(boolean fileMode) {
        this.fileMode = fileMode;
    }

    /**
     * Asks a user the marine's XCoordinate.
     *
     * @return Marine's coordinate X.
     */
    @Override
    public Float claimXCoordinate() {
        String fieldX;
        Float x = null;
        printMachine.println(messenger.xCoordinateInputMessage());
        fieldX = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldX);
        if (!(fieldsValidation.finalCheckX(fieldX).isOK()))
            printMachine.println(fieldsValidation.finalCheckX(fieldX).getErrorMessage());
        else x = (Float) fieldsValidation.finalCheckX(fieldX).getObject();
        return x;
    }

    /**
     * Asks a user the marine's YCoordinate.
     *
     * @return Marine's coordinate Y.
     */
    @Override
    public Double claimYCoordinate() {
        String fieldY;
        Double y = null;
        printMachine.println(messenger.yCoordinateInputMessage());
        fieldY = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldY);
        if (!(fieldsValidation.finalCheckY(fieldY).isOK()))
            printMachine.println(fieldsValidation.finalCheckY(fieldY).getErrorMessage());
        else y = (Double) fieldsValidation.finalCheckY(fieldY).getObject();
        return y;
    }

    /**
     * Asks a user the marine's health.
     *
     * @return Marine's health.
     */
    @Override
    public Float claimHealth() {
        String fieldHealth;
        Float health = null;
        printMachine.println(messenger.healthInputMessage());
        fieldHealth = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldHealth);
        if (!(fieldsValidation.finalCheckHealth(fieldHealth).isOK()))
            printMachine.println(fieldsValidation.finalCheckHealth(fieldHealth).getErrorMessage());
        else health = (Float) fieldsValidation.finalCheckHealth(fieldHealth).getObject();
        return health;
    }

    /**
     * Asks a user the marine's heartCount.
     *
     * @return Marine's heartCount.
     */
    @Override
    public Integer claimHeartCount() {
        String fieldHeartCount;
        Integer heartCount = null;
        printMachine.println(messenger.heartCountInputMessage());
        fieldHeartCount = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldHeartCount);
        if (!(fieldsValidation.finalCheckHeartCount(fieldHeartCount).isOK()))
            printMachine.println(fieldsValidation.finalCheckHeartCount(fieldHeartCount).getErrorMessage());
        else heartCount = (Integer) fieldsValidation.finalCheckHeartCount(fieldHeartCount).getObject();
        return heartCount;
    }

    /**
     * Asks a user the marine's height.
     *
     * @return Marine's height.
     */
    @Override
    public float claimHeight() {
        String fieldHeight;
        float height = 0;
        printMachine.println(messenger.heightInputMessage());
        fieldHeight = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldHeight);
        if (!(fieldsValidation.finalCheckHeight(fieldHeight).isOK()))
            printMachine.println(fieldsValidation.finalCheckHeight(fieldHeight).getErrorMessage());
        else height = (float) fieldsValidation.finalCheckHeight(fieldHeight).getObject();
        return height;
    }

    /**
     * Asks a user the marine's category.
     *
     * @return Marine's category.
     */
    @Override
    public AstartesCategory claimCategory() {
        String fieldCategory;
        AstartesCategory category = null;
        String line = "";
        for (AstartesCategory astartesCategory : AstartesCategory.values()) {
            line += astartesCategory.name() + " ";
        }
        printMachine.println(messenger.listOfAvailableCategoriesMessage(line));
        printMachine.println(messenger.categoryInputMessage());
        fieldCategory = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(fieldCategory);
        if (!(fieldsValidation.finalCheckCategory(fieldCategory).isOK()))
            printMachine.println(fieldsValidation.finalCheckCategory(fieldCategory).getErrorMessage());
        else category = (AstartesCategory) fieldsValidation.finalCheckCategory(fieldCategory).getObject();
        return category;
    }

    /**
     * Asks a user the marine's chapterName.
     *
     * @return Marine's chapterName.
     */
    @Override
    public String claimChapterName() {
        String name = null;
        printMachine.println(messenger.chapterNameMessage());
        name = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(name);
        if (!(fieldsValidation.finalCheckChapterName(name).isOK()))
            printMachine.println(fieldsValidation.finalCheckChapterName(name).getErrorMessage());
        else name = (String) fieldsValidation.finalCheckChapterName(name).getObject();
        return name;
    }

    /**
     * Asks a user the marine's chapterWorld.
     *
     * @return Marine's chapterWorld.
     */
    @Override
    public String claimChapterWorld() {
        String world = null;
        printMachine.println(messenger.chapterWorldMessage());
        world = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(world);
        if (!(fieldsValidation.finalCheckChapterWorld(world).isOK()))
            printMachine.println(fieldsValidation.finalCheckChapterWorld(world).getErrorMessage());
        else world = (String) fieldsValidation.finalCheckChapterWorld(world).getObject();
        return world;
    }
}
