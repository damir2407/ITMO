import java.util.Scanner;

public class Reader {
    private Scanner scanner;

    public Reader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInputType() {
        System.out.println("Выберите способ задания данных:(введите номер)\n" +
                "1. Набор данных (таблицы x,y) \n" +
                "2. На основе выбранной функции");
        int inputValue = scanner.nextInt();
        if (inputValue != 1 && inputValue != 2) {
            System.out.println("Выбранный вами способ не реализован!");
            System.exit(0);
        }
        return inputValue;
    }

    public int readNumberOfFunction() {
        System.out.println("Выберите функцию(введите номер)\n" +
                "1. sin(x) \n" +
                "2. 6x^2-2x+3\n" +
                "3. 2x^2-x+9");

        int inputValue = scanner.nextInt();
        if (inputValue != 1 && inputValue != 2 && inputValue != 3) {
            System.out.println("Выбранный вами способ не реализован!");
            System.exit(0);
        }
        return inputValue;
    }

    public int readSize() {
        System.out.println("Введите количество точек: ");
        return scanner.nextInt();
    }

    public double[] readX(int size) {
        System.out.println("Введите значения X (через пробел):");
        double x[] = new double[size];
        for (int i = 0; i < size; i++) {
            x[i] = scanner.nextDouble();
        }
        return x;
    }

    public double[] readY(int size) {
        System.out.println("Введите значения Y (через пробел):");
        double y[] = new double[size];
        for (int i = 0; i < size; i++) {
            y[i] = scanner.nextDouble();
        }
        return y;
    }

    public double readXCoordinate() {
        System.out.println("Введите координату X: ");
        return scanner.nextDouble();
    }

}
