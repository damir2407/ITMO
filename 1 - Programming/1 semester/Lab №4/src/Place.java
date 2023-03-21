public abstract class Place {
    private String status;
    private String condition;
    private String name;
    private static String nextCondition;

    public Place(String name, Adjectives adjectives1, Adjectives adjectives2, String nextCondition) {
        this.nextCondition = nextCondition;
        this.name = name;
        this.status = adjectives1.getTRANSLATE();
        this.condition = adjectives2.getTRANSLATE();
    }

    public Place() {
    }

    public Place(String name, Adjectives adjectives, String description, String nextCondition) {
        this.name = name;
        this.status = adjectives.getTRANSLATE();
        this.condition = description;
        this.nextCondition = nextCondition;
    }

    public Place(String name, Adjectives adjectives, String nextCondition) {
        this.name = name;
        this.condition = adjectives.getTRANSLATE();
        this.nextCondition = nextCondition;
    }

    public Place(String name, String nextCondition) {
        this.name = name;
        this.nextCondition = nextCondition;
    }


    public String getName() {
        return this.name;
    }

    public String getStatus() {
        return this.status;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getNextCondition() {
        return nextCondition;
    }
}
