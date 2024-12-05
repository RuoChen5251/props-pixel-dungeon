package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;

@PropAnnotation(type = PropRareType.Other)
public class PropDamageTemp extends PropDamage{
    @Override
    public int beforeDamaged(int dmg) {
        Dungeon.hero.remove(this);
        return super.beforeDamaged(dmg);
    }
}
