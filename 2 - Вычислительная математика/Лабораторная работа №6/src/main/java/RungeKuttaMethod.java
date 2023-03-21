import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;


public class RungeKuttaMethod {
    private Double a;
    private Double b;
    private Double h;
    private Double e;
    private Double firstValue;
    private Function function;
    private int n;
    private Graphics graphics;
    Map<Double, Double> rungeRuleValues;
    Map<Double, Double> TochnoeY = new TreeMap<>();


    public RungeKuttaMethod(Double a, Double b, Double h, Double e, Double firstValue, Function function, Graphics graphics) {
        this.a = a;
        this.b = b;
        this.h = h;
        this.e = e;
        this.firstValue = firstValue;
        this.function = function;
        this.n = calculateN(this.h);
        this.graphics = graphics;
        this.rungeRuleValues = new TreeMap<>();
    }

    public Map<Double, Double> solve() {
        Map<Double, Double> y1 = new TreeMap<>();
        Map<Double, Double> y2 = new TreeMap<>();
        Double scale = Math.pow(10, 5);

        double x = a;
        y1.put(x, firstValue);
        for (int i = 1; i < n + 1; i++) {
            double k1 = h * function.calculate(x, y1.get(x));
            double k2 = h * function.calculate(x + h / 2, y1.get(x) + k1 / 2);
            double k3 = h * function.calculate(x + h / 2, y1.get(x) + k2 / 2);
            double k4 = h * function.calculate(x + h, y1.get(x) + k3);
            y1.put(Math.round((x + h) * scale) / scale, (y1.get(x) + ((double) 1 / 6) * (k1 + 2 * k2 + 2 * k3 + k4)));
            x = Math.round((x + h) * scale) / scale;
        }

        while (true) {
            y2.clear();
            this.h = this.h / 2;
            this.n = calculateN(this.h);
            x = a;
            y2.put(x, firstValue);
            for (int i = 1; i < n + 1; i++) {
                double k1_r = h * function.calculate(x, y2.get(x));
                double k2_r = h * function.calculate(x + h / 2, y2.get(x) + k1_r / 2);
                double k3_r = h * function.calculate(x + h / 2, y2.get(x) + k2_r / 2);
                double k4_r = h * function.calculate(x + h, y2.get(x) + k3_r);
                y2.put(Math.round((x + h) * scale) / scale, (y2.get(x) + ((double) 1 / 6) * (k1_r + 2 * k2_r + 2 * k3_r + k4_r)));
                x = Math.round((x + h) * scale) / scale;
            }

            int counter = 0;
            for (Double key : y1.keySet()) {
                if (Math.abs(y2.get(key) - y1.get(key)) / 15 < e) {
                    counter++;
                    this.rungeRuleValues.put(key, Math.abs(y2.get(key) - y1.get(key)) / 15);
                    TochnoeY.put(key, function.exactValue(key));
                }
            }

            if (counter == y1.size()) {
                printTable(y1);
                graphics.drawFunction(y1);

                return y1;
            }
            this.rungeRuleValues.clear();
            y1.clear();
            y1 = y2;
        }

    }

    public int calculateN(Double h) {
        return (int) Math.round((this.b - this.a) / h);
    }

    public void printTable(Map<Double, Double> map) {
        JFrame frame = new JFrame("Одношаговый метод Рунге-Кутта четвертого порядка:");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Object[][] data = new Object[map.size()][5];
        String[] columnNames = {
                "i",
                "Xi",
                "Yi",
                "Оценка правилом Рунге",
                "Точное значение"
        };
        int i = 0;
        for (Double key : map.keySet()) {
            data[i][0] = i;
            data[i][1] = key;
            data[i][2] = map.get(key);
            data[i][3] = this.rungeRuleValues.get(key);
            data[i][4] = this.TochnoeY.get(key);
            i++;
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

    public Map<Double, Double> getRungeRuleValues() {
        return rungeRuleValues;
    }
}

