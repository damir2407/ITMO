import java.util.Scanner;

public class Reader {
    private Scanner scanner;

    public Reader(Scanner scanner) {
        this.scanner = scanner;
    }

    public Double readH() {
        System.out.println("Введите шаг h:");
        return Double.parseDouble(scanner.nextLine().trim());
    }

    public Double readEpsilon() {
        System.out.println("Введите точность e:");
        return Double.parseDouble(scanner.nextLine().trim());
    }


}
