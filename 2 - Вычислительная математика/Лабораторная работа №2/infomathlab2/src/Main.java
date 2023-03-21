import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }


    public static void start() {
        Messenger messenger = new Messenger();
        Equation equation = new Equation();
        Reader reader = new Reader(messenger, new Scanner(System.in));
        Writer writer = new Writer();


        try {
            switch (reader.readTypeOfRequest()) {
                case SIMPLE_EQUATION:

                    String numberOfEquation = reader.readNumberOfEquation();
                    String selectedEquation = equation.getMapOfEquations().get(numberOfEquation);

                    ComputingFunctional computingFunctional = new ComputingFunctional();
                    computingFunctional.setSelectedEquation(selectedEquation);
                    Graph graph = new Graph(computingFunctional);

                    if (selectedEquation == null) {
                        messenger.incorrectDataMessage();
                        System.exit(0);
                    }

                    MethodType methodType = reader.readSolutionMethod();

                    reader.readInputMethod();
                    String path = reader.readOutputMethod();

                    if (path != null) {
                        writer.setFileMode(true);
                        writer.setPath(path);
                    } else {
                        writer.setFileMode(false);
                    }

                    String boundaries = reader.readIntervalBoundaries();
                    Double firstBoundaries = Double.parseDouble(boundaries.substring(1, boundaries.indexOf(";")));
                    Double secondBoundaries = Double.parseDouble(boundaries.substring(boundaries.indexOf(";") + 1, boundaries.length() - 1));


                    if (methodType.equals(MethodType.SIMPLE_ITERATION_METHOD)) {
                        SimpleIterationFunctional simpleIterationFunctional = new SimpleIterationFunctional(messenger, computingFunctional, writer);
                        simpleIterationFunctional.setFirstBorder(firstBoundaries);
                        simpleIterationFunctional.setSecondBorder(secondBoundaries);
                        simpleIterationFunctional.setEpsilon(reader.readAccuracy());
                        simpleIterationFunctional.mainIterationMethod();
                    }

                    if (methodType.equals(MethodType.CHORD_METHOD)) {
                        ChordFunctional chordFunctional = new ChordFunctional(messenger, computingFunctional, writer);
                        chordFunctional.setFirstBorder(firstBoundaries);
                        chordFunctional.setSecondBorder(secondBoundaries);
                        chordFunctional.setEpsilon(reader.readAccuracy());
                        chordFunctional.mainChordMethod();
                    }

                    graph.drawFunction();

                    break;
                case SYSTEM_OF_EQUATIONS:
                    ComputingFunctional computingFunctional1 = new ComputingFunctional();
                    computingFunctional1.setSelectedEquation("{2*x1^2-x1*x2-5x1+1=0 {x1+3*lg(x1)-x2^2=0");
                    Graph graph1 = new Graph(computingFunctional1);

                    messenger.selectedSystemMessage();
                    reader.readInputMethod();

                    String path1 = reader.readOutputMethod();

                    if (path1 != null) {
                        writer.setFileMode(true);
                        writer.setPath(path1);
                    } else {
                        writer.setFileMode(false);
                    }

                    SystemSimpleIterationFunctional simpleIterationFunctional = new SystemSimpleIterationFunctional(messenger, computingFunctional1, writer);

                    String boundaries1 = reader.readIntervalBoundaries();
                    Double firstBoundaries1 = Double.parseDouble(boundaries1.substring(1, boundaries1.indexOf(";")));
                    Double secondBoundaries1 = Double.parseDouble(boundaries1.substring(boundaries1.indexOf(";") + 1, boundaries1.length() - 1));

                    String boundaries2 = reader.readIntervalBoundaries();
                    Double firstBoundaries2 = Double.parseDouble(boundaries2.substring(1, boundaries2.indexOf(";")));
                    Double secondBoundaries2 = Double.parseDouble(boundaries2.substring(boundaries2.indexOf(";") + 1, boundaries2.length() - 1));

                    simpleIterationFunctional.setFirstBorderOfFirstValue(firstBoundaries1);
                    simpleIterationFunctional.setSecondBorderOfFirstValue(secondBoundaries1);
                    simpleIterationFunctional.setFirstBorderOfSecondValue(firstBoundaries2);
                    simpleIterationFunctional.setSecondBorderOfSecondValue(secondBoundaries2);

                    simpleIterationFunctional.setEpsilon(reader.readAccuracy());
                    simpleIterationFunctional.mainIterationMethod();
                    graph1.drawSystemOfFunctions();
                    break;
                default:
                    break;

            }
        } catch (NumberFormatException | IOException | StringIndexOutOfBoundsException exception) {
            messenger.incorrectDataMessage();
            System.exit(0);
        }
    }
}
