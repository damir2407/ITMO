package main.java.task3.being;
import main.java.task3.notalive.Craft;
import main.java.task3.notalive.Size;


public class Hemuliha extends Born {

    private Size descriptions;
    private Born whoInteractsWith;
    private Craft Where;
    private Craft have;

    public Hemuliha() {
        setName("Хемулиха ");
    }

    public Size getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Size descriptions) {
        this.descriptions = descriptions;
    }

    public void setWhere(Craft where) {
        Where = where;
    }

    public void setHave(Craft have) {
        this.have = have;
    }

    public Born getWhoInteractsWith() {
        return whoInteractsWith;
    }

    public void setWhoInteractsWith(Born whoInteractsWith) {
        this.whoInteractsWith = whoInteractsWith;
    }

    public Craft getWhere() {
        return Where;
    }

    public Craft getHave() {
        return have;
    }

}
