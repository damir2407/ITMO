public interface IRead {
    void readInputMethod();

    Integer readMatrixSize();

    Double[][] readMatrix(Integer matrixSize);

    String readPath();

    Double readAccuracy();
}
