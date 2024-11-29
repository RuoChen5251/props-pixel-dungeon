package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Affection;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.AntiMagic;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Brimstone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Camouflage;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Entanglement;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Flow;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Obfuscation;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Potential;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Repulsion;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Stone;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Swiftness;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Thorns;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.glyphs.Viscosity;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public class Prop extends Actor {
    {
        actPriority = PROP_PRIO_AFTER; //low priority, towards the end of a turn
    }

    public float rate;
    public float rateByCount;
    public float value;
    public float valueByCount;
    public int count=1;
    public int maxCount = 1;
    public boolean canSuperposition(){
        return maxCount>count;
    }

    public float getFinallyRate(){
        float res = rate+(count-1)*rateByCount;
        return res>1?1:res;
    }
    public float getFinallyValue(){
        return value+(count-1)*valueByCount;
    }
    public Prop setCount(int count){
        this.count = count;
        return this;
    }

    //显示
    public int icon(){
        return PropIndicator.NONE;
    }
    public String name() {
        return Messages.get(this, "name");
    }

    public String desc(){
        return Messages.get(this, "desc");
    }

    public String iconTextDisplay(){
        return count>1?count+"":"";
    }

    //some buffs may want to tint the base texture color of their icon
    public void tintIcon( Image icon ){
        //do nothing by default
    }



    //逻辑
    public boolean act() {
        return false;
    }

    public void onAdd() {

    }

    public void onRemove() {

    }


    private static final String PROP_RATE    = "prop_rate";
    private static final String PROP_VALUE    = "prop_value";
    private static final String PROP_COUNT   = "prop_count";
    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(PROP_RATE,rate);
        bundle.put(PROP_VALUE,value);
        bundle.put(PROP_COUNT,count);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        rate = bundle.getFloat(PROP_RATE);
        value = bundle.getFloat(PROP_VALUE);
        count = bundle.getInt(PROP_COUNT);
    }
}
