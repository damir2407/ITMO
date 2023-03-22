package main.java.task3.notalive;

public class Drama extends Thing {

    private Premier premier = new Premier();

    public Drama() {
        setDescription("О ");
        setName("пьесы");
    }

    public class Premier extends Thing {
        public Premier() {
            setName("премьере ");
        }
    }

    public Premier getPremier() {
        return premier;
    }

}
