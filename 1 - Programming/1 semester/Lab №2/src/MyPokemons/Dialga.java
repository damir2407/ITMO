package MyPokemons;

import PokemonsAttacks.BulkUp;
import PokemonsAttacks.IceBeam;
import PokemonsAttacks.RockSlide;
import PokemonsAttacks.Thunder;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Dialga extends Pokemon {
    public Dialga(String name, int lvl){
        super(name,lvl);
        setStats(100,120,120,150,100,90);
        setType(Type.STEEL,Type.DRAGON);
        setMove(new Thunder(),new BulkUp(),new IceBeam(),new RockSlide());
    }
}
