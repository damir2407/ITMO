public class Alice extends Person implements AliveMoves, IThinkAble, ICurtainAble {
    private String name;
    private String mood = Feelings.STANDARD.getTRANSLATE();
    private int k = 0;
    private int p = 0;

    public Alice(String name) {
        super(name);
    }


    public Alice(String name, int intelligence) {
        super(name, intelligence);
        this.name = name;
    }


    @Override
    public void pace() {
        setMood(Feelings.SADLY);

        System.out.println(getName() + " " + getMood() + " расхаживает ");

        leg.setWhich("правая");
        stepForward();

        leg.setWhich("левая");
        stepBack();
    }


    @Override
    public void think(String reason) {
        if (getIntelligence() <= 120) System.out.println(getName() + " пытается придумать, как " + reason);
        else System.out.println(getName() + " придумала как выбраться!");
    }


    public void bump(Place place) {
        BumpInto bumpInto = new BumpInto();
        bumpInto.bumpInto(this, place, new Table(Adjectives.SMALL, Adjectives.GLASS));
        setMood(Feelings.HAPPY);
    }


    @Override
    public void openDoors(Walls.Door doors, Place place) {
        boolean findDoor = false;
        k++;


        if (k == 1) {
            setPosition(place);
            System.out.println("текущее местоположение " + getName() + " " + place.getName());
            System.out.println(getName() + " начала обходить подземелье.");
        }

        lendAHand();
        for (int i = 0; i < 2; i++) {
            doors.setStatus(false);
        }
        releaseAHand();

        if (doors.getStatus() == Adjectives.OPEN.getTRANSLATE()) findDoor = true;

        if (k % doors.getNumOfDoors() == 0) {
            System.out.println(getName() + " обошла подземелье " + k / doors.getNumOfDoors() + " раз. " + "\n");
        }

        if (k / doors.getNumOfDoors() == 2 && (findDoor == false)) {
            setMood(Feelings.BIG_REGRET);
            System.out.println("к " + getMood() + " " + getName() + " удостоверилась, что все двери " + doors.getStatus() + ".");
        } else if (findDoor) System.out.println(getName() + " нашла " + Adjectives.OPEN.getTRANSLATE() + " дверь. ");
    }


    public void openDoors(Walls.Door doors, String how, GoldenKey goldenKey, Place place) {
        boolean findDoor = false;
        p++;


        if (p == 1) {
            setPosition(place);
            System.out.println("текущее местоположение " + getName() + " " + place.getName());
        }

        lendAHand();
        doors.setStatus(goldenKey, how);
        releaseAHand();

        if (doors.getStatus() == Adjectives.OPEN.getTRANSLATE()) findDoor = true;

        if (p / doors.getNumOfDoors() == 1 && (findDoor == false)) {
            System.out.println("Увы!\n");
        } else if (findDoor) System.out.println(getName() + " нашла " + Adjectives.OPEN.getTRANSLATE() + " дверь. ");
    }


    public void openDoors(SpecialDoor specialDoor, GoldenKey goldenKey) {
        lendAHand();
        specialDoor.setCondition(goldenKey);
        if (specialDoor.getCondition() == Adjectives.OPEN.getTRANSLATE()) {
            setMood(Feelings.HAPPY);
            System.out.println(getName() + " вставила " + goldenKey.getName() + " и он как раз подошел! ");
            System.out.println(getName() + " изменила свое состояние на " + getMood());
            System.out.println(getName() + " отворила дверцу.");
        }
        releaseAHand();

        Corridor corridor = new Corridor("коридор", Adjectives.NARROW, "чуть пошире крысиного лаза");
        System.out.println("За дверью находится " + corridor.getStatus() + " " + corridor.getName() + " " + corridor.getCondition());
    }

    @Override
    public void noticeCurtain(Curtain curtain, Place place) {
        System.out.println(getName() + " заметила " + curtain.getCondition() + " " + curtain.getName());
        System.out.println(getName() + " подошла к " + curtain.getName());
        setPosition(place);
        System.out.println("текущее местоположение " + getName() + " " + place.getName());
    }

    Corridor corridor = new Corridor("коридор", Adjectives.NARROW);
    Garden garden = new Garden("сад", Adjectives.WONDERFUL);

    @Override
    public void openCurtain(Curtain curtain) {
        lendAHand();
        curtain.setStatus(Adjectives.OPEN.getTRANSLATE());
        System.out.println(getName() + " изменила состояние " + curtain.getName() + " на " + curtain.getStatus());
        releaseAHand();
        curtain.specialDoor.setNextCondition("раскрыта \n");
        System.out.println(curtain.specialDoor.getName() + " " + curtain.specialDoor.getNextCondition());
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public void toKneel() {
        leg.setWhich("правая");
        bend();

        leg.setWhich("левая");
        bend();
    }


    @Override
    public void lookIn(Place place) {
        if (place.getName() == getName()) {
            System.out.println(getName() + " заглянула в отверстие " + place.getName());
            if (corridor.garden.getCondition() == Adjectives.WONDERFUL.getTRANSLATE())
                System.out.println(getName() + " ахнула " + place.getName() + " выходил в такой " +
                        corridor.garden.getCondition() + " " + corridor.garden.getName() + " каких вы, может быть и не видывали");
        }
    }

    @Override
    public void getOut(Place place) {
        garden.new Fountains("фонтаны", Adjectives.COOL);
        garden.new FlowerBeds("клумбы", "с яркими цветами");
        setPosition(garden);
        if (getPosition() != garden) {
            System.out.println(getName() + " захотелось выбраться из этого " + place.getName() + " в " + garden.getName()
                    + " погулять среди " + garden.fountains.getCondition() + " " + garden.fountains.getName()
                    + " и " + garden.flowerBeds.getName() + " " + garden.flowerBeds.getDescription());
            if (head.getCondition() != corridor.getStatus()) {
                System.out.println("в " + corridor.getStatus() + " " + corridor.getName() + " не пролезет даже одна "
                        + head.getName() + " " + getName());
                System.out.println("А если бы и прошла, тоже хорошего мало: ведь " + head.getName() + " должна быть на плечах!");
            }
        }
    }


    @Override
    public void resent(String reason) {
        System.out.println(getName() + " возмущается из-за того, что она " + reason);
    }

    @Override
    public void wantBe(FoldThings foldThings, FoldThings foldThings2) {
        foldThings.takeShape();
        foldThings2.takeShape();
        System.out.println(getName() + " хочет быть " + foldThings.getCondition() + " как " + foldThings.getName() + " или " + foldThings2.getName());
        System.out.println("Научил бы " + getName() + " кто-нибудь " + getName() + " бы стала " + foldThings.getCondition() + " и все в порядке!");
    }
}


