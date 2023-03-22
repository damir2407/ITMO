package main.java.task3.exception;

public class DescException extends Exception {

    public DescException(String message) {
        super("!!!" + message + "!!!");
    }

}