import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader(new Scanner(System.in));
        Function function = new Function();
        try {
            int inputType = reader.readInputType();

            if (inputType == 2)
                function.setNumberOfFunction(reader.readNumberOfFunction());


            int size = reader.readSize();
            double[] x = reader.readX(size);

            LagrangePolynomial lagrangePolynomial = new LagrangePolynomial(x, function);
            NewtonPolynomial newtonPolynomial = new NewtonPolynomial(x, function);


            if (inputType == 1) {
                double[] y = reader.readY(size);
                lagrangePolynomial.setY(y);
                newtonPolynomial.setY(y);
            }

            double XCoordinate = reader.readXCoordinate();
            lagrangePolynomial.setXCoordinate(XCoordinate);
            newtonPolynomial.setXCoordinate(XCoordinate);


            System.out.println("Многочлен Лагранжа: ");
            lagrangePolynomial.interpolation();
            System.out.println(lagrangePolynomial.getlAnswers());
            System.out.println("Ответ: L" + (x.length - 1) + "(" + XCoordinate + ")" + " = " + lagrangePolynomial.getInterpolationAnswer());

            System.out.println("\nМногочлен Ньютона:");
            double NewtonAnswer = newtonPolynomial.interpolation();
            if (XCoordinate <= x[x.length / 2]) {
                System.out.println("Ответ, полученный первой интерполяционной формулой Ньютона для интерполирования вперед: \n" + NewtonAnswer);
            } else {
                System.out.println("Ответ, полученный второй интерполяционной формулой Ньютона для интерполирования назад: \n" + NewtonAnswer);
            }

            System.out.println(newtonPolynomial.getUsedCoef());

            Graph graph = new Graph(function, lagrangePolynomial, newtonPolynomial, XCoordinate);

            if (inputType == 1) {
                graph.drawLagrange();
                graph.drawNewton();
            } else {
                graph.drawLagrangeWithFunction();
                graph.drawNewtonWithFunction();
            }
        } catch (NoSuchElementException | NumberFormatException exception) {
            System.out.println("Произошла ошибка!");
            System.exit(0);
        }
    }
}
