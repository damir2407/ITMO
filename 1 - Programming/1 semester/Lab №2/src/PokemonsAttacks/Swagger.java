package PokemonsAttacks;


import ru.ifmo.se.pokemon.*;

public class Swagger extends StatusMove {
    private int k;

    public Swagger() {
        super(Type.NORMAL, 0, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        k = k + 1;
        if (k < 7) {
            Effect.confuse(p);
            p.setMod(Stat.ATTACK, +2);
            p.addEffect(new Effect().turns(4).chance(0.33).stat(Stat.HP, ((int) p.getStat(Stat.ATTACK))));
        }
    }


    @Override
    protected String describe() {
        if (k < 6) {
            return "использует способность Swagger";
        } else return "пытается использовать способность Swagger. Неудача";
    }
}
