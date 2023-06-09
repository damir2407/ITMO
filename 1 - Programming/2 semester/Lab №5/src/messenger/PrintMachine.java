package messenger;

public class PrintMachine implements PrintKeeper {


    @Override
    public void println(Object object) {
        System.out.println(object);
    }

    @Override
    public void printErr(Object object) {
        System.out.println(object);
    }

    @Override
    public void print(Object object) {
        System.out.println(object);
    }
}
