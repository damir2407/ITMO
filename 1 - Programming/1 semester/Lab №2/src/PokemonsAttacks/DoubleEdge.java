package PokemonsAttacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class DoubleEdge extends PhysicalMove {
    public DoubleEdge() {
        super(Type.NORMAL, 120, 100);
    }

    @Override
    protected void applySelfDamage(Pokemon att,
                                   double damage) {
        att.setMod(Stat.HP, (int) Math.round(damage * 1 / 3d));
    }

    @Override
    protected String describe() {
        return "использует способность Double Edge, нанося себе треть урона";
    }
}