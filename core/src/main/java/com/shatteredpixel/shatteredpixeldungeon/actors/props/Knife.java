package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.utils.Random;

@PropAnnotation
public class Knife extends Prop{
    {
        maxLevel = 10;
        rate=0.05f;
        rateByLevel=0.01f;
    }
    private int isSurprise = -1;
    public boolean getIsSurprise(){
        if (isSurprise==-1)
            isSurprise = Random.Float()<=getFinallyRate()?1:0;
        return isSurprise==1;
    }
    @Override
    public int icon() {
        return PropIndicator.DEFAULT;
    }

    @Override
    public void afterAttack(Char ch) {
        isSurprise=-1;
    }
}
