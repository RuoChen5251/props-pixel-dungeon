package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;

@PropAnnotation(type = PropRareType.Boss,isSomeHeroClassOnly = true,heroClass = HeroClass.WARRIOR)
public class EmblemCore extends Prop{
    {
        rate=2;
        rateByLevel=0.5f;
        value=2;
        valueByLevel=1;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }
}
