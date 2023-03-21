package request_structure;

import java.io.Serializable;

public class Request implements RequestInterface, Serializable {

    public static final long serialVersionUID = 2407704212345L;
    private String command;
    private Object[] args;

    public Request(String command, Object... args) {
        this.command = command;
        this.args = args;
    }


    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public Object[] getArgs() {
        return args;
    }

}
