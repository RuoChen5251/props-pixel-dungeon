package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

public class PropNone extends Prop{
    {
        maxLevel = 10000;
    }
    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }
}
