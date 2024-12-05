package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Random;

@PropAnnotation(type = PropRareType.Rare)
public class CorpseVine extends Prop{
    {
        rate=0.25f;
        rateByLevel=0.15f;
    }

    @Override
    public int icon() {
        return PropIndicator.CORPSE_VINE;
    }

    @Override
    public void onMobsDie(Mob mob) {
        if (Random.Float()<getFinallyRate()){
            float hp = mob.HT * getFinallyRate();
            if (Dungeon.hero.HP<Dungeon.hero.HT)
                GLog.p(logText());
            Dungeon.hero.heal(hp);
        }

    }

}
