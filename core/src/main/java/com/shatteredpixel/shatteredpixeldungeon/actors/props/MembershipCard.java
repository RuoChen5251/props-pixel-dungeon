package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

public class MembershipCard extends Prop {
    {
        rate = 0.8f;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",(int)(getFinallyRate()*10));
    }
}
