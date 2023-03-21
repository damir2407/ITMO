public abstract class FoldThings {
    private String name;
    private String condition;

    public FoldThings(String name, String condition) {
        this.name = name;
        this.condition = condition;

    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void takeShape() {
        setCondition(Adjectives.FOLDING.getTRANSLATE());
    }


    public String getName() {
        return name;
    }
}
