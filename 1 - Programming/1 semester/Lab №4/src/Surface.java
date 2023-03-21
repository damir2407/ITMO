public abstract class Surface {
    private String name;
    private String condition;

    public Surface() {
    }

    public Surface(String name, Adjectives adjectives, Adjectives adjectives1) {
        this.name = name;
        this.condition = adjectives.getTRANSLATE() + " " + adjectives1.getTRANSLATE();
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }
}
