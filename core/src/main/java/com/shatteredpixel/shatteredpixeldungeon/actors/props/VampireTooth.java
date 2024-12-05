package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

public class VampireTooth extends Prop{
    {
        maxLevel = 10;
        rate = 0.1f;
        rateByLevel=0.01f;
        value = 1;
        valueByLevel = 0.1f;
    }

    @Override
    public void onAttack() {
        if (Random.Float()<getFinallyRate()){
            Dungeon.hero.heal(getFinallyValue());
        }
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }
}
