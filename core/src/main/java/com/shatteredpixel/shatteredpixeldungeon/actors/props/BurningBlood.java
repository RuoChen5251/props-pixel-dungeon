package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

public class BurningBlood extends Prop{
    {
        rate  = 0.2f;
        value = 6;
    }

    @Override
    public int icon(){
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc",(int)rate*100,(int)value);
    }
}
