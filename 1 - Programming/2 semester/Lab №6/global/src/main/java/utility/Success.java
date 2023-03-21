package utility;

public class Success<T> implements Result {
    private T object;

    public Success(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
