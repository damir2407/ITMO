import methods.AbstractMethod;
import methods.RectangleMethod;
import methods.TrapezoidalMethod;

import java.util.Scanner;

public class Reader {
    private Scanner scanner;
    private Functions functions;


    public Reader(Scanner scanner, Functions functions) {
        this.scanner = scanner;
        this.functions = functions;
    }


    public AbstractMethod readSolveMethod() {

        String chosenNumberOfFunction = readNumberOfFunction();
        Double borderA = Double.parseDouble(readBorderA());
        Double borderB = Double.parseDouble(readBorderB());
        Double accuracy = Double.parseDouble(readAccuracy());


        System.out.println("Выберите номер метода для решения функции \n" +
                "1: Метод прямоугольников\n" +
                "2: Метод трапеций");

        String chosenMethod = scanner.nextLine().trim();
        if (chosenMethod.equals("1"))
            return new RectangleMethod(chosenNumberOfFunction, borderA, borderB, accuracy);
        if (chosenMethod.equals("2"))
            return new TrapezoidalMethod(chosenNumberOfFunction, borderA, borderB, accuracy);
        else {
            System.out.println("Данного метода не существует/не реализован");
            System.exit(0);
        }
        return null;
    }

    public String readNumberOfFunction() {
        System.out.println("Введите номер функции: \n" +
                functions.toString());
        String chosenNumberOfFunction = scanner.nextLine().trim();

        if (functions.getFunctionsMap().get(chosenNumberOfFunction) == null) {
            System.out.println("Такой функции не существует!");
            System.exit(0);
        }
        return chosenNumberOfFunction;
    }

    public String readBorderA() {
        System.out.println("Введите нижний предел интегрирования (a): ");
        return scanner.nextLine().trim();
    }

    public String readBorderB() {
        System.out.println("Введите верхний предел интегрирования (b): ");
        return scanner.nextLine().trim();
    }

    public String readAccuracy() {
        System.out.println("Введите точность: ");
        return scanner.nextLine().trim();
    }

}
