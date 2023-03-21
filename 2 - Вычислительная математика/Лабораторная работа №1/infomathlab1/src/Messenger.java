public class Messenger {

    public void selectMatrixInputMethodMessage() {
        System.out.println("Выберите метод ввода значений:\nВведите цифру 1 в консоль для ввода с клавиатуры или цифру 2 для ввода с файла");
    }

    public void selectedInputMethodIsConsoleMessage() {
        System.out.println("Вы выбрали возможность ввода данных с КЛАВИАТУРЫ");
    }

    public void selectedInputMethodIsFileMessage() {
        System.out.println("Вы выбрали возможность ввода данных с ФАЙЛА");
    }


    public void inputMethodNotFoundMessage() {
        System.out.println("Данного метода ввода данных не существует!");
    }

    public void inputFilePathMessage() {
        System.out.println("Введите расположение вашего файла:");
    }

    public void pathNotFoundMessage() {
        System.out.println("Файл по указанному расположению не найден!");
    }

    public void restartTheProgramMessage() {
        System.out.println("Некорректные данные! Запустите программу заново");
    }


    public void inputMatrixSizeMessage() {
        System.out.println("Введите размерность матрицы (n<=20):");
    }

    public void inputMatrixMessage() {
        System.out.println("Введите матрицу:");
    }

    public void incorrectNumberFormatMessage() {
        System.out.println("Введенное значение должно быть числом!");
    }

    public void chosenSizeMessage(Integer matrixSize) {
        System.out.println("Размер матрицы = " + matrixSize);
    }


    public void inputAccuracyMessage() {
        System.out.println("Введите точность:");
    }

    public void incorrectMatrixMessage() {
        System.out.println("Неправильно задана матрица!");
    }

    public void introducedMatrixMessage(Double[][] matrix) {
        System.out.println("Текущая матрица:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <= matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public void introducedAccuracy(Double accuracy) {
        System.out.println("Введенная точность: " + accuracy);
    }

    public void diagonalDominatingIsMissingMessage() {
        System.out.println("Отсутствует диагональное преобладание!");
    }

    public void newChangedMatrixMessage() {
        System.out.println("Произошла перестановка строк/столбцов для достижения диагонального преобладания");
    }

    public void computedXMessage(int index, double value) {
        System.out.println("x" + index + " = " + value);
    }

    public void numberOfIterationMessage(int count) {
        System.out.println("Итерация № " + count);
    }

    public void currentErrorEstimateMessage(double x) {
        System.out.println("Оценка погрешности: " + x);
    }

}
