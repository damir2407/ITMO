package methods;

public abstract class AbstractMethod {
    private String numberOfFunction;
    private Double a;
    private Double b;
    private Double accuracy;


    public AbstractMethod(String numberOfFunction, Double a, Double b, Double accuracy) {
        this.numberOfFunction = numberOfFunction;
        this.a = a;
        this.b = b;
        this.accuracy = accuracy;
    }


    public abstract void mainMethod();

    public Double functionEvaluation(Double x) {
        switch (numberOfFunction) {
            case "1":
                return firstFunctionEvaluation(x);
            case "2":
                return secondFunctionEvaluation(x);
            case "3":
                return thirdFunctionEvaluation(x);
            default:
                break;
        }
        return null;
    }

    public Double firstFunctionEvaluation(Double x) {
        return -2 * Math.pow(x, 3) - 4 * Math.pow(x, 2) + 8 * x - 4;
    }

    public Double secondFunctionEvaluation(Double x) {
        return -2 * Math.pow(x, 3) - 3 * Math.pow(x, 2) + x + 5;
    }

    public Double thirdFunctionEvaluation(Double x) {
        return 3 * Math.pow(x, 3) + 5 * Math.pow(x, 2) + 3 * x - 6;
    }


    public Double functionNewtonLeibniz() {
        switch (numberOfFunction) {
            case "1":
                return firstFunctionNewtonLeibniz();
            case "2":
                return secondFunctionNewtonLeibniz();
            case "3":
                return thirdFunctionNewtonLeibniz();
            default:
                break;
        }
        return null;
    }


    public Double firstFunctionAntiderivative(Double x) {
        return -Math.pow(x, 4) / 2 - (4 * Math.pow(x, 3) / 3) + 4 * Math.pow(x, 2) - 4 * x;
    }

    public Double secondFunctionAntiderivative(Double x) {
        return -Math.pow(x, 4) / 2 - Math.pow(x, 3) + Math.pow(x, 2) / 2 + 5 * x;
    }

    public Double thirdFunctionAntiderivative(Double x) {
        return 3 * Math.pow(x, 4) / 4 + 5 * Math.pow(x, 3) / 3 + 3 * Math.pow(x, 2) / 2 - 6 * x;
    }


    public Double firstFunctionNewtonLeibniz() {
        return firstFunctionAntiderivative(b) - firstFunctionAntiderivative(a);
    }

    public Double secondFunctionNewtonLeibniz() {
        return secondFunctionAntiderivative(b) - secondFunctionAntiderivative(a);
    }

    public Double thirdFunctionNewtonLeibniz() {
        return thirdFunctionAntiderivative(b) - thirdFunctionAntiderivative(a);
    }

    public String getNumberOfFunction() {
        return numberOfFunction;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public Double getAccuracy() {
        return accuracy;
    }
}
