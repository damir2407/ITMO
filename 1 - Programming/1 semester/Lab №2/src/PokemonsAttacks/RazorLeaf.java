package PokemonsAttacks;


import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class RazorLeaf extends PhysicalMove {
    public RazorLeaf() {
        super(Type.GRASS, 55, 95);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        if (Math.random() <= 0.125) {
            System.out.println("Критический удар!");
            def.setMod(Stat.HP, (int) Math.round(damage) * 2);
        } else {
            def.setMod(Stat.HP, (int) Math.round(damage) * 1);
        }
    }

    @Override
    protected String describe() {
        return "использует способность Razor Leaf с повышенным шансом на критический удар";
    }
}
