import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;

public class Graph {
    private Function function;
    private LagrangePolynomial lagrangePolynomial;
    private NewtonPolynomial newtonPolynomial;
    private double XCoordinate;

    public Graph(Function function, LagrangePolynomial lagrangePolynomial, NewtonPolynomial newtonPolynomial, double XCoordinate) {
        this.function = function;
        this.lagrangePolynomial = lagrangePolynomial;
        this.newtonPolynomial = newtonPolynomial;
        this.XCoordinate = XCoordinate;
    }


    public void drawLagrange() {
        double[] xValues = new double[101];
        double[] yValues = new double[101];

        double[] primaryXCoordinate = new double[1];
        double[] computedY = new double[1];

        primaryXCoordinate[0] = XCoordinate;
        computedY[0] = lagrangePolynomial.interpolation();

        double step = (lagrangePolynomial.getX()[lagrangePolynomial.getX().length - 1] - lagrangePolynomial.getX()[0]) / 100;
        double currentX = lagrangePolynomial.getX()[0];

        for (int i = 0; i <= 100; i++) {
            xValues[i] = currentX;
            lagrangePolynomial.setXCoordinate(currentX);
            yValues[i] = lagrangePolynomial.interpolation();
            currentX += step;
        }

        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Многочлен Лагранжа");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.addSeries("Приблизительная функция", xValues, yValues).setMarker(SeriesMarkers.NONE);;
        chart.addSeries("Исходные точки", lagrangePolynomial.getX(), lagrangePolynomial.getY()).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.BLUE).setLineWidth(0);
        chart.addSeries("Значение в заданной точке", primaryXCoordinate, computedY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.GREEN).setLineColor(Color.GREEN);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }


    public void drawNewton() {
        double[] xValues = new double[11];
        double[] yValues = new double[11];

        double[] primaryXCoordinate = new double[1];
        double[] computedY = new double[1];

        primaryXCoordinate[0] = XCoordinate;
        computedY[0] = newtonPolynomial.interpolation();

        double step = (newtonPolynomial.getX()[newtonPolynomial.getX().length - 1] - newtonPolynomial.getX()[0]) / 10;
        double currentX = newtonPolynomial.getX()[0];


        for (int i = 0; i <= 10; i++) {
            xValues[i] = currentX;
            newtonPolynomial.setXCoordinate(currentX);
            yValues[i] = newtonPolynomial.interpolation();
            currentX += step;
        }

        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Многочлен Ньютона");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.addSeries("Приблизительная функция", xValues, yValues).setMarker(SeriesMarkers.NONE);
        chart.addSeries("Исходные точки", newtonPolynomial.getX(), newtonPolynomial.getY()).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.BLUE).setLineWidth(0);
        chart.addSeries("Значение в заданной точке", primaryXCoordinate, computedY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.GREEN).setLineColor(Color.GREEN);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }


    public void drawLagrangeWithFunction() {
        double[] xValues = new double[101];
        double[] yValues = new double[101];

        double[] primaryXCoordinate = new double[1];
        double[] computedY = new double[1];

        double[] xValuesFunction = new double[101];
        double[] yValuesFunction = new double[101];


        double step = (lagrangePolynomial.getX()[lagrangePolynomial.getX().length - 1] - lagrangePolynomial.getX()[0]) / (100);
        double currentX = lagrangePolynomial.getX()[0];


        primaryXCoordinate[0] = XCoordinate;
        computedY[0] = lagrangePolynomial.interpolation();

        for (int i = 0; i <= 100; i++) {
            xValues[i] = currentX;
            xValuesFunction[i] = currentX;
            lagrangePolynomial.setXCoordinate(currentX);
            yValues[i] = lagrangePolynomial.interpolation();
            yValuesFunction[i] = function.calculate(currentX);
            currentX += step;

        }

        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Многочлен Лагранжа");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.addSeries("Приблизительная функция", xValues, yValues).setMarker(SeriesMarkers.NONE);
        chart.addSeries("Точная функция", xValuesFunction, yValuesFunction).setMarker(SeriesMarkers.NONE);
        chart.addSeries("Исходные точки", lagrangePolynomial.getX(), lagrangePolynomial.getY()).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.BLUE).setLineWidth(0);
        chart.addSeries("Значение в заданной точке", primaryXCoordinate, computedY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.GREEN).setLineColor(Color.GREEN);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }


    public void drawNewtonWithFunction() {
        double[] xValues = new double[101];
        double[] yValues = new double[101];

        double[] primaryXCoordinate = new double[1];
        double[] computedY = new double[1];

        double[] xValuesFunction = new double[101];
        double[] yValuesFunction = new double[101];


        double step = (newtonPolynomial.getX()[newtonPolynomial.getX().length - 1] - newtonPolynomial.getX()[0]) / 100;
        double currentX = lagrangePolynomial.getX()[0];


        primaryXCoordinate[0] = XCoordinate;
        computedY[0] = newtonPolynomial.interpolation();

        for (int i = 0; i <= 100; i++) {
            xValues[i] = currentX;
            xValuesFunction[i] = currentX;
            newtonPolynomial.setXCoordinate(currentX);
            yValues[i] = newtonPolynomial.interpolation();
            yValuesFunction[i] = function.calculate(currentX);
            currentX += step;

        }

        XYChart chart = new XYChart(new XYChartBuilder());
        chart.setTitle("Многочлен Ньютона");
        chart.setXAxisTitle("X");
        chart.setYAxisTitle("Y");
        chart.addSeries("Приблизительная функция", xValues, yValues).setMarker(SeriesMarkers.NONE);;
        chart.addSeries("Точная функция", xValuesFunction, yValuesFunction).setMarker(SeriesMarkers.NONE);
        chart.addSeries("Исходные точки", newtonPolynomial.getX(), newtonPolynomial.getY()).setMarker(SeriesMarkers.CIRCLE).setMarkerColor(Color.BLUE).setLineWidth(0);
        chart.addSeries("Значение в заданной точке", primaryXCoordinate, computedY).setMarker(SeriesMarkers.DIAMOND).setMarkerColor(Color.GREEN).setLineColor(Color.GREEN);
        SwingWrapper swingWrapper = new SwingWrapper(chart);
        swingWrapper.displayChart();
    }
}
