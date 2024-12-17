package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation
public class Strawberry extends Prop{
    {
        maxLevel = 10;
        value = 5;
        valueByLevel=5;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",(int)getFinallyValue());
    }

    @Override
    public void onAdd() {
        Dungeon.hero.HTBoost+=(int)getFinallyValue();
        Dungeon.hero.updateHT(true);
    }
}
