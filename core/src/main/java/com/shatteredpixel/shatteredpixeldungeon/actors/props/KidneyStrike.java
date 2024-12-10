package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Paralysis;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

@PropAnnotation(type = PropRareType.Boss)
public class KidneyStrike extends Prop{
    {
        maxLevel=5;
        rate = 0.2f;
        rateByLevel=0.1f;
        value = 3;
        valueByLevel=1;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public int beforeAttack(Char ch, int damage) {
        if (Random.Float()<=getFinallyRate()){
            if (ch.buff(Paralysis.class)==null){
                Buff.affect(ch, Paralysis.class,getFinallyValue());
            }
            return (int)(damage * (1+getFinallyRate()));
        }
        return super.beforeAttack(ch, damage);
    }
}
