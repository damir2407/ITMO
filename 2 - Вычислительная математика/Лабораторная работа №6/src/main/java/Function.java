public class Function {

    public Double calculate(double x, double y) {
        return y + (1 + x) * Math.pow(y, 2);
    }

    public Double exactValue(double x) {
        return (-1 / x);
    }
}
