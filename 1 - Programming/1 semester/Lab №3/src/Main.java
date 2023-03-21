public class Main {

    public static void main(String[] args) {


        // Пнув ногой винтовку так, что она отлетела в угол, и опрокинув несколько стульев, он выбежал из телевизионного павильона.
        // Больше его не видели. Эта сцена произвела неизгладимое впечатление на телезрителей, а в особенности на полицейских,
        // которые смотрели в тот день передачу. Многие из них впервые поняли, что теперь наконец настала пора, когда нельзя уже безнаказанно
        // хвататься за оружие и палить из него в кого попало. Всем стало ясно, что по-прежнему жить скоро будет нельзя.

        Khnygl khnygl = new Khnygl("Хныгль");
        khnygl.kick(Things.RIFLE, new Angle("угол"));
        khnygl.overTurn(Things.CHAIRS);
        khnygl.runOut(new TelevisionPavilion("телевизионный ", "павильон"));

        TelevisionViewers televisionViewers = new TelevisionViewers("Телезрители");
        Event event = new Event("Сцена");
        event.doing("произвела");
        televisionViewers.demonstration(Feelings.INDELIBLE_IMPRESSION);

        Policemen policemen = new Policemen("Полицейские");
        event.doing("произвела");
        policemen.demonstration(Feelings.INDELIBLE_IMPRESSION);
        policemen.see(Things.BROADCAST);


        policemen.underStood(Feelings.FIRST);

        Event event1 = new Event("пора");
        event1.doing("настала");

        policemen.notGrab(Things.WEAPON, Feelings.IMPUNITY);
        policemen.shoot(Things.WEAPON, "кого попало.");

        televisionViewers.becameClear();
        televisionViewers.notLive("по-прежнему");
        policemen.becameClear();
        policemen.notLive("по-прежнему");


    }
}
