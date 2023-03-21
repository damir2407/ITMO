public class SpecialDoor {
    private int height;
    private String condition = Adjectives.CLOSED.getTRANSLATE();
    private String name = "дверца";
    private String status = Adjectives.TINY.getTRANSLATE();
    private String nextCondition;


    public SpecialDoor(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public String getNextCondition() {
        return nextCondition;
    }

    public void setNextCondition(String nextCondition) {
        this.nextCondition = nextCondition;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public String getCondition() {
        return condition;
    }

    public String getStatus() {
        return status;
    }

    public void setCondition(GoldenKey goldenKey) {
        if (goldenKey.getCondition() == getStatus())
            this.condition = Adjectives.OPEN.getTRANSLATE();
    }
}
