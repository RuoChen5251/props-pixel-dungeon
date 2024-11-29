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
        return Messages.get(this, "desc",(int)(getFinallyRate()*100),(int)getFinallyValue());
    }

    @Override
    public void onKill() {
        if (Random.Float(0,1)<getFinallyRate()){
            Hero hero = Dungeon.hero;
            int healAmt = Math.min((int)getFinallyValue(),hero.HT-hero.HP);
            if (healAmt>0){
                hero.HP += healAmt;
                hero.sprite.showStatusWithIcon( CharSprite.POSITIVE, Integer.toString( healAmt ), FloatingText.HEALING );
            }
        }
    }

}
