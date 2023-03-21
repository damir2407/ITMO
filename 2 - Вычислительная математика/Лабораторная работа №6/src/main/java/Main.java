import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Решение ОДУ: y'=y+(1+x)y^2; Интервал: [1;10]; y0= -1 ");
        Reader reader = new Reader(new Scanner(System.in));
        Function function = new Function();
        Graphics graphics = new Graphics(function);

        Double a = 1.0;
        Double b = 1.5;
        Double firstValue = -1.0;

        Double h = reader.readH();
        Double e = reader.readEpsilon();

        RungeKuttaMethod rungeKuttaMethod = new RungeKuttaMethod(a, b, h, e, firstValue, function, graphics);
        MilneMethod milneMethod = new MilneMethod(a, b, h, e, firstValue, function, graphics);


        Map<Double, Double> rungeKuttaMap = rungeKuttaMethod.solve();
        milneMethod.solve(rungeKuttaMap, rungeKuttaMethod.getRungeRuleValues());

    }
}
