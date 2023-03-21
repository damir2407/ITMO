public class Main {

    public static void main(String[] args) {

        Dungeon dungeon = new Dungeon("Подземелье", Adjectives.LOW, Adjectives.LONG);
        System.out.println(dungeon);
        Dungeon.Lamps rowOfLamps = new Dungeon.Lamps("Лампы", Adjectives.HANGING);
        rowOfLamps.illuminate("слабо");

        Walls walls = new Walls("стены");
        System.out.println(walls);

        Alice alice = new Alice("Алиса", 110);

        Walls.Door[] doors = new Walls.Door[4];
        for (int i = 0; i < doors.length; i++) {
            doors[i] = walls.new Door("дверь_" + (i + 1));
            alice.openDoors(doors[i], new NearDoors());
        }
        for (int i = 0; i < doors.length; i++) {
            alice.openDoors(doors[i], new NearDoors());
        }

        alice.pace();
        alice.think("выбраться");


        alice.bump(new NearTable());
        Table table = new Table(Adjectives.SMALL, Adjectives.GLASS);
        GoldenKey goldenKey = new GoldenKey(Adjectives.TINY);
        System.out.println(table);
        System.out.println(alice.getName() + " испытывает " + alice.getMood());

        IThinkAble iThinkAble = new IThinkAble() {
            @Override
            public void think(String reason) {
                System.out.println(alice.getName() + " думает, что " + goldenKey.getName() + reason);
            }
        };
        iThinkAble.think(" от дверей \n");


        for (int i = 0; i < doors.length; i++) {
            alice.openDoors(doors[i], "Добросовестно", table.goldenKey, new NearDoors());
        }


        Curtain curtain = new Curtain("Штора", Adjectives.EXTRA_LONG);
        alice.noticeCurtain(curtain, new NearCurtain());
        curtain.hide();
        alice.openCurtain(curtain);

        Corridor corridor = new Corridor("коридор");
        alice.openDoors(curtain.specialDoor, goldenKey);
        alice.toKneel();
        alice.lookIn(corridor);

        alice.getOut(dungeon);
        alice.resent("большая и нескладная");

        Spyglass spyglass = new Spyglass("подзорная труба");
        Fan fan = new Fan("веер");
        alice.wantBe(spyglass, fan);

    }


}
