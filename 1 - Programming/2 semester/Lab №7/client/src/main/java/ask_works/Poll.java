package ask_works;

import client_validate.ClientValidator;
import data.AstartesCategory;
import messenger.Messenger;
import print_works.PrintInterface;
import utility.Error;
import utility.Result;
import utility.Success;

import java.util.Scanner;

/**
 * Asks a user a marine's value.
 */

public class Poll implements PollInterface {

    private Scanner userScanner;
    private boolean fileMode;
    private Messenger messenger;
    private PrintInterface printMachine;
    private ClientValidator clientValidator;

    public Poll(Scanner userScanner, ClientValidator clientValidator, Messenger messenger, PrintInterface printMachine) {
        this.userScanner = userScanner;
        this.clientValidator = clientValidator;
        this.fileMode = false;
        this.messenger = messenger;
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
        Result<Object> result = clientValidator.finalCheckName(name);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            name = ((Success<String>) result).getObject();
        return name;
    }


    @Override
    public String claimLogin() {
        printMachine.println(messenger.inputLoginMessage());
        String login = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(login);
        return login;
    }

    @Override
    public String claimPassword() {
        printMachine.println(messenger.inputPasswordMessage());
        String password = userScanner.nextLine().trim();
        if (fileMode) printMachine.println(password);
        return password;
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
        Result<Object> result = clientValidator.finalCheckX(fieldX);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            x = ((Success<Float>) result).getObject();
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
        Result<Object> result = clientValidator.finalCheckY(fieldY);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            y = ((Success<Double>) result).getObject();
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
        Result<Object> result = clientValidator.finalCheckHealth(fieldHealth);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            health = ((Success<Float>) result).getObject();
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
        Result<Object> result = clientValidator.finalCheckHeartCount(fieldHeartCount);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            heartCount = ((Success<Integer>) result).getObject();
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
        Result<Object> result = clientValidator.finalCheckHeight(fieldHeight);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            height = ((Success<Float>) result).getObject();
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
        Result<Object> result = clientValidator.finalCheckCategory(fieldCategory);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            category = ((Success<AstartesCategory>) result).getObject();
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
        Result<Object> result = clientValidator.finalCheckChapterName(name);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            name = ((Success<String>) result).getObject();
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
        Result<Object> result = clientValidator.finalCheckChapterWorld(world);
        if (result instanceof Error)
            printMachine.println(((Error) result).getErrorMessage());
        if (result instanceof Success)
            world = ((Success<String>) result).getObject();
        return world;
    }
}
