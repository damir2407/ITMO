package main.java.task3.being;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import main.java.task3.act.Spell;
import main.java.task3.exception.ExistException;


public abstract class Born {

    private String name;
    private List<Spell> skills = new ArrayList<Spell>();
    private String pronoun;

    private String description;

    public void learn(Spell spell) {
        this.skills.add(spell);
    }

    public List<Spell> getSkills() {
        return skills;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        try {
            if (name != "") {
                return name;
            } else {
                throw new ExistException("обнаружен неизвестная сущность");
            }
        } catch (ExistException ex) {
            return ex.getExc();

        }
    }

    public void setSkills(List<Spell> skills) {
        this.skills = skills;
    }

    public void setPronoun(String pronoun) {
        this.pronoun = pronoun;
    }

    public String getPronoun(boolean swapParameter) {
        class Local {
            String swap() {
                if (swapParameter) {
                    return pronoun;
                } else {
                    return name;
                }
            }
        }
        Local local = new Local();
        return local.swap();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Born born = (Born) o;
        return Objects.equals(name, born.name) && Objects.equals(skills, born.skills) && Objects.equals(pronoun, born.pronoun);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, skills, pronoun);
    }

    @Override
    public String toString() {
        return "Born{" +
                "name='" + name + '\'' +
                ", skills=" + skills +
                ", pronoun='" + pronoun + '\'' +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return name + description;
    }
}
