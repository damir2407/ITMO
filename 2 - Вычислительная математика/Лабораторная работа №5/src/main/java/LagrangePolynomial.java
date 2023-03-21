public class LagrangePolynomial {
    private double[] x;
    private double[] y;
    private double XCoordinate;
    private Function function;
    private String lstr = "";
    private String interpolationAnswer = "";

    public LagrangePolynomial(double[] x, Function function) {
        this.x = x;
        this.function = function;
    }


    public double interpolation() {


        double L = 0;
        for (int i = 0; i < x.length; i++) {
            L += l(i, y[i]);
        }

        interpolationAnswer = L+" ";
        return L;

    }


    public double l(int indexOfL, double currentY) {
        double l = 1;
        for (int i = 0; i < x.length; i++) {
            if (i != indexOfL) {
                l *= (XCoordinate - x[i]) / (x[indexOfL] - x[i]);
            }
        }
        l = l * currentY;
        lstr += ("l" + indexOfL + " = " + l + "\n");
        return l;
    }

    public void checkOnEmpty() {
        if (y == null) {
            this.y = new double[x.length];
            for (int i = 0; i < x.length; i++) {
                y[i] = function.calculate(x[i]);
            }
        }
    }

    public double[] getX() {
        return x;
    }

    public void setX(double[] x) {
        this.x = x;
    }

    public double[] getY() {
        return y;
    }

    public void setY(double[] y) {
        this.y = y;
    }

    public double getXCoordinate() {
        return XCoordinate;
    }

    public void setXCoordinate(double XCoordinate) {
        this.XCoordinate = XCoordinate;
        checkOnEmpty();
    }

    public String getlAnswers() {
        return lstr;
    }

    public String getInterpolationAnswer() {
        return interpolationAnswer;
    }
}
