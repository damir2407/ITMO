public class Walls {
    private String name;

    public Walls(String name) {
        this.name = name;

    }

    class Door {
        private final int numOfDoors = 4;
        private String name = "двери";
        private String status = Adjectives.CLOSED.getTRANSLATE();
        private String lock = Adjectives.BIG.getTRANSLATE();

        public String getLock() {
            return this.lock;
        }

        public Door() {
        }

        public Door(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String getStatus() {
            return this.status;
        }

        public int getNumOfDoors() {
            return this.numOfDoors;
        }

        public void setStatus(Boolean haveKey) {
            if (haveKey) {
                this.status = Adjectives.OPEN.getTRANSLATE();
                System.out.println(" дверь открылась. ");
            } else
                System.out.println("пытается изменить статус " + getName() + " с " + Adjectives.CLOSED.getTRANSLATE() + " на " + Adjectives.OPEN.getTRANSLATE() + ".");
        }

        public void setStatus(GoldenKey goldenKey, String how) {
            System.out.println(how + " проверяет дверь.");
            if (goldenKey.getCondition() != Adjectives.SMALL.getTRANSLATE()) {
                System.out.println(getName() + " не открывается, " + goldenKey.getName() + " слишком " + goldenKey.getCondition() + ", а замок слишком " + this.lock);
            }
        }
    }


    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        return "По всей длине " + name + " шли "
                + new Door().getName() + ".\n";
    }
}
