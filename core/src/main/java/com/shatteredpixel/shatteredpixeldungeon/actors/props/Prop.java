package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.noosa.Image;

public class Prop {

    public int count;//重复个数
    public float rate;//触发概率
    public float rateByCount;//每多一个触发概率叠加率
    public float value;//生效值
    public float valueByCount;//每多一个生效值增加量

    public float getFinallyRate(){
        return rate+(count-1)*rateByCount;
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
        return ""+count;
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
}
