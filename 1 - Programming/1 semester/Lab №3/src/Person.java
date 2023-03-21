import java.util.Objects;

public abstract class Person implements Alive {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String getAliveName() {
        return this.name;
    }

    public void becameClear() {
        System.out.print(getAliveName() + " стало ясно ");
    }

    public void notLive(String word) {
        System.out.println(word + " жить скоро будет " + "нельзя.");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
