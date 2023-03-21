package PokemonsAttacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class PlayNice extends StatusMove {
    private int k;

    public PlayNice() {
        super(Type.NORMAL, 0, 0);
    }

    @Override
    protected boolean checkAccuracy(Pokemon pokemon, Pokemon pokemon1) {
        return true;
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        k = k + 1;
        if (k < 7) {
            pokemon.setMod(Stat.ATTACK, -1);
        }
    }

    @Override
    protected String describe() {
        if (k < 6) {
            return "использует способность Play Nice";
        } else return "пытается использовать способность Play Nice. Неудача";


    }
}