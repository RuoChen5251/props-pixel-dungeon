package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;

@PropAnnotation(type = PropRareType.Other)
public class PropDamageTemp extends PropDamage{
    @Override
    public int beforeAttack(Char ch, int dmg) {
        Dungeon.hero.remove(this);
        return super.beforeAttack(ch,dmg);
    }
}
