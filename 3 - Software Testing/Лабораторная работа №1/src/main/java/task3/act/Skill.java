package main.java.task3.act;

import java.util.Objects;

public class Skill implements Spell {

    private String name;
    private String how;

    public Skill(String name) {
        this.name = name;
    }

    public Skill(String name, String how) {
        this.name = name;
        this.how = how;
    }

    public String getName() {
        return name;
    }

    public String getHow() {
        return how;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return Objects.equals(name, skill.name) && Objects.equals(how, skill.how);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, how);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", how='" + how + '\'' +
                '}';
    }
}
