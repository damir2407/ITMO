package PokemonsAttacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class Tackle extends PhysicalMove {
    public Tackle() {
        super(Type.NORMAL, 40, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon def,
                                  double damage) {
        def.setMod(Stat.HP, (int) Math.round(damage));
    }

    @Override
    protected String describe() {
        return "использует способность Tackle";
    }
}
