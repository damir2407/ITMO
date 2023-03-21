import functions.*;
import writers.Writer;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            Reader reader = new Reader(new Scanner(System.in));
            Writer writer = reader.readOutputMethod();
            reader.readInputMethod();


            int n = reader.readNumberOfCoordinates();
            double x[] = reader.readArrayX(n);
            double y[] = reader.readArrayY(n);

            AbstractFunction linearFunction = new LinearFunction(n, x, y);
            AbstractFunction polynomialSecondDegreeFunction = new PolynomialSecondDegreeFunction(n, x, y);
            AbstractFunction polynomialThirdDegreeFunction = new PolynomialThirdDegreeFunction(n, x, y);
            AbstractFunction exponentialFunction = new ExponentialFunction(n, x, y);
            AbstractFunction logarithmicFunction = new LogarithmicFunction(n, x, y);
            AbstractFunction powerFunction = new PowerFunction(n, x, y);

            List<AbstractFunction> listOfFunctions = new ArrayList<>();
            listOfFunctions.add(linearFunction);
            listOfFunctions.add(polynomialSecondDegreeFunction);
            listOfFunctions.add(polynomialThirdDegreeFunction);
            listOfFunctions.add(exponentialFunction);
            listOfFunctions.add(logarithmicFunction);
            listOfFunctions.add(powerFunction);

            Graphics graphics = new Graphics(linearFunction, polynomialSecondDegreeFunction, polynomialThirdDegreeFunction,
                    exponentialFunction, logarithmicFunction, powerFunction);
            AbstractFunction betterFunction = listOfFunctions.get(0);

            for (AbstractFunction function : listOfFunctions) {
                function.approximate();
                if (Double.isNaN(function.getS())) {
                    System.err.println(function.getName() + " не может быть высчитана");
                    continue;
                }
                if (function.getSigma() < betterFunction.getSigma()) betterFunction = function;
                writer.write("\n" + function.toString());
                writer.write("Коэффициент a: " + function.getA());
                writer.write("Коэффициент b: " + function.getB());
                if (function instanceof PolynomialSecondDegreeFunction || function instanceof PolynomialThirdDegreeFunction) {
                    writer.write("Коэффициент c: " + function.getC());
                    if (function instanceof PolynomialThirdDegreeFunction)
                        writer.write("Коэффициент d: " + function.getD());
                }
                writer.write("Мера отклонения S = " + function.getS());
                if (function instanceof LinearFunction)
                    writer.write("Коэффициент корреляции Пирсона r = " + function.getR());
                writer.write("Среднеквадратическое отклонение \uD835\uDF39 = " + function.getSigma());
            }
            writer.write("\nНаилучшая аппроксимирующая функция: " + betterFunction.toString());
            writer.write("Ее среднеквадратическое отклонение \uD835\uDF39 = " + betterFunction.getSigma());

            graphics.drawFunction();

        } catch (IOException | NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException | NoSuchElementException exception) {
            System.err.println("Некорректно введенны данные!");
            System.exit(0);
        }
    }
}
