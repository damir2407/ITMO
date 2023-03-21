package MyPokemons;

import PokemonsAttacks.Megahorn;
import ru.ifmo.se.pokemon.Type;

public class Sawsbuck extends Deerling{
    public Sawsbuck(String name,int lvl){
        super(name,lvl);
        setType(Type.NORMAL,Type.GRASS);
        setStats(80,100,70,60,70,95);
        setMove(new Megahorn());
    }
}
