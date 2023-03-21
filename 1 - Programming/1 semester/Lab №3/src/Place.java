import java.util.Objects;

public abstract class Place implements NotAlive {
    private String name;
    private String description;

    public Place(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Place(String name) {
        this.name = name;
    }

    @Override
    public String getNotAliveName() {
        return this.name;
    }

    @Override
    public String getNotAliveDescription() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(name, place.name) &&
                Objects.equals(description, place.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


