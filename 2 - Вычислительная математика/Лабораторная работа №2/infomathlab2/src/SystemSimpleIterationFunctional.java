import java.io.IOException;

public class SystemSimpleIterationFunctional {
    private Messenger messenger;
    private ComputingFunctional computingFunctional;
    private Writer writer;

    private Double firstBorderOfFirstValue;
    private Double secondBorderOfFirstValue;
    private Double firstBorderOfSecondValue;
    private Double secondBorderOfSecondValue;
    private Double epsilon;

    public SystemSimpleIterationFunctional(Messenger messenger, ComputingFunctional computingFunctional, Writer writer) {
        this.messenger = messenger;
        this.computingFunctional = computingFunctional;
        this.writer = writer;
    }

    public void mainIterationMethod() throws IOException {

        checkOnConvergence();
        Double x0_1 = secondBorderOfFirstValue;
        Double x0_2 = secondBorderOfSecondValue;


        Double x1_new;
        Double x2_new;


        int iteration_counter = 0;
        writer.write("Метод простых итераций:\n");

        while (true) {

            x1_new = computingFunctional.firstSystemEquationArgument(x0_1, x0_2);
            x2_new = computingFunctional.secondSystemEquationArgument(x0_1, x0_2);

            writer.write("Итерация № " + iteration_counter);
            writer.write("x1 = " + x1_new);
            writer.write("x2 =" + x2_new);

            Double fault1 = Math.abs(x1_new - x0_1);
            Double fault2 = Math.abs(x2_new - x0_2);

            writer.write("xi^k-xi^(k-1) = " + fault1);
            writer.write("xi^k-xi^(k-1) = " + fault2);

            if ((fault1 <= getEpsilon() && fault2 <= getEpsilon()) ||
                    Math.abs(computingFunctional.firstSystemEquationArgument(x1_new, x2_new)) <= getEpsilon() ||
                    Math.abs(computingFunctional.secondSystemEquationArgument(x1_new, x2_new)) <= getEpsilon()) {
                writer.write("Количество итераций: " + iteration_counter);
                return;
            }

            x0_1 = x1_new;
            x0_2 = x2_new;
            iteration_counter++;

        }

    }


    public void checkOnConvergence() {
        Double q1 = Math.abs(computingFunctional.f1x1(secondBorderOfFirstValue, secondBorderOfSecondValue));
        Double q2 = Math.abs(computingFunctional.f1x2(secondBorderOfFirstValue, secondBorderOfSecondValue));
        Double q3 = Math.abs(computingFunctional.f2x1(secondBorderOfFirstValue));
        Double q4 = Math.abs(computingFunctional.f2x2());

        if (((q1 + q3) < 1) && ((q2 + q4) < 1)) {
            messenger.convergenceConditionIsMetMessage();
        } else {
            messenger.convergenceConditionIsNotMetMessage();
            System.exit(1);
        }


    }


    public Double getEpsilon() {
        return epsilon;
    }

    public void setEpsilon(Double epsilon) {
        this.epsilon = epsilon;
    }

    public Double getFirstBorderOfFirstValue() {
        return firstBorderOfFirstValue;
    }

    public void setFirstBorderOfFirstValue(Double firstBorderOfFirstValue) {
        this.firstBorderOfFirstValue = firstBorderOfFirstValue;
    }

    public Double getSecondBorderOfFirstValue() {
        return secondBorderOfFirstValue;
    }

    public void setSecondBorderOfFirstValue(Double secondBorderOfFirstValue) {
        this.secondBorderOfFirstValue = secondBorderOfFirstValue;
    }

    public Double getFirstBorderOfSecondValue() {
        return firstBorderOfSecondValue;
    }

    public void setFirstBorderOfSecondValue(Double firstBorderOfSecondValue) {
        this.firstBorderOfSecondValue = firstBorderOfSecondValue;
    }

    public Double getSecondBorderOfSecondValue() {
        return secondBorderOfSecondValue;
    }

    public void setSecondBorderOfSecondValue(Double secondBorderOfSecondValue) {
        this.secondBorderOfSecondValue = secondBorderOfSecondValue;
    }
}
