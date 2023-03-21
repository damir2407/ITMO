import java.io.IOException;

public class ChordFunctional {
    private Messenger messenger;
    private ComputingFunctional computingFunctional;
    private Writer writer;

    private String selectedEquation;
    private Double firstBorder;
    private Double secondBorder;
    private Double epsilon;


    public ChordFunctional(Messenger messenger, ComputingFunctional computingFunctional, Writer writer) {
        this.computingFunctional = computingFunctional;
        this.messenger = messenger;
        this.writer = writer;
    }


    public void mainChordMethod() throws IOException {
        Double a = null;
        Double b = null;
        Double x0 = null;
        int iteration_counter = 0;

        if (computingFunctional.equationArgument(firstBorder) * computingFunctional.equationSecondDerivative(firstBorder) > 0) {
            x0 = firstBorder;
            a = firstBorder;
            b = secondBorder;
        } else if (computingFunctional.equationArgument(secondBorder) * computingFunctional.equationSecondDerivative(secondBorder) > 0) {
            x0 = secondBorder;
            a = secondBorder;
            b = firstBorder;
        } else {
            messenger.convergenceConditionIsNotMetMessage();
            System.exit(0);
        }

        if (computingFunctional.equationArgument(firstBorder)*computingFunctional.equationArgument(secondBorder)<0){
            messenger.convergenceConditionIsMetMessage();
        }else {
            messenger.convergenceConditionIsNotMetMessage();
            System.exit(0);
        }

        writer.write("Метод хорд:");
        while (true) {
            writer.write("Итерация №" + iteration_counter);
            writer.write("a = " + a);
            writer.write("b = " + b);
            double x = (a * computingFunctional.equationArgument(b) - b * computingFunctional.equationArgument(a)) /
                    (computingFunctional.equationArgument(b) - computingFunctional.equationArgument(a));

            writer.write("x = " + x);
            writer.write("F(a) = " + computingFunctional.equationArgument(a));
            writer.write("F(b) = " + computingFunctional.equationArgument(b));
            writer.write("F(x) = " + computingFunctional.equationArgument(x));

            Double fault = Math.abs(x - x0);
            writer.write("|Xn-1-Xn| = " + fault);
            if (fault <= getEpsilon() && Math.abs(computingFunctional.equationArgument(x)) <= getEpsilon()) {
                writer.write("\nОтвет: " + x);
                writer.write("Количество итераций = " + iteration_counter);
                writer.write("Значение функции в точке x = "+computingFunctional.equationArgument(x));
                return;
            }

            if (computingFunctional.equationArgument(a) * computingFunctional.equationArgument(x) > 0) {
                a = x;
                x0 = a;
            }

            if (computingFunctional.equationArgument(b) * computingFunctional.equationArgument(x) > 0) {
                b = x;
                x0 = b;
            }

            iteration_counter++;
        }


    }


    public String getSelectedEquation() {
        return selectedEquation;
    }

    public void setSelectedEquation(String selectedEquation) {
        this.selectedEquation = selectedEquation;
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
