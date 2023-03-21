public class ComputingFunctional {

    private String selectedEquation;

    public Double equationArgument(Double x) {
        switch (selectedEquation) {
            case ("x^3-1.89x^2-2x+1.76"):
                return firstEquationArgument(x);
            case ("x^3-0.12x^2-1.475x+0.192"):
                return secondEquationArgument(x);
            case ("-0.5-x^2+cos(x)"):
                return thirdEquationArgument(x);
            default:
                return null;

        }
    }

    public Double equationDerivative(Double x) {
        switch (selectedEquation) {
            case ("x^3-1.89x^2-2x+1.76"):
                return firstEquationDerivative(x);
            case ("x^3-0.12x^2-1.475x+0.192"):
                return secondEquationDerivative(x);
            case ("-0.5-x^2+cos(x)"):
                return thirdEquationDerivative(x);
            default:
                return null;

        }
    }

    public Double equationSecondDerivative(Double x) {
        switch (selectedEquation) {
            case ("x^3-1.89x^2-2x+1.76"):
                return firstEquationSecondDerivative(x);
            case ("x^3-0.12x^2-1.475x+0.192"):
                return secondEquationSecondDerivative(x);
            case ("-0.5-x^2+cos(x)"):
                return thirdEquationSecondDerivative(x);
            default:
                return null;

        }
    }


    public Double equationTransformedDerivative(Double lambda, Double x) {
        switch (selectedEquation) {
            case ("x^3-1.89x^2-2x+1.76"):
                return firstEquationTransformedDerivative(lambda, x);
            case ("x^3-0.12x^2-1.475x+0.192"):
                return secondEquationTransformedDerivative(lambda, x);
            case ("-0.5-x^2+cos(x)"):
                return thirdEquationTransformedDerivative(lambda, x);
            default:
                return null;

        }
    }


    public Double equationFiX(Double lambda, Double x) {
        switch (selectedEquation) {
            case ("x^3-1.89x^2-2x+1.76"):
                return firstEquationFiX(lambda, x);
            case ("x^3-0.12x^2-1.475x+0.192"):
                return secondEquationFiX(lambda, x);
            case ("-0.5-x^2+cos(x)"):
                return thirdEquationFiX(lambda, x);
            default:
                return null;

        }
    }


    //для первого уравнения
    public Double firstEquationArgument(Double x) {
        return (Math.pow(x, 3) - 1.89 * Math.pow(x, 2) - 2 * x + 1.76);
    }

    public Double firstEquationDerivative(Double x) {
        return (3.0 * Math.pow(x, 2) - 3.78 * x - 2);
    }

    public Double firstEquationSecondDerivative(Double x) {
        return (6 * x - 3.78 * x);
    }

    public Double firstEquationTransformedDerivative(Double lambda, Double x) {
        return 1 + lambda * firstEquationDerivative(x);
    }

    public Double firstEquationFiX(Double lambda, Double x) {
        return x + lambda * firstEquationArgument(x);
    }


    //для второго уравнения
    public Double secondEquationArgument(Double x) {
        return (Math.pow(x, 3) - 0.12 * Math.pow(x, 2) - 1.475 * x + 0.192);
    }

    public Double secondEquationDerivative(Double x) {
        return (3 * Math.pow(x, 2) - 0.24 * x - 1.475);
    }

    public Double secondEquationSecondDerivative(Double x) {
        return (6 * x - 0.24);
    }

    public Double secondEquationTransformedDerivative(Double lambda, Double x) {
        return 1 + lambda * secondEquationDerivative(x);
    }

    public Double secondEquationFiX(Double lambda, Double x) {
        return x + lambda * secondEquationArgument(x);
    }


    //для третьего уравнения
    public Double thirdEquationArgument(Double x) {
        return (-0.5 - Math.pow(x, 2) + Math.cos(x));
    }

    public Double thirdEquationDerivative(Double x) {
        return (-2 * x - Math.sin(x));
    }

    public Double thirdEquationSecondDerivative(Double x) {
        return (-2 - Math.cos(x));
    }

    public Double thirdEquationTransformedDerivative(Double lambda, Double x) {
        return 1 + lambda * thirdEquationDerivative(x);
    }


    //  для системы
    public Double firstSystemEquationArgument(Double x1, Double x2) {
        return Math.sqrt((x1 * (x2 + 5) - 1) / 2);
    }

    public Double secondSystemEquationArgument(Double x1, Double x2) {
        if (x2 > 0) return Math.sqrt(x1 + 3 * Math.log10(x1));
        return -Math.sqrt(x1 + 3 * Math.log10(x1));
    }

    public Double getYFromFirstEquationFromSystem(Double x1) {
        return (2 * Math.pow(x1, 2) - 5 * x1 + 1) / x1;
    }

    public Double getYFromSecondEquationFromSystem(Double x1) {
        return Math.sqrt(x1 + 3 * Math.log10(x1));
    }

    public Double f1x1(Double x1, Double x2) {
        return (x2 + 5) / (4 * Math.sqrt(((x1 + 0.2) * (x2 + 5) - 1) / 2));
    }

    public Double f1x2(Double x1, Double x2) {
        return x1 / (4 * Math.sqrt((x1 * (x2 + 5) - 1) / 2));
    }

    public Double f2x1(Double x1) {
        return ((1 + (3 * 0.43429) / x1)) / (2 * Math.sqrt(x1 + 3 * Math.log10(x1)));
    }

    public Double f2x2() {
        return 0.0;
    }


    public Double thirdEquationFiX(Double lambda, Double x) {
        return x + lambda * thirdEquationArgument(x);
    }


    public String getSelectedEquation() {
        return selectedEquation;
    }

    public void setSelectedEquation(String selectedEquation) {
        this.selectedEquation = selectedEquation;
    }
}
