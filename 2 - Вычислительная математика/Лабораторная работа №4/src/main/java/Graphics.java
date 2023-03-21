import functions.AbstractFunction;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;

public class Graphics {

    private AbstractFunction linearFunction;
    private AbstractFunction polynomialSecondDegreeFunction;
    private AbstractFunction polynomialThirdDegreeFunction;
    private AbstractFunction exponentialFunction;
    private AbstractFunction logarithmicFunction;
    private AbstractFunction powerFunction;

    public Graphics(AbstractFunction linearFunction, AbstractFunction polynomialSecondDegreeFunction,
                    AbstractFunction polynomialThirdDegreeFunction, AbstractFunction exponentialFunction,
                    AbstractFunction logarithmicFunction, AbstractFunction powerFunction) {

        this.linearFunction = linearFunction;
        this.polynomialSecondDegreeFunction = polynomialSecondDegreeFunction;
        this.polynomialThirdDegreeFunction = polynomialThirdDegreeFunction;
        this.exponentialFunction = exponentialFunction;
        this.logarithmicFunction = logarithmicFunction;
        this.powerFunction = powerFunction;
    }


    public void drawFunction() {
        double[] xValues = new double[1000];
        double[] yValuesLinear = new double[1000];
        double[] yValuesPolynomialSecond = new double[1000];
        double[] yValuesPolynomialThird = new double[1000];
        double[] yValuesExponential = new double[1000];
        double[] yValuesLogarithm = new double[1000];
        double[] yValuesPowerFunction = new double[1000];
        double[] primaryXValues = linearFunction.getX();
        double[] primaryYValues = linearFunction.getY();


        double step = (linearFunction.getX()[linearFunction.getN() - 1] + 1.5 - linearFunction.getX()[0] - 0.5) / 1000;
        xValues[0] = linearFunction.getX()[0] - 0.5;
        yValuesLinear[0] = linearFunction.linearXArgument(xValues[0]);
        yValuesPolynomialSecond[0] = polynomialSecondDegreeFunction.linearXArgument(xValues[0]);
        yValuesPolynomialThird[0] = polynomialThirdDegreeFunction.linearXArgument(xValues[0]);
        yValuesExponential[0] = exponentialFunction.linearXArgument(xValues[0]);
        yValuesLogarithm[0] = logarithmicFunction.linearXArgument(xValues[0]);
        yValuesPowerFunction[0] = powerFunction.linearXArgument(xValues[0]);

        double currentX = xValues[0];
        for (int i = 1; i < 1000; i++) {
            currentX += step;
            xValues[i] = currentX;
            yValuesLinear[i] = linearFunction.linearXArgument(currentX);
            yValuesPolynomialSecond[i] = polynomialSecondDegreeFunction.linearXArgument(currentX);
            yValuesPolynomialThird[i] = polynomialThirdDegreeFunction.linearXArgument(currentX);
            yValuesExponential[i] = exponentialFunction.linearXArgument(currentX);
            yValuesLogarithm[i] = logarithmicFunction.linearXArgument(currentX);
            yValuesPowerFunction[i] = powerFunction.linearXArgument(currentX);
        }
        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Графики функций");
        chart.setXAxisTitle("X");

        chart.setYAxisTitle("Y");
        if (!Double.isNaN(linearFunction.getS())) {
            chart.addSeries(linearFunction.getName(), xValues, yValuesLinear).setMarkerColor(Color.YELLOW).setLineColor(Color.RED);
        }

        if (!Double.isNaN(polynomialSecondDegreeFunction.getS())) {
            chart.addSeries(polynomialSecondDegreeFunction.getName(), xValues, yValuesPolynomialSecond).setMarkerColor(Color.RED).setLineColor(Color.RED);
        }

        if (!Double.isNaN(polynomialThirdDegreeFunction.getS())) {
            chart.addSeries(polynomialThirdDegreeFunction.getName(), xValues, yValuesPolynomialThird).setMarkerColor(Color.ORANGE).setLineColor(Color.ORANGE);
        }

        if (!Double.isNaN(exponentialFunction.getS())) {
            chart.addSeries(exponentialFunction.getName(), xValues, yValuesExponential).setMarkerColor(Color.CYAN).setLineColor(Color.CYAN);
        }

        if (!Double.isNaN(logarithmicFunction.getS())) {
            chart.addSeries(logarithmicFunction.getName(), xValues, yValuesLogarithm).setMarkerColor(Color.PINK).setLineColor(Color.PINK);
        }

        if (!Double.isNaN(powerFunction.getS())) {
            chart.addSeries(powerFunction.getName(), xValues, yValuesPowerFunction).setMarkerColor(Color.GREEN).setLineColor(Color.GREEN);
        }

        chart.addSeries("Искомые точки", primaryXValues, primaryYValues).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.BLUE).setLineWidth(0);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }


}
