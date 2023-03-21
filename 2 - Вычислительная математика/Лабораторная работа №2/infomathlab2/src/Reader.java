import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Reader {
    private Messenger messenger;
    private Scanner mainScanner;
    private boolean filemode = false;

    public Reader(Messenger messenger, Scanner mainScanner) {
        this.messenger = messenger;
        this.mainScanner = mainScanner;
    }

    public NonLinearType readTypeOfRequest() {
        String selectedType = "";
        try {
            messenger.selectEquationOrSystemMessage();
            selectedType = mainScanner.nextLine().trim();
            switch (selectedType) {
                case ("1"):
                    return NonLinearType.SIMPLE_EQUATION;
                case ("2"):
                    return NonLinearType.SYSTEM_OF_EQUATIONS;
                default:
                    messenger.incorrectDataMessage();
                    System.exit(0);
                    break;
            }
        } catch (NullPointerException | NoSuchElementException exception) {
            messenger.incorrectDataMessage();
            System.exit(0);
        }
        return null;
    }

    public boolean isFilemode() {
        return filemode;
    }

    public void setFilemode(boolean filemode) {
        this.filemode = filemode;
    }

    public String readNumberOfEquation() {
        try {
            messenger.selectEquationMessage();
            String inputValue = mainScanner.nextLine().trim();
            return inputValue;

        } catch (NoSuchElementException noSuchElementException) {
            messenger.incorrectDataMessage();
            System.exit(0);
        }
        return null;
    }

    public MethodType readSolutionMethod() {
        try {
            messenger.selectSolutionMethodMessage();
            String inputValue = mainScanner.nextLine().trim();
            switch (inputValue) {
                case ("1"):
                    return MethodType.CHORD_METHOD;
                case ("2"):
                    return MethodType.SIMPLE_ITERATION_METHOD;
                default:
                    messenger.incorrectDataMessage();
                    System.exit(0);
                    break;
            }

        } catch (NoSuchElementException noSuchElementException) {
            messenger.incorrectDataMessage();
            System.exit(0);
        }
        return null;
    }


    public void readInputMethod() {
        try {
            messenger.selectInputMethodMessage();
            String inputValue = mainScanner.nextLine().trim();

            switch (inputValue) {
                case ("1"):
                    messenger.selectedInputMethodIsConsoleMessage();
                    break;
                case ("2"):
                    messenger.selectedInputMethodIsFileMessage();
                    messenger.inputFilePathMessage();

                    this.mainScanner = new Scanner(new File(readPath()));
                    setFilemode(true);
                    break;

                default:
                    messenger.inputMethodNotFoundMessage();
                    break;
            }


        } catch (NoSuchElementException noSuchElementException) {
            messenger.incorrectDataMessage();
            System.exit(0);
        } catch (FileNotFoundException | NullPointerException fileNotFoundException) {
            messenger.pathNotFoundMessage();
            messenger.incorrectDataMessage();
            System.exit(0);
        }
    }

    public String readPath() {
        Scanner scanner = new Scanner(System.in);
        try {
            String path = scanner.nextLine().trim();
            if (!path.isEmpty()) {
                return path;
            }
        } catch (NoSuchElementException noSuchElementException) {
            messenger.incorrectDataMessage();
            System.exit(0);
        }
        return null;
    }

    public String readIntervalBoundaries() {
        try {
            messenger.inputIntervalBoundariesMessage();
            String inputValue = mainScanner.nextLine().trim();
            if (filemode) System.out.println("Введенный интервал: " + inputValue);
            return inputValue;

        } catch (NoSuchElementException noSuchElementException) {
            messenger.incorrectDataMessage();
            System.exit(0);
        }
        return null;
    }

    public Double readAccuracy() {
        try {
            messenger.inputAccuracyMessage();
            String inputValue = mainScanner.nextLine().trim();
            if (filemode) System.out.println("Введенная точность: " + inputValue);
            return Double.parseDouble(inputValue);

        } catch (NoSuchElementException noSuchElementException) {
            messenger.incorrectDataMessage();
            System.exit(0);
        }
        return null;
    }


    public String readOutputMethod() {
        try {
            Scanner scanner = new Scanner(System.in);
            messenger.selectWriteMethodMessage();
            String inputValue = scanner.nextLine().trim();

            switch (inputValue) {
                case ("1"):
                    messenger.selectedOutputMethodIsConsoleMessage();
                    return null;
                case ("2"):
                    messenger.selectedOutputMethodIsFileMessage();
                    messenger.inputFilePathMessage();

                    return readPath();

                default:
                    messenger.inputMethodNotFoundMessage();
                    break;
            }


        } catch (NoSuchElementException noSuchElementException) {
            messenger.incorrectDataMessage();
            System.exit(0);
        } catch (NullPointerException nullPointerException) {
            messenger.pathNotFoundMessage();
            messenger.incorrectDataMessage();
            System.exit(0);
        }
        return null;
    }

}
