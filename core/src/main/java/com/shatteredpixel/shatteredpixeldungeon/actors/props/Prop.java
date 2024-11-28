package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public class Prop extends Actor {
    {
        actPriority = PROP_PRIO_AFTER; //low priority, towards the end of a turn
    }
    public int count;//重复个数
    public float rate;//触发概率
    public float rateByCount;//每多一个触发概率叠加率
    public float value;//生效值
    public float valueByCount;//每多一个生效值增加量

    public float getFinallyRate(){
        float res = rate+(count-1)*rateByCount;
        return res>1?1:res;
    }
    public float getFinallyValue(){
        return value+(count-1)*valueByCount;
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
        return ""+ getFinallyValue();
    }
    public String iconCountDisplay(){
        return count>1?""+count:"";
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

    public Prop setCount(int count){
        this.count = count;
        return this;
    }

    private static final String COUNT    = "count";
    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
        bundle.put(COUNT,count);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        count = bundle.getInt(COUNT);
    }
}
