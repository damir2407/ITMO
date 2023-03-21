package utility;

public interface AnswerInterface {

    AnswerInterface ok(Object object);


    AnswerInterface error(String errorMessage);

    boolean isOK();

    String getErrorMessage();

    Object getObject();
}
