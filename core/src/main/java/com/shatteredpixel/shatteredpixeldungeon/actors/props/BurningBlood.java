package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Burning;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

public class BurningBlood extends Prop{
    {
        rate  = 0.15f;
        rateByCount = 0.05f;
        value = 3;
        valueByCount = 1;
        maxCount = 10;
    }

    @Override
    public int icon(){
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this, "desc"
                ,Messages.decimalFormat("#.##", getFinallyRate()*100)
                ,Messages.decimalFormat("#.##", getFinallyValue()));
    }

    @Override
    public void onKill() {
        if (Random.Float(0,1)<getFinallyRate()){
            Dungeon.hero.heal(getFinallyValue());
        }
    }

}
