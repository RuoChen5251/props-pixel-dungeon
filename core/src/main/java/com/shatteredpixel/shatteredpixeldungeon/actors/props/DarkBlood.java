package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.NPC;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

@PropAnnotation(type = PropRareType.Rare)
public class DarkBlood extends Prop{
    {
        rate=0.1f;
        rateByLevel = 0.05f;
        value=0;
        valueByLevel =0;
    }

    @Override
    public int icon() {
        return PropIndicator.DARK_BLOOD;
    }

    @Override
    public void onAttack(Char ch) {
        if (ch instanceof NPC)
            return;
        if (Random.Float()<getFinallyRate()){
            float damage = Math.max(1,ch.HP*getFinallyRate());
            ch.HP-=(int)damage;
            value+=damage;
            ch.sprite.showStatusWithIcon( CharSprite.NEGATIVE, Integer.toString( (int)damage ), FloatingText.BLEEDING );
            PropIndicator.refreshHero();
        }
    }

    @Override
    public String iconTextDisplay() {
        return Messages.decimalFormat("#.#",getFinallyValue());
    }

    public int reduceDamage(int damage) {
        int dam = (int)getFinallyValue();
        if (dam>damage){
            value-=damage;
            GLog.p(Messages.get(this,"show1"));
            return 0;
        }
        value-=dam;
        GLog.p(Messages.get(this,"show"));
        return damage-dam;
    }

}
