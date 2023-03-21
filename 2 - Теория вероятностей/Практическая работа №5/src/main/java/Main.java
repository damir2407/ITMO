import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String path = "C:\\Users\\User\\Desktop\\Лабораторные работы\\2 курс\\2 семестр\\Теория вероятностей\\Практическая работа №5\\src\\main\\resources\\input.txt";
            Reader reader = new Reader(new Scanner(new File(path)));
            CalculationOfStatisticalCharacteristics calculationOfStatisticalCharacteristics = new CalculationOfStatisticalCharacteristics(reader.readNumbers());
            calculationOfStatisticalCharacteristics.getVariationSeries();
            calculationOfStatisticalCharacteristics.getExtremeValues();
            calculationOfStatisticalCharacteristics.getScope();
            calculationOfStatisticalCharacteristics.getExpectedValue();
            calculationOfStatisticalCharacteristics.getEmpiricalFunction();
            calculationOfStatisticalCharacteristics.drawEmpiricalFunctionGraphic();
            calculationOfStatisticalCharacteristics.drawFunctionHistogram();
            calculationOfStatisticalCharacteristics.getFrequencyPolygon();
        } catch (FileNotFoundException exception) {
            System.out.println("Файл не найден!");
            System.exit(0);
        }
    }
}
