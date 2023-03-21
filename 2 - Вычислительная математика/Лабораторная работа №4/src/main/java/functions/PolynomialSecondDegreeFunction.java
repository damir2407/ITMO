package functions;

public class PolynomialSecondDegreeFunction extends AbstractFunction {

    public PolynomialSecondDegreeFunction(int n, double[] x, double[] y) {
        super(n, x, y);
    }

    private double a;
    private double b;
    private double c;
    private double sigma;
    private double S = 0;
    private String name = "Полиномиальная функция 2-й степени";


    @Override
    public void approximate() {
        double X4S = 0;
        double X3S = 0;
        double X2S = 0;
        double X2YS = 0;
        double XS = 0;
        double XYS = 0;
        double YS = 0;

        for (int i = 0; i < getN(); i++) {
            X4S += Math.pow(getX()[i], 4);
            X3S += Math.pow(getX()[i], 3);
            X2S += Math.pow(getX()[i], 2);
            X2YS += Math.pow(getX()[i], 2) * getY()[i];
            XS += getX()[i];
            XYS += getX()[i] * getY()[i];
            YS += getY()[i];
        }
        double[][] firstMatrix = {{getN(), XS, X2S},
                {XS, X2S, X3S},
                {X2S, X3S, X4S}};


        double[][] secondsMatrix = {{YS, XS, X2S},
                {XYS, X2S, X3S},
                {X2YS, X3S, X4S}};


        double[][] thirdMatrix = {{getN(), YS, X2S},
                {XS, XYS, X3S},
                {X2S, X2YS, X4S}};


        double[][] fourthMatrix = {{getN(), XS, YS},
                {XS, X2S, XYS},
                {X2S, X3S, X2YS}};


        double delta = det(firstMatrix);
        double delta1 = det(secondsMatrix);
        double delta2 = det(thirdMatrix);
        double delta3 = det(fourthMatrix);

        a = delta3 / delta;
        b = delta2 / delta;
        c = delta1 / delta;

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
    public double getC() {
        return c;
    }

    @Override
    public double linearXArgument(double x) {
        return a * Math.pow(x, 2) + b * x + c;
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
        return getName() + "\nФункция: " + a + "*x^2" + "+" + b + "*x" + "+" + c;
    }

}
