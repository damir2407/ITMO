public class Messenger {

    public void selectEquationOrSystemMessage() {
        System.out.println("Введите цифру 1 для решения нелинейного уравнения\nВведите цифру 2 для решение системы нелинейных уравнений");
    }

    public void selectEquationMessage() {
        System.out.println("Выберите уравнение:\n" +
                "Введите цифру 1 для решения уравнения x^3-1.89x^2-2x+1.76\n" +
                "Введите цифру 2 для решения уравнения x^3-0.12x^2-1.475x+0.192\n" +
                "Введите цифру 3 для решения уравнения -0.5-x^2+cos(x)");
    }

    public void incorrectDataMessage() {
        System.out.println("Неверно введены данные, попробуйте заново");
    }

    public void selectSolutionMethodMessage() {
        System.out.println("Выберите метод для решения нелинейного уравнения:\n" +
                "Введите цифру 1 для решения методом хорд\n" +
                "Введите цифру 2 для решения методом простых итераций");
    }

    public void selectInputMethodMessage() {
        System.out.println("Выберите метод ввода значений:\nВведите цифру 1 в консоль для ввода с клавиатуры или цифру 2 для ввода с файла");
    }

    public void selectedInputMethodIsConsoleMessage() {
        System.out.println("Вы выбрали возможность ввода данных с КЛАВИАТУРЫ");
    }

    public void selectedInputMethodIsFileMessage() {
        System.out.println("Вы выбрали возможность ввода данных с ФАЙЛА");
    }

    public void inputFilePathMessage() {
        System.out.println("Введите расположение вашего файла:");
    }

    public void inputMethodNotFoundMessage() {
        System.out.println("Данного метода ввода/вывода данных не существует!");
    }

    public void pathNotFoundMessage() {
        System.out.println("Файл по указанному расположению не найден!");
    }

    public void inputIntervalBoundariesMessage() {
        System.out.println("Введите границы интервала в формате [a;b]:");
    }

    public void inputAccuracyMessage() {
        System.out.println("Введите точность:");
    }

    public void convergenceConditionIsMetMessage() {
        System.out.println("Условие сходимости соблюдается!");
    }

    public void convergenceConditionIsNotMetMessage() {
        System.out.println("Условие сходимости НЕ соблюдается!");
    }

    public void selectWriteMethodMessage() {
        System.out.println("Выберите метод вывода значений:\nВведите цифру 1 для вывода ответа в консоль или цифру 2 для вывода в файл");
    }


    public void selectedOutputMethodIsConsoleMessage() {
        System.out.println("Вы выбрали возможность вывода ответа в КОНСОЛЬ");
    }

    public void selectedOutputMethodIsFileMessage() {
        System.out.println("Вы выбрали возможность вывода ответа в ФАЙЛ");
    }

    public void selectedSystemMessage() {
        System.out.println("Выбранная система уравнений: \n" +
                "{2*x1^2-x1*x2-5x1+1=0\n" +
                "{x1+3*lg(x1)-x2^2=0");
    }
}
