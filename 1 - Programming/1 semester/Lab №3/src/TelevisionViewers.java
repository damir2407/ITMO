import java.util.Objects;

public class TelevisionViewers extends Person {
    private String name;

    public TelevisionViewers(String name) {
        super(name);
        this.name = name;
    }

    public void demonstration(Feelings feelings) {
        if (feelings.equals(Feelings.INDELIBLE_IMPRESSION)) {
            System.out.println(feelings.getTRANSLATE() + " на " + getAliveName() + ".");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TelevisionViewers that = (TelevisionViewers) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "TelevisionViewers{" +
                "name='" + name + '\'' +
                '}';
    }
}
