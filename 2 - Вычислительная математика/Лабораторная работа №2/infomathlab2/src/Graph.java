import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.awt.*;

public class Graph {
    public ComputingFunctional computingFunctional;
    public double[] xValues = new double[1000];
    public double[] yValues = new double[1000];
    public double[] yValuesPlus = new double[1000];
    public double[] yValuesMinus = new double[1000];

    public Graph(ComputingFunctional computingFunctional) {
        this.computingFunctional = computingFunctional;
    }

    public void drawFunction() {
        double leftBorder = -10;
        double rightBorder = 10;
        double step = (rightBorder - leftBorder) / 1000;
        xValues[0] = leftBorder;
        yValues[0] = computingFunctional.equationArgument(leftBorder);
        double currentX = xValues[0];
        for (int i = 1; i < 1000; i++) {
            currentX += step;
            xValues[i] = currentX;
            yValues[i] = computingFunctional.equationArgument(currentX);
        }
        XYChart chart = QuickChart.getChart("График", "X", "Y", "График функции", xValues, yValues);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }

    public void drawSystemOfFunctions() {
        double leftBorder = 0.1;
        double rightBorder = 9;
        double step = (rightBorder - leftBorder) / 1000;
        xValues[0] = leftBorder;
        yValues[0] = computingFunctional.getYFromFirstEquationFromSystem(leftBorder);
        yValuesPlus[0] = computingFunctional.getYFromSecondEquationFromSystem(leftBorder);
        yValuesMinus[0] = -computingFunctional.getYFromSecondEquationFromSystem(leftBorder);
        double currentX = xValues[0];
        for (int i = 1; i < 1000; i++) {
            currentX += step;
            xValues[i] = currentX;
            yValues[i] = computingFunctional.getYFromFirstEquationFromSystem(currentX);
            yValuesPlus[i] = computingFunctional.getYFromSecondEquationFromSystem(currentX);
            yValuesMinus[i] = -computingFunctional.getYFromSecondEquationFromSystem(currentX);
        }
        XYChart chart = QuickChart.getChart("График", "X", "Y", "График первой функции", xValues, yValues);
        chart.addSeries("График второй функции(y>0)", xValues, yValuesPlus).setMarkerColor(Color.RED).setLineColor(Color.RED);
        chart.addSeries("График второй функции(y<0)", xValues, yValuesMinus).setMarkerColor(Color.RED).setLineColor(Color.RED);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();

    }
}
