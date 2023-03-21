public class Corridor extends Place {
    private String status;
    private String condition;
    private String name;
    private static final String nextCondition = Adjectives.CLOSED.getTRANSLATE();


    public Corridor(String name, Adjectives adjectives, String description) {
        super(name, adjectives, description, nextCondition);
        this.name = name;
        this.status = adjectives.getTRANSLATE();
        this.condition = description;
    }

    public Corridor() {
    }

    public Corridor(String name) {
        super(name, nextCondition);
        this.name = name;
    }

    public Corridor(String name, Adjectives adjectives) {
        super(name, adjectives, nextCondition);
        this.name = name;
        this.status = adjectives.getTRANSLATE();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    Garden garden = new Garden("сад", Adjectives.WONDERFUL);

}
