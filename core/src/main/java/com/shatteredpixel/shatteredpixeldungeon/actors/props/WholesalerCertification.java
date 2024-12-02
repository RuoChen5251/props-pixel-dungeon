package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation(type = PropRareType.UnCommon)
public class WholesalerCertification extends Prop{
    {
        rate = 2.5f;
        value = 3;
    }

    @Override
    public int icon() {
        return PropIndicator.WHOLESALER_CERTIFICATION;
    }
}
