import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Reader implements IRead {
    private Scanner scannerReadMethod;
    private Messenger messenger;

    public Reader(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public void readInputMethod() {
        boolean repeatQuiz = true;
        try {
            Scanner scanner = new Scanner(System.in);

            while (repeatQuiz) {
                messenger.selectMatrixInputMethodMessage();
                String inputValue = scanner.nextLine().trim();
                if (!inputValue.isEmpty()) {
                    switch (inputValue) {
                        case ("1"):
                            messenger.selectedInputMethodIsConsoleMessage();
                            this.scannerReadMethod = new Scanner(System.in);
                            repeatQuiz = false;
                            break;
                        case ("2"):
                            messenger.selectedInputMethodIsFileMessage();
                            messenger.inputFilePathMessage();

                            try {
                                this.scannerReadMethod = new Scanner(new File(readPath()));
                            } catch (FileNotFoundException | NullPointerException fileNotFoundException) {
                                messenger.pathNotFoundMessage();
                                messenger.restartTheProgramMessage();
                                System.exit(1);
                            }

                            repeatQuiz = false;
                            break;

                        default:
                            messenger.inputMethodNotFoundMessage();
                            break;
                    }
                }
            }
        } catch (NoSuchElementException noSuchElementException) {

        }
    }

    @Override
    public String readPath() {
        try {
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine().trim();
            if (!path.isEmpty()) {
                return path;
            }
        } catch (NoSuchElementException noSuchElementException) {
        }
        return null;
    }


    @Override
    public Integer readMatrixSize() {
        try {
            messenger.inputMatrixSizeMessage();
            String inputValue = this.scannerReadMethod.nextLine().trim();
            if (inputValue.isEmpty()) {
                messenger.incorrectNumberFormatMessage();
                messenger.restartTheProgramMessage();
                System.exit(1);
            }
            Integer matrixSize = Integer.parseInt(inputValue);
            if (matrixSize > 20 || matrixSize < 1) {
                messenger.restartTheProgramMessage();
                System.exit(1);
            }
            messenger.chosenSizeMessage(matrixSize);
            return matrixSize;

        } catch (NoSuchElementException noSuchElementException) {

        } catch (NumberFormatException numberFormatException) {
            messenger.incorrectNumberFormatMessage();
            messenger.restartTheProgramMessage();
            System.exit(1);
        }

        return null;
    }

    @Override
    public Double[][] readMatrix(Integer matrixSize) {
        try {
            messenger.inputMatrixMessage();
            Double[][] matrix = new Double[matrixSize][matrixSize + 1];
            Double[] results = new Double[matrixSize];
            for (int i = 0; i < matrixSize; i++) {
                String[] row = scannerReadMethod.nextLine().split(" ");
                for (int j = 0; j < matrixSize + 1; j++) {
                    matrix[i][j] = Double.parseDouble(row[j]);
                    results[i] = Double.parseDouble(row[j]);
                }
            }

            messenger.introducedMatrixMessage(matrix);
            return matrix;

        } catch (NoSuchElementException noSuchElementException) {

        } catch (NumberFormatException numberFormatException) {
            messenger.incorrectNumberFormatMessage();
            messenger.restartTheProgramMessage();
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException exception) {
            messenger.incorrectMatrixMessage();
            messenger.restartTheProgramMessage();
            System.exit(1);
        }
        return null;

    }

    @Override
    public Double readAccuracy() {
        try {
            messenger.inputAccuracyMessage();
            String inputValue = this.scannerReadMethod.nextLine().trim();
            if (inputValue.isEmpty()) {
                messenger.incorrectNumberFormatMessage();
                messenger.restartTheProgramMessage();
                System.exit(1);
            }
            Double accuracy = Double.parseDouble(inputValue);
            messenger.introducedAccuracy(accuracy);
            return accuracy;

        } catch (NoSuchElementException noSuchElementException) {

        } catch (NumberFormatException numberFormatException) {
            messenger.incorrectNumberFormatMessage();
            messenger.restartTheProgramMessage();
            System.exit(1);
        }
        return null;
    }

}
