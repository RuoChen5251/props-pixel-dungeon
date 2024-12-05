package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation(type = PropRareType.Rare)
public class Telescope extends Prop{
    {
        rate=2;
        rateByLevel=0.5f;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",
                Messages.decimalFormat("#.##",(getFinallyRate())));
    }
}
