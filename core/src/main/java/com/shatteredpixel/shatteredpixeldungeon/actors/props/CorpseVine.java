package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Regeneration;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
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
            plantGrass(mob.pos);
        }

    }
    private void plantGrass(int cell){
        int t = Dungeon.level.map[cell];
        if ((t == Terrain.EMPTY || t == Terrain.EMPTY_DECO || t == Terrain.EMBERS
                || t == Terrain.GRASS || t == Terrain.FURROWED_GRASS)
                && Dungeon.level.plants.get(cell) == null){
            if (!Regeneration.regenOn()){
                Level.set(cell, Terrain.FURROWED_GRASS);
            } else {
                Level.set(cell, Terrain.HIGH_GRASS);
            }
            GameScene.updateMap(cell);
            CellEmitter.get( cell ).burst( LeafParticle.LEVEL_SPECIFIC, 1 );
        }
    }

}
