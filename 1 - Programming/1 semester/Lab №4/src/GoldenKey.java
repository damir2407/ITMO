public class GoldenKey {
    private final String name = "золотой ключик";
    private String condition;

    public GoldenKey(Adjectives adjectives) {
        this.condition = adjectives.getTRANSLATE();
    }

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return getCondition() + " " + getName();
    }
}
