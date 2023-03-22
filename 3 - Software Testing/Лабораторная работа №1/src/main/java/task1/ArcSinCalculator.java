package main.java.task1;

public class ArcSinCalculator {
    public static double asin(double x) {
        if (x > 1 || x < -1) {
            return Double.NaN;
        }

        double numeratorCoeff = 1;
        double denominatorCoeff = 2;

        double e = 1;
        double n = 2;
        double arcsin = x;

        while (Math.abs(e) > 0.0001) {
            e = (numeratorCoeff * Math.pow(x, 2 * n - 1)) / (denominatorCoeff * (2 * n - 1));
            numeratorCoeff = numeratorCoeff * (2 * n - 1);
            denominatorCoeff = denominatorCoeff * 2 * n;
            arcsin = arcsin + e;
            n++;
        }
        return arcsin;
    }
}
