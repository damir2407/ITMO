package PokemonsAttacks;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class EnergyBall extends SpecialMove {
    private int k;

    public EnergyBall() {
        super(Type.GRASS, 90, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        k = k + 1;
        if ((Math.random() <= 0.1) && (k < 7)) {
            p.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe() {
        if (k < 6) {
            return "использует способность Energy Ball, с шансом снизить специальную защиту соперника на 1";
        } else return "пытается использовать способность Energy Ball. Неудача";

    }
}
