package data;

/**
 * X-Y coordinates.
 */
public class Coordinates {
    private Float x; //Максимальное значение поля: 610, Поле не может быть null
    private Double y; //Поле не может быть null

    public Coordinates(Float x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    /**
     * @return X-coordinate.
     */
    public Float getX() {
        return x;
    }

    /**
     * @return Y-coordinate.
     */
    public Double getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }
}

