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
    public String iconTextDisplay() {
        return getFinallyRate()*100+"%";
    }

    @Override
    public String name() {
        return Messages.get(this, "name",
                count);
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc",
                (int)getFinallyRate()*100
                ,(int)getFinallyValue());
    }
}
