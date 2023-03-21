import java.io.IOException;

public class SimpleIterationFunctional {

    private Messenger messenger;
    private ComputingFunctional computingFunctional;
    private Writer writer;

    private String selectedEquation;
    private Double firstBorder;
    private Double secondBorder;
    private Double epsilon;
    private Double lambda;
    private Double q;

    public SimpleIterationFunctional(Messenger messenger, ComputingFunctional computingFunctional, Writer writer) {
        this.messenger = messenger;
        this.computingFunctional = computingFunctional;
        this.writer = writer;
    }


    public void mainIterationMethod() throws IOException {

        lambdaCalculation();

        // Проверка на сходимость

        if (q >= 0 && q < 1) {
            messenger.convergenceConditionIsMetMessage();
        } else {
            messenger.convergenceConditionIsNotMetMessage();
            System.exit(0);
        }


        writer.write("Метод простых итераций:");
        int iterationCounter = 0;
        Double xi = null;
        if (computingFunctional.equationArgument(firstBorder) * computingFunctional.equationSecondDerivative(firstBorder) > 0) {
            xi = firstBorder;
        } else if (computingFunctional.equationArgument(secondBorder) * computingFunctional.equationSecondDerivative(secondBorder) > 0) {
            xi = secondBorder;
        } else xi = findMax(false);
        while (true) {
            writer.write("Итерация № " + iterationCounter);
            writer.write("Xi = " + xi);
            Double xi_1 = computingFunctional.equationFiX(lambda, xi);
            writer.write("Xi+1 = " + xi_1);
            Double fi_xi_1 = computingFunctional.equationFiX(lambda, xi_1);
            writer.write("\uD835\uDF4B(Xi+1) = " + fi_xi_1);
            Double f_xi_1 = computingFunctional.equationArgument(xi_1);
            writer.write("f(Xi+1) = " + f_xi_1);
            Double fault = Math.abs(xi_1 - xi);
            writer.write("|Xi+1-Xi| = " + fault);

            if (q >= 0 && q <= 0.5) {
                if (fault <= getEpsilon() && (Math.abs(computingFunctional.equationArgument(xi_1)) <= getEpsilon())) {
                    writer.write("\nОтвет: " + xi_1);
                    writer.write("Количество итераций: " + iterationCounter);
                    writer.write("Значение функции в точке x = " + computingFunctional.equationArgument(xi_1));
                    writer.write("Fi'(a) = " + computingFunctional.equationTransformedDerivative(lambda, firstBorder));
                    writer.write("Fi'(b) = " + computingFunctional.equationTransformedDerivative(lambda, secondBorder));

                    return;
                }
            }

            if (q > 0.5 && q < 1) {
                if (fault <= ((1 - q) / q) * getEpsilon() && (Math.abs(computingFunctional.equationArgument(xi_1)) <= getEpsilon())) {
                    writer.write("\nОтвет: " + xi_1);
                    writer.write("Количество итераций: " + iterationCounter);
                    writer.write("Значение функции в точке x = " + computingFunctional.equationArgument(xi_1));
                    writer.write("Fi'(a) = " + computingFunctional.equationTransformedDerivative(lambda, firstBorder));
                    writer.write("Fi'(b) = " + computingFunctional.equationTransformedDerivative(lambda, secondBorder));

                    return;
                }
            }

            iterationCounter++;
            xi = xi_1;
        }
    }

    public void lambdaCalculation() {

        double maxOfDerivativeF = computingFunctional.equationDerivative(findMax(false));

        this.lambda = (-1.0) / maxOfDerivativeF;

        this.q = Math.abs(computingFunctional.equationTransformedDerivative(lambda, findMax(true)));

        System.out.println("q = " + q);
    }


    public double findMax(boolean transformed) {
        double a = firstBorder;
        double b = secondBorder;
        final double PHI = (1 + Math.sqrt(5)) / 2;


        double x1, x2;
        while (true) {
            x1 = b - ((b - a) / PHI);
            x2 = a + ((b - a) / PHI);

            if (transformed) {
                if (Math.abs(computingFunctional.equationTransformedDerivative(lambda, x1)) <= Math.abs(computingFunctional.equationTransformedDerivative(lambda, x2)))
                    a = x1;
                else
                    b = x2;
            } else {

                if (computingFunctional.equationDerivative(x1) <= computingFunctional.equationDerivative(x2))
                    a = x1;
                else
                    b = x2;
            }

            if (Math.abs(b - a) < 0.001)
                break;
        }
        return (a + b) / 2;
    }


    public String getSelectedEquation() {
        return selectedEquation;
    }

    public void setSelectedEquation(String selectedEquation) {
        this.selectedEquation = selectedEquation;
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public Double getFirstBorder() {
        return firstBorder;
    }

    public void setFirstBorder(Double firstBorder) {
        this.firstBorder = firstBorder;
    }

    public Double getSecondBorder() {
        return secondBorder;
    }

    public void setSecondBorder(Double secondBorder) {
        this.secondBorder = secondBorder;
    }

    public Double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }

}
