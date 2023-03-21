package PokemonsAttacks;


import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class SandAttack extends StatusMove {
    private int k;

    public SandAttack() {
        super(Type.GROUND, 0, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        k = k + 1;
        if (k < 7) {
            p.setMod(Stat.ACCURACY, -1);
        }
    }

    @Override
    protected String describe() {
        if (k < 6) {
            return "использует способность Sand Attack";
        } else {
            return "пытается использовать способность Sand Attack. Неудача";
        }

    }
}
