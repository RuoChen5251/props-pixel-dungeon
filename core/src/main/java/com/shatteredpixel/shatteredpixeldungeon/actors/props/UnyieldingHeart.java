package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

public class UnyieldingHeart extends Prop{
    {
        maxCount=10;
        value=1;
        valueByCount=1;
        rate=0.1f;
        rateByCount=0.01f;
    }

    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public String desc() {
        return Messages.get(this,"desc",
                Messages.decimalFormat("#.##",getFinallyRate()*100),
                Messages.decimalFormat("#.##",getFinallyValue()));
    }

    @Override
    public float getFinallyRate() {
        float leftHp = Dungeon.hero.HT-Dungeon.hero.HP;
        float otherRate = leftHp/Dungeon.hero.HT/5;
        return super.getFinallyRate()+otherRate;
    }

    @Override
    public void onDefense() {
        if (Random.Float(0,1)<getFinallyRate()){
            Dungeon.hero.heal(getFinallyValue());
        }
    }
}
