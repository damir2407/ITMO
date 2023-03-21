public class Dungeon extends Place {
    private String status;
    private String condition;
    private static final String nextCondition = Adjectives.OPEN.getTRANSLATE();

    public Dungeon(String name, Adjectives adjectives1, Adjectives adjectives2) {
        super(name, adjectives1, adjectives2, nextCondition);
        this.status = adjectives1.getTRANSLATE();
        this.condition = adjectives2.getTRANSLATE();
    }

    static class Lamps {
        private String name;
        private String condition = "выключены";
        private String status;

        public Lamps(String name, Adjectives adjectives) {
            this.name = name;
            this.status = adjectives.getTRANSLATE();
        }

        public Lamps(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public String getStatus() {
            return status;
        }



        public String getCondition() {
            return condition;
        }

        public void illuminate(String how) {

            class Vaults {
                private String mode = "темнота";
                private final String name;

                public Vaults(String name) {
                    this.name = name;
                }

                public String getName() {
                    return name;
                }

                public void setMode(String mode) {
                    this.mode = mode;
                }

                public String getMode() {
                    return this.mode;
                }
            }


            Vaults vaults = new Vaults("Своды");
            this.condition = "включены";
            System.out.print(getStatus() + " " + getName() + " " + getCondition() + ". ");
            vaults.setMode("освещены");
            System.out.println(vaults.getName() + " " + how + " " + vaults.getMode() + ".");
        }
    }

    @Override
    public String toString() {
        return "Подзелемелье" + " " + status +
                " и " + condition + ".";
    }

}
