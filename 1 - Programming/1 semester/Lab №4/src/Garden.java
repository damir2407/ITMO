public class Garden extends Place {
    private String name;
    private String condition;
    private final static String nextCondition = Adjectives.CLOSED.getTRANSLATE();

    public Garden(String name, Adjectives adjectives) {
        super(name, adjectives, nextCondition);
        this.name = name;
        this.condition = adjectives.getTRANSLATE();
    }

    class Fountains {
        private String name;
        private String condition;

        public Fountains() {
        }

        public Fountains(String name, Adjectives adjectives) {
            this.name = name;
            this.condition = adjectives.getTRANSLATE();
        }

        public String getName() {
            return name;
        }

        public String getCondition() {
            return condition;
        }
    }

    Fountains fountains = new Fountains("фонтаны", Adjectives.COOL);

    class FlowerBeds {
        private String name;
        private String description;

        public FlowerBeds() {
        }

        public FlowerBeds(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }

    FlowerBeds flowerBeds = new FlowerBeds("клумбы", "с яркими цветами");


}


