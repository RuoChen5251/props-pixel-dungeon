package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

public class CorpseVine extends Prop{
    {
        maxCount=1;
        rate=0.25f;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public void onMobsDie(Mob mob) {
        if (Random.Float()<getFinallyRate()){
            float hp = mob.HT * getFinallyRate();
            Dungeon.hero.heal(hp);
        }

    }

}
