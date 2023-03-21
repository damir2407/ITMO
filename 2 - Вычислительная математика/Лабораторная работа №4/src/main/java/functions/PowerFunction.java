package functions;

public class PowerFunction extends AbstractFunction {

    public PowerFunction(int n, double[] x, double[] y) {
        super(n, x, y);
    }

    private double a;
    private double b;
    private double sigma;
    private double S = 0;
    private String name = "Степенная функция";

    @Override
    public void approximate() {
        double SX = 0;
        double SXX = 0;
        double SY = 0;
        double SXY = 0;

        for (int i = 0; i < getN(); i++) {
            SX += Math.log(getX()[i]);
            SXX += Math.log(getX()[i]) * Math.log(getX()[i]);
            SY += Math.log(getY()[i]);
            SXY += Math.log(getX()[i]) * Math.log(getY()[i]);
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
    public double getA() {
        return a;
    }

    @Override
    public double getB() {
        return b;
    }

    @Override
    public double linearXArgument(double x) {
        return a * Math.pow(x, b);
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
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getName() + "\nФункция: " + a + "*x^" + b;
    }


}
