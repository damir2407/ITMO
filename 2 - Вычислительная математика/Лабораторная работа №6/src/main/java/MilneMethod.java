import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class MilneMethod {
    private Double a;
    private Double b;
    private Double h;
    private Double e;
    private Double firstValue;
    private Function function;
    private int n;
    private Graphics graphics;
    Map<Double, Double> TochnoeY = new TreeMap<>();

    public MilneMethod(Double a, Double b, Double h, Double e, Double firstValue, Function function, Graphics graphics) {
        this.a = a;
        this.b = b;
        this.h = h;
        this.e = e;
        this.firstValue = firstValue;
        this.function = function;
        this.n = (int) Math.round((b - a) / h);
        this.graphics = graphics;
    }


    public void solve(Map<Double, Double> rungeKuttaMap, Map<Double, Double> rungeRuleMap) {
        Vector<Double> xRungeKutta = new Vector<>();
        Vector<Double> yRungeKutta = new Vector<>();
        Vector<Double> rungeRuleValues = new Vector<>();

        Double scale = Math.pow(10, 5);

        int i = 0;
        int coeff = 0;
        int counter = 0;
        for (Double key : rungeKuttaMap.keySet()) {
            if (counter == 4) break;

            if (Math.round((a + h * coeff) * scale) / scale == key) {
                xRungeKutta.add(i, key);
                yRungeKutta.add(i, rungeKuttaMap.get(key));
                rungeRuleValues.add(i, rungeRuleMap.get(key));
                i++;
                coeff++;
                counter++;
            }
        }


        Double x = xRungeKutta.lastElement();

        Double yProgn = 0.0;
        Double yCorr = 0.0;

        for (int j = 4; j < n + 1; j++) {
            x = Math.round((x + h) * scale) / scale;
            yProgn = yRungeKutta.get(j - 4) + ((4.0 * h) / 3.0) * (2 * function.calculate(xRungeKutta.get(j - 3), yRungeKutta.get(j - 3))
                    - function.calculate(xRungeKutta.get(j - 2), yRungeKutta.get(j - 2))
                    + 2.0 * function.calculate(xRungeKutta.get(j - 1), yRungeKutta.get(j - 1)));


            yCorr = yRungeKutta.get(j - 2) + (h / 3.0) * (function.calculate(xRungeKutta.get(j - 2), yRungeKutta.get(j - 2))
                    + 4.0 * function.calculate(xRungeKutta.get(j - 1), yRungeKutta.get(j - 1)) + function.calculate(x, yProgn));

            while (Math.abs((yCorr - yProgn)) / 15 > e) {
                yProgn = yCorr;
                yCorr = yRungeKutta.get(j - 2) + (h / 3.0) * (function.calculate(xRungeKutta.get(j - 2), yRungeKutta.get(j - 2))
                        + 4.0 * function.calculate(xRungeKutta.get(j - 1), yRungeKutta.get(j - 1)) + function.calculate(x, yProgn));

            }
            xRungeKutta.add(x);
            yRungeKutta.add(yCorr);
            TochnoeY.put(x,function.exactValue(x));
            rungeRuleValues.add(Math.abs((yCorr - yProgn)) / 15 );
        }

        printTable(xRungeKutta, yRungeKutta, rungeRuleValues);
        graphics.drawFunction(xRungeKutta, yRungeKutta);
    }

    public void printTable(Vector<Double> xRungeKutta, Vector<Double> yRungeKutta, Vector<Double> rungeRuleValues) {
        JFrame frame = new JFrame("Многошаговый метод Милна:");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[][] data = new Object[xRungeKutta.size()][4];
        String[] columnNames = {
                "i",
                "Xi",
                "Yi",
                "Оценка правилом Рунге"
        };
        for (int i = 0; i < xRungeKutta.size(); i++) {
            data[i][0] = i;
            data[i][1] = xRungeKutta.get(i);
            data[i][2] = yRungeKutta.get(i);
            data[i][3] = rungeRuleValues.get(i);
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
    }


}
