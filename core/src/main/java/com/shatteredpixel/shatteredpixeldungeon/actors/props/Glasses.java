package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation
public class Glasses extends Prop{
    {
        maxLevel=3;
        rate = 1.5f;
        rateByLevel = 0.1f;
        value = 1;
        valueByLevel=1;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",(int)getFinallyValue(),
                Messages.decimalFormat("#.##%",100*(getFinallyRate()-1)));
    }
}
