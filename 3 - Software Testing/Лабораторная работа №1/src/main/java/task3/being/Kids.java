package main.java.task3.being;

import main.java.task3.notalive.Place;
import main.java.task3.notalive.Poster;

public class Kids extends Born {

    private Place whereClimbed;
    private Poster giveWhat;
    private Snusmumrik toWhom;

    public void setWhereClimbed(Place whereClimbed) {
        this.whereClimbed = whereClimbed;
    }

    public void setToWhom(Snusmumrik toWhom) {
        this.toWhom = toWhom;
    }

    public Kids(String name, String description) {
        super.setName(name);
        super.setDescription(description);
    }

    public Kids(){
        super.setName("малыш");
    }

    public Place getWhereClimbed() {
        return whereClimbed;
    }

    public Poster getGiveWhat() {
        return giveWhat;
    }

    public Snusmumrik getToWhom() {
        return toWhom;
    }

    public void setGivePoster(Poster giveWhat) {
        if (whereClimbed != null && whereClimbed.equals(giveWhat.getWhereCling())) {
            this.giveWhat = giveWhat;
        }
    }
}
