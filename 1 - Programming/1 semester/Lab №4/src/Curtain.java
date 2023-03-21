public class Curtain {
    private String condition;
    private String name;
    private String status = Adjectives.CLOSED.getTRANSLATE();

    public Curtain(String name, Adjectives adjectives) {
        this.name = name;
        this.condition = adjectives.getTRANSLATE();
    }

    public Curtain() {
    }

    public Curtain(String name) {
        this.name = name;
    }

    public String getCondition() {
        return condition;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    SpecialDoor specialDoor = new SpecialDoor("дверца", 30);

    public void hide() {
        specialDoor.setNextCondition("спрятана");
        System.out.println(specialDoor.getStatus() + " " + specialDoor.getName() + " " + specialDoor.getHeight() + " сантиметров высотой " + specialDoor.getNextCondition() + " за " + getName());
    }
}

