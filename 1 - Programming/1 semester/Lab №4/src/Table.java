public class Table extends Surface {
    private static final String name = "столик";
    private String condition;

    public Table(Adjectives adjectives, Adjectives adjectives1) {
        super(name, adjectives, adjectives1);
        this.condition = adjectives.getTRANSLATE() + " " + adjectives1.getTRANSLATE();
    }
    public Table(){}

    public String getName() {
        return name;
    }

    public String getCondition() {
        return condition;
    }

    GoldenKey goldenKey = new GoldenKey(Adjectives.TINY);

    @Override
    public String toString() {
        return "На " + getCondition() + " " + getName() + " лежит " + goldenKey.toString();
    }
}
