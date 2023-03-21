import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.util.Arrays;
import java.util.Vector;

public class CalculationOfStatisticalCharacteristics {
    private double[] values;
    private Vector<Double> x;
    private Vector<Integer> n;
    private Vector<Double> p;


    public CalculationOfStatisticalCharacteristics(double[] numbers) {
        this.values = numbers;
        this.x = new Vector<>();
        this.p = new Vector<>();
        this.n = new Vector<>();
    }

    public void getVariationSeries() {
        Arrays.sort(values);
        System.out.println("Вариационный ряд:");
        for (int i = 0; i < values.length; i++) {
            System.out.print(values[i] + " ");
        }
    }

    public void getExtremeValues() {
        System.out.println("\n\nЭкстремальные значения: \n" +
                "Минимум: " + values[0] + "\n" +
                "Максимум: " + values[values.length - 1] + "\n");
    }

    public void getScope() {
        System.out.println("Размах выборки: \n" + (values[values.length - 1] - values[0]) + '\n');
    }

    public void getExpectedValue() {

//        Вычисление xi
        for (int i = 0; i < values.length; i++) {
            if (x.isEmpty() || x.lastElement() != values[i]) {
                x.add(values[i]);
            }
        }

//        Вычисление ni
        int count = 0;
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < values.length; j++) {
                if (x.get(i) == values[j]) {
                    count++;
                }
            }
            n.add(count);
            count = 0;
        }

//      Вычисление pi
        for (int i = 0; i < x.size(); i++) {
            p.add((double) n.get(i) / (double) values.length);
        }

//      Вычисление мат.ожидания
        double expectedValue = 0.0;
        for (int i = 0; i < x.size(); i++) {
            expectedValue += x.get(i) * p.get(i);
        }
        System.out.println("Оценка математического ожидания:");
        System.out.println(expectedValue);


//      Вычисление дисперсии и СКО
        double dispersion = 0.0;
        for (int i = 0; i < x.size(); i++) {
            dispersion += x.get(i) * x.get(i) * p.get(i);
        }
        dispersion = dispersion - expectedValue * expectedValue;
        System.out.println("\nСреднеквадратическое отклонение:");
        System.out.println(Math.sqrt(dispersion));

    }

    public void getEmpiricalFunction() {
        System.out.println("\nЭмпиирическая функция распределения:");
        double counter = 0.0;
        for (int i = 0; i < x.size(); i++) {

            if (i == 0) System.out.println("x <= " + x.get(0) + ", F(x) = " + 0);
            else {
                counter += p.get(i - 1);
                System.out.println(x.get(i - 1) + " < x <= " + x.get(i) + ", F(x) = " + String.format("%.2f", counter));
            }

        }
        System.out.println(x.lastElement() + " < x, F(x) = " + (String.format("%.2f", counter + p.lastElement())));
    }

    public void drawEmpiricalFunctionGraphic() {
        double[] xValues = new double[1000];
        double step = (x.lastElement() + 1.5 - x.firstElement() - 0.5) / 1000;
        xValues[0] = x.firstElement() - 0.5;

        double currentX = xValues[0];
        for (int i = 0; i < 1000; i++) {
            currentX += step;
            xValues[i] = currentX;
        }
        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("График Эмпирической функции");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("P");
        double counter = p.get(0);
        Vector<Double> yValues = new Vector<>();
        Vector<Double> specificX = new Vector<>();
        for (int i = 0; i < x.size(); i++) {

            for (int j = 0; j < 1000; j++) {

                if ((i == 0) && (xValues[j] <= x.get(0))) {
                    specificX.add(xValues[j]);
                    yValues.add(0.0);
                } else if (i > 0) {
                    if (xValues[j] > x.get(i - 1) && xValues[j] <= x.get(i)) {
                        specificX.add(xValues[j]);
                        yValues.add(counter);
                    }
                }
            }


            chart.getStyler().setMarkerSize(3);
            if (i == 0) chart.addSeries("x <= " + x.get(0), specificX, yValues);

            if (i > 0) {
                counter += p.get(i);
                chart.addSeries(x.get(i - 1) + " < x <= " + x.get(i), specificX, yValues);
            }
            specificX.clear();
            yValues.clear();
        }
        for (int j = 0; j < 1000; j++) {
            if (xValues[j] > x.lastElement()) {
                specificX.add(xValues[j]);
                yValues.add(counter);
            }

        }

        chart.addSeries(x.lastElement() + " < x", specificX, yValues);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }

    public void drawFunctionHistogram() {
        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Гистограмма");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("ni/h");

        double h = (values[values.length - 1] - values[0]) / (1 + (Math.log(values.length) / Math.log(2)));
        double m = (int) Math.ceil(1 + (Math.log(values.length) / Math.log(2)));
        double primary_x = values[0] - h / 2;


        Vector<Double> xValues = new Vector<>();
        Vector<Double> yValues = new Vector<>();
        for (int i = 0; i < m; i++) {
            int s = 0;
            for (double value : values) {
                if (value >= primary_x && value < (primary_x + h)) {
                    s++;
                }
            }

            xValues.clear();
            yValues.clear();
            xValues.add(primary_x);
            xValues.add(primary_x + h);
            yValues.add((double) s / h);
            yValues.add((double) s / h);
            chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
            chart.getStyler().setMarkerSize(1);
            chart.addSeries("[" + primary_x + "; " + (primary_x + h) + "]", xValues, yValues).setLineWidth(2);
            System.out.println("\nИнтервал: ");
            System.out.println("[" + primary_x + "; " + (primary_x + h) + "]");
            System.out.println("Количество точек попавших в интервал: "+s);
            primary_x += h;

        }
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();


    }

    public void getFrequencyPolygon() {
        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Полигон частот");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("ni");

        double h = (values[values.length - 1] - values[0]) / (1 + (Math.log(values.length) / Math.log(2)));
        double m = (int) Math.ceil(1 + (Math.log(values.length) / Math.log(2)));
        double primary_x = values[0] - h / 2;


        Vector<Double> xValues = new Vector<>();
        Vector<Double> yValues = new Vector<>();
        for (int i = 0; i < m; i++) {
            int s = 0;
            for (double value : values) {
                if (value >= primary_x && value < (primary_x + h)) {
                    s++;
                }
            }

            xValues.add(primary_x + h / 2);
            yValues.add((double) s);
            primary_x += h;

        }
        chart.addSeries("Полигон частот", xValues, yValues).setLineWidth(1);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();


    }
}
