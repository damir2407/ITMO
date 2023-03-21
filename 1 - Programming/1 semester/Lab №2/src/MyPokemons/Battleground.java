package MyPokemons;

import ru.ifmo.se.pokemon.Battle;

public class Battleground {

    public static void main(String[] args) {
        Battle b = new Battle();
        b.addAlly(new Dialga("Макгрегор", 7));
        b.addFoe(new Deerling("Нурмагомедов", 7));
        b.addAlly(new Sawsbuck("Тайсон", 6));
        b.addFoe(new Bounsweet("Али", 6));
        b.addAlly(new Steenee("Мейвезер", 8));
        b.addFoe(new Tsareena("Пакьяо", 8));
        Deerling dr = new Sawsbuck("salam",12);
        b.go();
    }
}
