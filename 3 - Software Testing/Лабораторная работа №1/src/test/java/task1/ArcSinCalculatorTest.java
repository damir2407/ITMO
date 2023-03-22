package test.java.task1;

import main.java.task1.ArcSinCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class ArcSinCalculatorTest {
    static private final double DELTA = 0.1;

    @ParameterizedTest
    @ValueSource(doubles = {0.34, 0.6, 0.85})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при положительных значениях")
    public void positiveNumbersTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.34, -0.6, -0.82})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при отрицательных значениях")
    public void negativeNumbersTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0001, 10, 100.212})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при числах больших 1")
    public void numbersGreaterThanOneTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0001, -10, -100.212})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при числах меньших -1")
    public void numbersLessThanNegativeOneTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.9, 0.95, 0.97, 0.99})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при числах близких к 1")
    public void numbersCloseToOneTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.89, -0.9, -0.901})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при числах близких к -1")
    public void numbersCloseToNegativeOneTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.01, 0.05, 0.1})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при положительных числах близких к 0")
    public void positiveNumbersCloseToZeroTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -0.05, -0.1})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при отрицательных числах близких к 0")
    public void negativeNumbersCloseToZeroTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 1})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при границах области определения функции arcsin(x)")
    public void onePositiveNegativeTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @Test
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при 0")
    public void zeroTest() {
        Assertions.assertEquals(Math.asin(0), ArcSinCalculator.asin(0), DELTA, "результаты Math.asin() и степенного ряда различаются при значении 0");
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.5, 0.5})
    @DisplayName("Тест для проверки равности результата Math.asin() и степенного ряда при числах -0.5 и 0.5")
    public void positiveAndNegativePointFiveTest(double value) {
        Assertions.assertEquals(Math.asin(value), ArcSinCalculator.asin(value), DELTA, "результаты Math.asin() и степенного ряда различаются при значении " + value);
    }

    @Test
    @DisplayName("Тест для проверки корректности программы при параметре null")
    public void checkNull() {
        Double value = null;
        Assertions.assertThrows(NullPointerException.class, () -> ArcSinCalculator.asin(value), "NullPointerException!");
    }

}
