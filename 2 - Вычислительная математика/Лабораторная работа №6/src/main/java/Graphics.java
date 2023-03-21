import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

public class Graphics {
    private Function function;

    public Graphics(Function function) {
        this.function = function;
    }


    public void drawFunction(Map<Double, Double> rungeKuttaMap) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> yExactValues = new ArrayList<>();

        for (Double key : rungeKuttaMap.keySet()) {
            xValues.add(key);
            yValues.add(rungeKuttaMap.get(key));
            yExactValues.add(function.exactValue(key));
        }


        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Одношаговый метод Рунге-Кутта 4-го порядка");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.addSeries("Полученное численное решение", xValues, yValues);
        chart.addSeries("Точное решение", xValues, yExactValues);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

    }


    public void drawFunction(Vector<Double> xRungeKutta, Vector<Double> yRungeKutta) {
        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> yExactValues = new ArrayList<>();

        xValues = xRungeKutta.stream().collect(Collectors.toList());
        yValues = yRungeKutta.stream().collect(Collectors.toList());

        for (int i = 0; i < xValues.size(); i++) {
            yExactValues.add(function.exactValue(xValues.get(i)));
        }

        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Многошаговый метод Милна");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.addSeries("Полученное численное решение", xValues, yValues);
        chart.addSeries("Точное решение", xValues, yExactValues);

        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

    }
}
