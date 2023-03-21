public abstract class Person {
    private String name;
    private String mood;
    private int intelligence;
    private Place position;

    public Person(String name, int intelligence) {
        if (name.matches(".*\\d+.*")) {
            try {
                throw new DescException("Имя персонажа не может содержать цифр!");
            } catch (DescException e) {
                e.printStackTrace();
                this.name = "undefined";
            }
        } else this.name = name;
        this.intelligence = intelligence;
    }

    public Person(String name) {
    }

    public Place getPosition() {
        return position;
    }

    public void setPosition(Place place) {
        Garden garden = new Garden("сад", Adjectives.WONDERFUL);
        if (place.getNextCondition() == Adjectives.OPEN.getTRANSLATE()) this.position = place;
    }


    public String getMood() {
        return this.mood;
    }

    public void setMood(Feelings feelings) {
        this.mood = feelings.getTRANSLATE();
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
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

    class Hand {
        private final String name;
        private String condition = Adjectives.CALM.getTRANSLATE();

        Hand(String name) {
            this.name = name;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getName() {
            return this.name;
        }

        public String getCondition() {
            return this.condition;
        }
    }

    Hand hand = new Hand(name);

    protected void lendAHand() {
        hand.setCondition("протянута");
        System.out.println("у " + getName() + " " + hand.getCondition() + " " + hand.getName());
    }

    protected void releaseAHand() {
        hand.setCondition("опущенна");
        System.out.println(getName() + " опустил(а) " + hand.getName());
    }

    class Leg {
        private String which;

        public Leg() {
        }

        private final String name = "нога";
        private String condition = Adjectives.CALM.getTRANSLATE();

        public String getName() {
            return this.name;
        }

        public String getCondition() {
            return this.condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getWhich() {
            return which;
        }

        public void setWhich(String which) {
            this.which = which;
        }

    }

    Leg leg = new Leg();


    protected void stepForward() {
        leg.setCondition(Adjectives.ACTIVE.getTRANSLATE());
        System.out.println("Объект " + leg.getWhich() + " " + leg.getName() + " персонажа " + getName() + " изменяет состояние на " + leg.getCondition() + " и совершает шаг вперед ");
    }

    protected void stepBack() {
        leg.setCondition(Adjectives.ACTIVE.getTRANSLATE());
        System.out.println("Объект " + leg.getWhich() + " " + leg.getName() + " персонажа " + getName() + " изменяет состояние на " + leg.getCondition() + " и совершает шаг назад ");
    }

    protected void bend() {
        leg.setCondition(Adjectives.BENT.getTRANSLATE());
        System.out.println("Объект " + leg.getWhich() + " " + leg.getName() + " персонажа " + getName() + " изменяет состояние на " + leg.getCondition());
    }

    class Head {
        private final String name = "голова";
        private String condition = Adjectives.BIG.getTRANSLATE();

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getName() {
            return this.name;
        }

        public String getCondition() {
            return this.condition;
        }
    }


    Head head = new Head();
}



