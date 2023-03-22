package main.java.task3.notalive;

import java.util.Objects;

public abstract class Thing implements Craft {

    private String name;
    private String description;
    private String pronoun;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getReversDescription() {
        return description;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getPronoun() {
        return pronoun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thing thing = (Thing) o;
        return Objects.equals(name, thing.name) && Objects.equals(description, thing.description) && Objects.equals(pronoun, thing.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, pronoun);
    }

    @Override
    public String toString() {
        return "Thing{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }

}
