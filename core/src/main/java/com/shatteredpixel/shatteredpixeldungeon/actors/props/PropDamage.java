package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
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
    public int beforeAttack(Char ch, int damage) {
        damage += level;
        return super.beforeAttack(ch, damage);
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",level);
    }
}
