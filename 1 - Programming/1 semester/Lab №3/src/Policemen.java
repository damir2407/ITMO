import java.util.Objects;

public class Policemen extends Person {
    private String name;

    public Policemen(String name) {
        super(name);
        this.name = name;
    }

    public void demonstration(Feelings feelings) {
        if (feelings.equals(Feelings.INDELIBLE_IMPRESSION)) {
            System.out.print("в особенности " + feelings.getTRANSLATE() + " на " + getAliveName());
        }
    }

    public void see(Things things) {
        if (things.equals(Things.BROADCAST)) {
            System.out.println(", которые смотрели в тот день " + things.getTRANSLATE() + ".");
        }
    }

    public void underStood(Feelings feelings) {
        if (feelings.equals(Feelings.FIRST)) {
            System.out.print("Многие из " + getAliveName() + " " + feelings.getTRANSLATE() + " поняли, что теперь наконец ");
        }
    }

    public void notGrab(Things things, Feelings feelings) {
        System.out.print("когда нельзя " + feelings.getTRANSLATE() + " хвататься за " + things.getTRANSLATE());
    }

    public void shoot(Things things, String name) {
        System.out.println(" и палить из " + things.getTRANSLATE() + " в " + name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Policemen policemen = (Policemen) o;
        return Objects.equals(name, policemen.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

    @Override
    public String toString() {
        return "Policemen{" +
                "name='" + name + '\'' +
                '}';
    }
}
