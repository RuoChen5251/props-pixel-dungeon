package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation(type = PropRareType.UnCommon)
public class MembershipCard extends Prop {
    {
        rate = 0.8f;
        rateByLevel = -0.05f;
    }

    @Override
    public int icon() {
        return PropIndicator.MEMBERSHIP_CARD;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",(int)(getFinallyRate()*10));
    }
}
