package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Shadows;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation(type = PropRareType.UnCommon)
public class BrokenShadowCloak extends Prop{
    {
        rate = 5;
        rateByLevel=5;
        value = 300;
        valueByLevel=-50;
        curTime=0;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public float getFinallyValue() {
        return super.getFinallyValue()<=0?1:super.getFinallyValue();
    }

    @Override
    public void onDelay(float time) {
        curTime+=time;
        while (curTime>=getFinallyValue()){
            curTime-=getFinallyValue();
            Buff.affect( Dungeon.hero, Invisibility.class, getFinallyRate() );
        }
    }
}
