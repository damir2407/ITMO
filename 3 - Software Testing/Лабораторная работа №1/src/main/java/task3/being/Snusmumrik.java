package main.java.task3.being;


import main.java.task3.notalive.Grass;
import main.java.task3.notalive.Poster;

public class Snusmumrik extends Born {

    private Poster whatUses;
    private Grass where = new Grass();

    public void setWhere(Grass where) {
        this.where = where;
    }

    public Snusmumrik() {
        setName("Снюсмумрик.");

    }

    public void setWhatUses(Poster whatUses) {
        this.whatUses = whatUses;
    }

    public Poster getWhatUses() {
        return whatUses;
    }

    public Grass getWhere() {
        return where;
    }
}
