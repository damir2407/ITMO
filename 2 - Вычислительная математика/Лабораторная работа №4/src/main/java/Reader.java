import writers.ConsoleWriter;
import writers.FileWriter;
import writers.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
    private Scanner scanner;
    private boolean fileMode;


    public Reader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Writer readOutputMethod() throws IOException {
        System.out.println("Выберите способ ВЫВОДА значений (введите цифру):\n1: Вывод в КОНСОЛЬ\n2: Вывод в ФАЙЛ");
        String inputValue = scanner.nextLine().trim();

        switch (inputValue) {
            case ("1"):
                System.out.println("Вывод данных в КОНСОЛЬ");
                return new ConsoleWriter();
            case ("2"):
                System.out.println("Вывод данных в ФАЙЛ");
                System.out.println("Введите расположение вашего файла:");
                return new FileWriter(readPath());
            default:
                System.out.println("Данный способ вывода данных не предусмотрен!");
                System.exit(0);
        }
        return null;

    }

    public void readInputMethod() throws FileNotFoundException {
        System.out.println("Выберите способ ВВОДА значений (введите цифру):\n1: С КЛАВИАТУРЫ\n2: С ФАЙЛА");
        String inputValue = scanner.nextLine().trim();

        switch (inputValue) {
            case ("1"):
                System.out.println("Ввод данных осуществляется с КЛАВИАТУРЫ");
                fileMode = false;
                break;
            case ("2"):
                System.out.println("Ввод данных осуществляется с ФАЙЛА");
                System.out.println("Введите расположение вашего файла:");
                this.scanner = new Scanner(new File(readPath()));
                fileMode = true;
                break;

            default:
                System.out.println("Данный способ ввода данных не предусмотрен!");
                System.exit(0);
        }

    }

    public String readPath() {
        return scanner.nextLine().trim();
    }

    public int readNumberOfCoordinates() {
        System.out.println("Введите количество точек:");
        int value = scanner.nextInt();
        if (fileMode) System.out.println(value);
        return value;
    }


    public double[] readArrayX(int n) {
        System.out.println("Введите значения X (через пробел):");
        double x[] = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
        }

        if (fileMode) {
            for (int i = 0; i < n; i++) {
                System.out.println(x[i] + " ");
            }
        }
        return x;
    }

    public double[] readArrayY(int n) {
        System.out.println("Введите значения Y (через пробел):");
        double y[] = new double[n];
        for (int i = 0; i < n; i++) {
            y[i] = scanner.nextDouble();
        }

        if (fileMode) {
            for (int i = 0; i < n; i++) {
                System.out.println(y[i] + " ");
            }
        }
        return y;
    }
}
