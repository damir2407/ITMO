package methods;

import java.math.BigDecimal;

public class RectangleMethod extends AbstractMethod {

    public RectangleMethod(String numberOfFunction, Double a, Double b, Double accuracy) {
        super(numberOfFunction, a, b, accuracy);
    }


    @Override
    public void mainMethod() {
        Integer n = 4;
        Double IAverage;
        Double IRight;
        Double ILeft;
        Double IAveragePrevious = Double.MAX_VALUE;
        Double IRightPrevious = Double.MAX_VALUE;
        Double ILeftPrevious = Double.MAX_VALUE;
        while (true) {
            IRight = 0.0;
            IAverage = 0.0;
            ILeft = 0.0;
            Double h = (getB() - getA()) / n;
            for (int i = 1; i <= n; i++) {
                IRight = IRight + functionEvaluation(getA() + i * h);
                ILeft = ILeft + functionEvaluation(getA() + (i - 1) * h);
                IAverage = IAverage + functionEvaluation(((getA() + (i - 1) * h) + (getA() + (i) * h)) / 2);
            }
            IAverage = IAverage * h;
            IRight = IRight * h;
            ILeft = ILeft * h;
            if ((Math.abs(IAveragePrevious - IAverage) / 3 <= getAccuracy()
                    && Math.abs(IRightPrevious - IRight) / 3 <= getAccuracy()
                    && Math.abs(ILeftPrevious - ILeft) / 3 <= getAccuracy())) break;
            IAveragePrevious = IAverage;
            IRightPrevious = IRight;
            ILeftPrevious = ILeft;
            n = n * 2;

        }

        System.out.println("Ответ полученный методом средних прямоугольников: " + IAverage);
        System.out.println("Ответ полученный методом правых прямоугольников: " + IRight);
        System.out.println("Ответ полученный методом левых прямоугольников: " + ILeft + "\n");

        System.out.println("Оценка погрешности правилом Рунге: \n" +
                "Оценка погрешности для метода средних прямоугольников: " + Math.abs((IAveragePrevious - IAverage) / 3) + "\n" +
                "Оценка погрешности для метода правых прямоугольников: " + Math.abs((IRightPrevious - IRight) / 3) + "\n" +
                "Оценка погрешности для метода левых прямоугольников: " + Math.abs((ILeftPrevious - ILeft) / 3) + "\n");

        System.out.println("Число разбиения интервала: " + n + "\n");

        System.out.println("Точное значение интегралла методом Ньютона Лейбница = " + functionNewtonLeibniz());
        System.out.println("Погрешность в вычислении интеграла составляет:\n" +
                "ΔIсред = I-Iсред = " + Math.abs(functionNewtonLeibniz() - IAverage) + "\n" +
                "ΔIлев = I-Iлев = " + Math.abs(functionNewtonLeibniz() - ILeft) + "\n" +
                "ΔIправ = I-Iправ = " + Math.abs(functionNewtonLeibniz() - IRight) + "\n");

        System.out.println(BigDecimal.valueOf(Double.parseDouble("6.457169732243528E-8")).toPlainString());

    }

}
