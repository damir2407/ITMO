package PokemonsAttacks;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Type;

public class Thunder extends SpecialMove {
    public Thunder() {
        super(Type.ELECTRIC, 110, 70);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if ((Math.random() <= 0.3) && (!p.hasType(Type.ELECTRIC))) {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe() {
        return "использует способность Thunder, с шансом 30% парализовать соперника";
    }
}
