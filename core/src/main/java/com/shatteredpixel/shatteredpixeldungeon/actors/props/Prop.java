package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIndicator;
import com.watabou.noosa.Image;
import com.watabou.utils.Bundle;

public class Prop extends Actor {
    {
        actPriority = PROP_PRIO_AFTER; //low priority, towards the end of a turn
    }

    public float rate;
    public float rateByLevel;
    public float value;
    public float valueByLevel;
    public int level =1;
    public int maxLevel = 1;
    public boolean IsMaxLevel(){
        return maxLevel <= level;
    }

    public float getFinallyRate(){
        return rate+(level -1)* rateByLevel;
    }
    public float getFinallyValue(){
        return value+(level -1)* valueByLevel;
    }
    public Prop setLevel(int level){
        this.level = level;
        return this;
    }
    public Prop levelUp(int level){
        if (level<=0) return this;
        this.level+=level;
        if (this.level>this.maxLevel)
            this.level=maxLevel;
        return this;
    }

    //显示
    public int icon(){
        return PropIndicator.NONE;
    }
    public String name() {
        StringBuilder ext = new StringBuilder(" ");
        if (level<maxLevel){
            ext.append(level).append("/").append(maxLevel);
        } else{
            int dis = level-maxLevel;
            ext.append("MAX ");
            for (int i = 0; i < dis; i++) {
                ext.append("+");
            }
        }
        return Messages.get(this, "name")+ext;
    }

    public String desc(){
        return Messages.get(this, "desc");
    }

    public String iconTextDisplay(){
        return level >1? level +"":"";
    }
    public String logText(){
        return Messages.get(this,"show");
    }

    //some buffs may want to tint the base texture color of their icon
    public void tintIcon( Image icon ){
        //do nothing by default
    }



    //逻辑
    public boolean act() {
        return false;
    }

    public void onAdd() {

    }

    public void onRemove() {

    }
    public void onKill() {

    }
    public void onAttack() {

    }

    public void onAttack(Char ch) {

    }

    public void onMobsDie(Mob mob) {

    }
    public void onDelay(float time) {

    }
    public void onDefense() {

    }
    public void onDamaged() {

    }
    public void beforeDamaged() {

    }

    //读写存档
//    private static final String PROP_RATE    = "prop_rate";
//    private static final String PROP_VALUE    = "prop_value";
    private static final String PROP_LEVEL = "prop_level";
    @Override
    public void storeInBundle(Bundle bundle) {
        super.storeInBundle(bundle);
//        bundle.put(PROP_RATE,rate);
//        bundle.put(PROP_VALUE,value);
        bundle.put(PROP_LEVEL, level);
    }

    @Override
    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
//        rate = bundle.getFloat(PROP_RATE);
//        value = bundle.getFloat(PROP_VALUE);
        level = bundle.getInt(PROP_LEVEL);
    }
}
