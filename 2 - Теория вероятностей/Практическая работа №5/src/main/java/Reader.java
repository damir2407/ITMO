import java.util.Scanner;

public class Reader {
    private Scanner scanner;

    public Reader(Scanner scanner) {
        this.scanner = scanner;
    }

    public double[] readNumbers() {
        double[] x = new double[20];
        for (int i = 0; i < 20; i++) {
            x[i] = scanner.nextDouble();
        }
        return x;
    }
}
