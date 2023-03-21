package functions;

public class ExponentialFunction extends AbstractFunction {

    public ExponentialFunction(int n, double[] x, double[] y) {
        super(n, x, y);
    }

    private double a;
    private double b;
    private double sigma;
    private double S = 0;
    private String name = "Экспоненциальная функция";

    @Override
    public void approximate() {
        double SX = 0;
        double SXX = 0;
        double SY = 0;
        double SXY = 0;

        for (int i = 0; i < getN(); i++) {
            SX += getX()[i];
            SXX += getX()[i] * getX()[i];
            SY += Math.log(getY()[i]);
            SXY += getX()[i] * Math.log(getY()[i]);
        }

        a = (SXX * SY - SX * SXY) / (SXX * getN() - SX * SX);
        b = (SXY * getN() - SX * SY) / (SXX * getN() - SX * SX);

        a = Math.pow(Math.E, a);

        for (int i = 0; i < getN(); i++) {
            S += Math.pow((linearXArgument(getX()[i]) - getY()[i]), 2);
        }
        sigma = Math.sqrt(S / getN());
    }

    @Override
    public double linearXArgument(double x) {
        return a * Math.pow(Math.E, b * x);
    }

    @Override
    public double getA() {
        return a;
    }

    @Override
    public double getB() {
        return b;
    }

    @Override
    public double getSigma() {
        return sigma;
    }

    @Override
    public double getS() {
        return S;
    }

    @Override
    public String toString() {
        return getName() + "\nФункция: " + a + "*e^(" + b + "*x)";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
