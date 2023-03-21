package functions;

public abstract class AbstractFunction {
    private int n;
    private double[] x;
    private double[] y;
    private double S;
    private double r;
    private double sigma;
    private String name;
    private double a;
    private double b;
    private double c;
    private double d;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public abstract void approximate();

    public AbstractFunction(int n, double[] x, double[] y) {
        this.n = n;
        this.x = x;
        this.y = y;
    }

    public double det(double[][] A) {
        double res = 1, tmp;
        for (int i = 0, index = 0; i < A.length; index = ++i) {
            for (int j = i + 1; j < A.length; ++j)
                if (Math.abs(A[j][i]) > Math.abs(A[index][i]))
                    index = j;
            if (Math.abs(A[index][i]) <= 0.0) return 0.;
            if (index != i) res *= -1;
            for (int j = i; j < A.length; ++j) {
                tmp = A[index][j];
                A[index][j] = A[i][j];
                A[i][j] = tmp;
            }
            for (int j = i + 1; j < A.length; ++j) {
                tmp = A[j][i] / A[i][i];
                for (int k = i; k < A.length; ++k)
                    A[j][k] -= tmp * A[i][k];
            }
            res *= A[i][i];
        }
        return res;
    }

    @Override
    public String toString() {
        return "AbstractFunction{}";
    }

    public abstract double linearXArgument(double x);

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
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

    public double getS() {
        return S;
    }

    public String getName() {
        return name;
    }

    public double getR() {
        return r;
    }

    public double getSigma() {
        return sigma;
    }
}
