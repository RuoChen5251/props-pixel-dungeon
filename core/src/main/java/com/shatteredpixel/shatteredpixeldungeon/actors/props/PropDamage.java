package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

@PropAnnotation(type = PropRareType.Other)
public class PropDamage extends Prop{
    {
        maxLevel=1000;
    }

    @Override
    public String iconTextDisplay() {
        return level+"";
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public int beforeDamaged(int dmg) {
        dmg += Random.IntRange(0,level);
        return dmg;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",level);
    }
}
