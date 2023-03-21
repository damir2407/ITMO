package MyPokemons;

import PokemonsAttacks.PlayNice;
import ru.ifmo.se.pokemon.Type;

public class Steenee extends Bounsweet {
    public Steenee(String name, int lvl) {
        super(name, lvl);
        setStats(52, 40, 48, 40, 48, 62);
        setType(Type.GRASS);
        setMove(new PlayNice());
    }

}
