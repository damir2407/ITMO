package PokemonsAttacks;


import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

public class BulkUp extends StatusMove {
    private int k = 0;

    public BulkUp() {
        super(Type.FIGHTING, 0, 0);
    }

    @Override
    protected boolean checkAccuracy(Pokemon att,
                                    Pokemon def) {
        return true;
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        k = k + 1;
        if (k < 7) {
            p.setMod(Stat.ATTACK, +1);
            p.setMod(Stat.DEFENSE, +1);
        }
    }

    @Override
    protected String describe() {
        if (k < 6) {
            return "использует способность Bulk Up";
        } else {
            return "пытается использовать способность Bulk Up. Неудача";
        }

    }
}

