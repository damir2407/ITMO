import java.util.Objects;

public class Khnygl extends Person {
    private String name;

    public Khnygl(String name) {
        super(name);
        this.name = name;
    }

    public void kick(Things things, Place place) {
        System.out.print("Пнув ногой " + things.getTRANSLATE() + " так, что " + things.getTRANSLATE() + " отлетела в " + place.getNotAliveName());
    }

    public void overTurn(Things things) {
        System.out.print(", и опрокинув несколько " + things.getTRANSLATE() + ", ");
    }

    public void runOut(Place place) {
        System.out.println(getAliveName() + " выбежал из " + place.getNotAliveName() + place.getNotAliveDescription() + ". Больше его не видели.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Khnygl khnygl = (Khnygl) o;
        return Objects.equals(name, khnygl.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Khnygl{" +
                "name='" + name + '\'' +
                '}';
    }
}

