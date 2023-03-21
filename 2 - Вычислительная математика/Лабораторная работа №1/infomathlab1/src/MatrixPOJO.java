public class MatrixPOJO {
    private Double[][] matrix;
    private Double accuracy;
    private Integer size;

    public MatrixPOJO() {
    }

    public MatrixPOJO(Double[][] matrix, Double accuracy, Integer size) {
        this.matrix = matrix;
        this.accuracy = accuracy;
        this.size = size;
    }

    public Double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Double[][] matrix) {
        this.matrix = matrix;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
