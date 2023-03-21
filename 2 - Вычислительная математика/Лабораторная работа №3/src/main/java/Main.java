import methods.AbstractMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        create();
    }


    public static void create() {

        try {
            Map<String, String> functionsMap = new HashMap<>();
            functionsMap.put("1", "-2x^3-4x^2+8x-4");
            functionsMap.put("2", "-2x^3-3x^2+x+5");
            functionsMap.put("3", "3x^3+5x^2+3x-6");

            Functions functions = new Functions(functionsMap);
            Reader reader = new Reader(new Scanner(System.in), functions);

            AbstractMethod chosenMethod = reader.readSolveMethod();

            chosenMethod.mainMethod();

        } catch (NumberFormatException | NullPointerException | NoSuchElementException exception){
            System.out.println("Неверно введенны данные!");
            System.exit(0);
        }
    }
}
