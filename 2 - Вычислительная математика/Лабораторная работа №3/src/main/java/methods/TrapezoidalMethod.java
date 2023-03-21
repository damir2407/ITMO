package methods;

public class TrapezoidalMethod extends AbstractMethod {

    public TrapezoidalMethod(String numberOfFunction, Double a, Double b, Double accuracy) {
        super(numberOfFunction, a, b, accuracy);
    }


    @Override
    public void mainMethod() {
        Integer n = 4;

        Double answer;
        Double answerPrevious = Double.MAX_VALUE;


        while (true) {
            answer = 0.0;
            Double h = (getB() - getA()) / n;
            for (int i = 1; i <= n - 1; i++) {
                answer += functionEvaluation(getA() + i * h);
            }
            answer = h * (answer + ((functionEvaluation(getB()) + functionEvaluation(getA())) / 2));
            if ((Math.abs((answerPrevious - answer) / 3) <= getAccuracy())) break;

            if (n > 1000000) {
                System.out.println("Количество разбиений превысило отметку в 1.000.000!");
                break;
            }

            answerPrevious = answer;
            n = n * 2;


        }

        System.out.println("Ответ полученный методом трапеций: " + answer + "\n");

        System.out.println("Оценка погрешности правилом Рунге: \n" +
                "Оценка погрешности правилом Рунге для метода трапеций: " + Math.abs((answerPrevious - answer) / 3) + "\n");

        System.out.println("Число разбиения интервала: " + n + "\n");

        System.out.println("Точное значение интегралла методом Ньютона Лейбница = " + functionNewtonLeibniz());
        System.out.println("Погрешность в вычислении интеграла составляет:\n" +
                "ΔI =I-Iтрап = " + Math.abs(functionNewtonLeibniz() - answer));
    }

}

