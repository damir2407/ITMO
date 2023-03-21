public class Function {
    private int numberOfFunction;

    public Function() {
    }

    public double calculate(double x) {
        switch (numberOfFunction) {
            case 1:
                return Math.sin(x);
            case 2:
                return 6 * Math.pow(x, 2) - 2 * x + 3;
            case 3:
                return 2 * Math.pow(x, 2) - x + 9;
            default:
                break;
        }
        return 0.0;
    }


    public int getNumberOfFunction() {
        return numberOfFunction;
    }

    public void setNumberOfFunction(int numberOfFunction) {
        this.numberOfFunction = numberOfFunction;
    }
}
