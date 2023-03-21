package MyPokemons;

import PokemonsAttacks.DoubleEdge;
import PokemonsAttacks.SandAttack;
import PokemonsAttacks.Tackle;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Deerling extends Pokemon {
    public Deerling(String name,int lvl){
        super(name,lvl);
        setType(Type.NORMAL,Type.GRASS);
        setStats(60,60,50,40,50,75);
        setMove(new DoubleEdge(),new Tackle(),new SandAttack());
    }
}
