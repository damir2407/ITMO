package main.java.task2;

public class BreadCrumbs {
    private String trace = "";

    public String getTrace() {
        return trace;
    }

    public void addLowAndHighBorder(int low, int high) {
        trace += "l" + low + "h" + high;
    }


}
