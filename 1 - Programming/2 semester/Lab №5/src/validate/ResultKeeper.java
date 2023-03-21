package validate;

public interface ResultKeeper {


    Result ok(Object object);

    Result error(String errorMessage);

    boolean isOK();

    String getErrorMessage();

    Object getObject();
}
