package functions;

public class PolynomialThirdDegreeFunction extends AbstractFunction {

    public PolynomialThirdDegreeFunction(int n, double[] x, double[] y) {
        super(n, x, y);
    }

    private double a;
    private double b;
    private double c;
    private double d;
    private double sigma;
    private double S = 0;
    private String name = "Полиномиальная функция 3-й степени";


    @Override
    public void approximate() {
        double XS = 0;
        double X2S = 0;
        double X3S = 0;
        double X4S = 0;
        double X5S = 0;
        double X6S = 0;

        double YS = 0;
        double YXS = 0;
        double YX2S = 0;
        double YX3S = 0;
        for (int i = 0; i < getN(); i++) {
            XS += getX()[i];
            X2S += Math.pow(getX()[i], 2);
            X3S += Math.pow(getX()[i], 3);
            X4S += Math.pow(getX()[i], 4);
            X5S += Math.pow(getX()[i], 5);
            X6S += Math.pow(getX()[i], 6);

            YS += getY()[i];
            YXS += getY()[i] * getX()[i];
            YX2S += getY()[i] * Math.pow(getX()[i], 2);
            YX3S += getY()[i] * Math.pow(getX()[i], 3);
        }

        double[][] firstMatrix = {{getN(), XS, X2S, X3S},
                {XS, X2S, X3S, X4S},
                {X2S, X3S, X4S, X5S},
                {X3S, X4S, X5S, X6S}};

        double[][] secondsMatrix = {{YS, XS, X2S, X3S},
                {YXS, X2S, X3S, X4S},
                {YX2S, X3S, X4S, X5S},
                {YX3S, X4S, X5S, X6S}};

        double[][] thirdMatrix = {{getN(), YS, X2S, X3S},
                {XS, YXS, X3S, X4S},
                {X2S, YX2S, X4S, X5S},
                {X3S, YX3S, X5S, X6S}};

        double[][] fourthMatrix = {{getN(), XS, YS, X3S},
                {XS, X2S, YXS, X4S},
                {X2S, X3S, YX2S, X5S},
                {X3S, X4S, YX3S, X6S}};

        double[][] fifthMatrix = {{getN(), XS, X2S, YS},
                {XS, X2S, X3S, YXS},
                {X2S, X3S, X4S, YX2S},
                {X3S, X4S, X5S, YX3S}};


        double delta = det(firstMatrix);
        double delta1 = det(secondsMatrix);
        double delta2 = det(thirdMatrix);
        double delta3 = det(fourthMatrix);
        double delta4 = det(fifthMatrix);

        a = delta4 / delta;
        b = delta3 / delta;
        c = delta2 / delta;
        d = delta1 / delta;

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
    public double getD() {
        return d;
    }

    @Override
    public double linearXArgument(double x) {
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
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
        return getName() + "\nФункция: " + a + "*x^3" + "+" + b + "*x^2" + "+" + c + "*x" + "+" + d;
    }

}
