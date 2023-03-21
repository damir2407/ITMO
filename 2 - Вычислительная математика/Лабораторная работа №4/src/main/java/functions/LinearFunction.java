package functions;

public class LinearFunction extends AbstractFunction {

    public LinearFunction(int n, double[] x, double[] y) {
        super(n, x, y);
    }

    private double a;
    private double b;
    private double sigma;
    private double S = 0;
    private double r;
    private String name = "Линейная функция";


    @Override
    public void approximate() {
        double SX = 0;
        double SXX = 0;
        double SY = 0;
        double SXY = 0;
        double xAverage = 0;
        double yAverage = 0;

        for (int i = 0; i < getN(); i++) {
            SX += getX()[i];
            SXX += getX()[i] * getX()[i];
            SY += getY()[i];
            SXY += getX()[i] * getY()[i];
        }
        xAverage = SX / getN();
        yAverage = SY / getN();

        a = (SXY * getN() - SX * SY) / (SXX * getN() - SX * SX);
        b = (SXX * SY - SX * SXY) / (SXX * getN() - SX * SX);

        double numerator = 0;
        double firstPartDenominator = 0;
        double secondPartDenominator = 0;
        for (int i = 0; i < getN(); i++) {
            S += Math.pow((linearXArgument(getX()[i]) - getY()[i]), 2);
            numerator += (getX()[i] - xAverage) * (getY()[i] - yAverage);
            firstPartDenominator += Math.pow((getX()[i] - xAverage), 2);
            secondPartDenominator += Math.pow((getY()[i] - yAverage), 2);
        }
        r = numerator / Math.sqrt(firstPartDenominator * secondPartDenominator);
        sigma = Math.sqrt(S / getN());


    }



    @Override
    public double linearXArgument(double x) {
        return a * x + b;
    }

    @Override
    public String toString() {
        return getName() + "\nФункция: " + a + "*x" + "+" + b;
    }


    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    @Override
    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    @Override
    public double getS() {
        return S;
    }

    @Override
    public double getR() {
        return r;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
