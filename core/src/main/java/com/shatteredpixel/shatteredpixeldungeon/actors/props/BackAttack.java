package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation
public class BackAttack extends Prop{
    {
        maxLevel=10;
        rate = 1.1f;
        rateByLevel=0.1f;
    }

    @Override
    public int beforeAttack(Char ch, int damage) {
        if (((Mob)ch).surprisedBy(Dungeon.hero))
            damage = (int)(damage * getFinallyRate());
        return damage;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }
}
