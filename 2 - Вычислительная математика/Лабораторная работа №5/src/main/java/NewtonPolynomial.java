public class NewtonPolynomial {
    private double[] x;
    private double[] y;
    private double XCoordinate;
    private Function function;
    private boolean b = true;
    private boolean c = true;
    private String usedCoef = "В формуле использовал";

    public NewtonPolynomial(double[] x, Function function) {
        this.x = x;
        this.function = function;
    }


    public double interpolation() {

        double h = x[1] - x[0];
        double[][] deltaMatrix = new double[x.length][x.length];
        for (int i = 0; i < x.length; i++) {
            deltaMatrix[i][0] = y[i];
        }

        for (int i = 1; i < x.length; i++) {
            for (int j = 0; j < (x.length - i); j++) {
                deltaMatrix[j][i] = deltaMatrix[j + 1][i - 1] - deltaMatrix[j][i - 1];
            }
        }

        if (b) {
            System.out.println("Таблица конечных разностей: ");
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < (x.length - i); j++) {
                    System.out.print(deltaMatrix[i][j] + " ");
                }
                System.out.println();
            }
            b = false;
        }


        if (XCoordinate <= x[x.length / 2]) {
            return (firstInterpolationFunction(deltaMatrix, h));
        } else {
            return (secondInterpolationFunction(deltaMatrix, h));
        }

    }

    public double firstInterpolationFunction(double[][] deltaMatrix, double h) {
        double answer;
        int x0 = x.length - 1;
        for (int i = 0; i < x.length; i++) {
            if (XCoordinate <= x[i]) {
                x0 = i - 1;
                if (x0 < 0) x0 = 0;
                break;
            }
        }
        double t = (XCoordinate - x[x0]) / h;
        answer = y[x0];
        int counter = 0;
        for (int i = 1; i < x.length; i++) {
            double new_t = t;
            for (int j = 1; j < i; j++) {
                new_t *= t - j;
            }
            answer += (new_t * deltaMatrix[x0][i]) / getFactorial(i);

            if (c) {
                usedCoef += "Δy" + counter + " = " + deltaMatrix[x0][i] + '\n';
            }
        }
        c = false;
        return answer;
    }

    public double secondInterpolationFunction(double[][] deltaMatrix, double h) {
        double answer;
        double t = (XCoordinate - x[x.length - 1]) / h;
        answer = y[x.length - 1];
        int counter = 0;
        for (int i = 1; i < x.length; i++) {

            double new_t = t;
            for (int j = 1; j < i; j++) {
                new_t *= t + j;
            }

            answer += (new_t * deltaMatrix[x.length - i - 1][i]) / getFactorial(i);

            if (c) {
                usedCoef += "Δy" + counter + " = " + deltaMatrix[x.length - i - 1][i] + '\n';
            }
        }
        c = false;
        return answer;
    }

    public double getFactorial(int num) {
        double factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public void checkOnEmpty() {
        if (y == null) {
            this.y = new double[x.length];
            for (int i = 0; i < x.length; i++) {
                y[i] = function.calculate(x[i]);
            }
        }
    }

    public String getUsedCoef() {
        return usedCoef;
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
}
