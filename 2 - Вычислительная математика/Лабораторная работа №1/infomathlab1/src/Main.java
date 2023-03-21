public class Main {

    public static void main(String[] args) {
        startApp();
    }

    public static void startApp() {
        Messenger messenger = new Messenger();
        IRead reader = new Reader(messenger);

        reader.readInputMethod();

        MatrixPOJO userMatrix = new MatrixPOJO();
        Integer matrixSize = reader.readMatrixSize();
        userMatrix.setSize(matrixSize);
        Double[][] matrix = reader.readMatrix(matrixSize);
        userMatrix.setMatrix(matrix);
        Double accuracy = reader.readAccuracy();
        userMatrix.setAccuracy(accuracy);

        SimpleIterationFunctional simpleIterationFunctional = new SimpleIterationFunctional(messenger);
        simpleIterationFunctional.simpleIterationMethod(userMatrix);
        long usedBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory used " + usedBytes);
    }
}
