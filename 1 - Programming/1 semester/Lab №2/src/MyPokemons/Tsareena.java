package MyPokemons;


import PokemonsAttacks.Swagger;
import ru.ifmo.se.pokemon.Type;

public class Tsareena extends Steenee {
    public Tsareena(String name,int lvl){
        super(name,lvl);
        setStats(72,120,98,50,98,72);
        setType(Type.GRASS);
        setMove(new Swagger());
    }
}
