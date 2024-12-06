package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation
public class Sword extends Prop{
    {
        maxLevel=10;
        value=1;
        valueByLevel=1;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public void onAdd() {
        Dungeon.hero.add(new PropDamage().setLevel((int)getFinallyValue()));
    }

    @Override
    public void onRemove() {
        PropDamage pd = Dungeon.hero.prop(PropDamage.class);
        if (pd==null) return;;
        pd.level-=(int)getFinallyValue();
        if (pd.level<=0)
            Dungeon.hero.remove(pd);
    }
}
