import java.time.LocalDateTime;
import java.util.Date;

public class FirstLab {
    public static void main(String args[]) {

        int i, j;
        short[] d = new short[14];
        for (i = 0; i < 14; i++) {
            d[i] = (short) (17 - i);
        }
        float[] x = new float[14];
        for (i = 0; i < 14; i++) {
            x[i] = (float) (Math.random() * 28) - 15;
        }
        double[][] f = new double[14][14];
        for (i = 0; i < 14; i++) {
            for (j = 0; j < 14; j++) {
                if (d[i] == 13) {
                    f[i][j] = Math.tan(Math.pow(Math.pow(x[j], 2 * x[j]), (Math.pow(((2 / 3d - x[j]) / x[j]), 2) / 2)));
                } else if (d[i] == 5 || d[i] == 7 || d[i] == 10 || d[i] == 11 || d[i] == 14 || d[i] == 16 || d[i] == 17) {
                    f[i][j] = Math.pow(Math.E, x[j]);
                } else {
                    f[i][j] = Math.pow(Math.PI * (Math.pow(Math.PI * (Math.pow(Math.asin((x[j] - 1) / 28), (2 * Math.pow(Math.E, x[j])))), 2) + 1), 3);
                }
            }
        }
        for (i = 0; i < 14; i++) {
            for (j = 0; j < 14; j++) {
                System.out.printf("%-12.3f", f[i][j]);
            }
            System.out.println();
        }
    }
}

