package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.noosa.Image;

public class Prop {

    protected int count;
    protected float effectValue;

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
        return ""+effectValue;
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
