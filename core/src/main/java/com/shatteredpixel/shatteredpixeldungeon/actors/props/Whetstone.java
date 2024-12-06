package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Bundle;

@PropAnnotation(type = PropRareType.UnCommon)
public class Whetstone extends Prop{
    {
        maxLevel=5;
        rate=5;
        rateByLevel=5;
        value=500;
        valueByLevel=-50;
    }

    @Override
    public float getFinallyValue() {
        float val = super.getFinallyValue();
        if (val<=0) val=1;
        return val;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }
    @Override
    public void onDelay(float time) {
        curTime+=time;
        if (curTime>getFinallyValue()){
            curTime=curTime-getFinallyValue();
            Dungeon.hero.add(new PropDamage().setLevel((int)getFinallyRate()));
        }
    }
}
