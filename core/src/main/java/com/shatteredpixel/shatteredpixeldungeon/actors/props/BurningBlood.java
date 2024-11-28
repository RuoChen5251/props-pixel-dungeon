package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

public class BurningBlood extends Prop{
    {
        count = 1;
        rate  = 0.2f;
        rateByCount = 0.05f;
        value = 1;
        valueByCount = 1;
    }

    @Override
    public int icon(){
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc",
                getFinallyRate()*100,getFinallyValue());
    }
}
