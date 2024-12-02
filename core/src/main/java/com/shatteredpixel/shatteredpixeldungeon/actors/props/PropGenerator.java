package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.sun.tools.javac.code.Attribute;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropGenerator {

    public static ArrayList<Class<? extends Prop>> COMMON;
    public static ArrayList<Class<? extends Prop>> UNCOMMON;
    public static ArrayList<Class<? extends Prop>> RARE;
    public static ArrayList<Class<? extends Prop>> BOSS;
    public static boolean isInit = false;
    public static final float[] typeChances = new float[]{
            50,
            35,
            15
    };
    private static void init(){
        if (!isInit){
            // 扫描指定包（这里使用当前包）
            Reflections reflections = new Reflections("com.shatteredpixel.shatteredpixeldungeon.actors.props");  // 替换为你的包名

            // 获取所有继承自Prop的类
            List<Class<? extends Prop>> subclasses = new ArrayList<>(reflections.getSubTypesOf(Prop.class));

            // 筛选出带有 @PropAnnotation 注解的类
            for (Class<? extends Prop> cl:subclasses){
                if (cl.isAnnotationPresent(PropAnnotation.class)){
                    PropAnnotation p = cl.getAnnotation(PropAnnotation.class);
                    if (!p.isSomeHeroClassOnly() || p.heroClass() == Dungeon.hero.heroClass){

                        if (p.type()==PropRareType.Common)
                            COMMON.add(cl);

                        if (p.type()==PropRareType.UnCommon)
                            UNCOMMON.add(cl);

                        if (p.type()==PropRareType.Rare)
                            RARE.add(cl);

                        if (p.type()==PropRareType.Boss)
                            BOSS.add(cl);
                    }
                }
            }
            isInit = true;
        }
    }
    public static Prop random() {
        init();
        ArrayList<Class<? extends Prop>> filter = new ArrayList<>();
        for (Prop prop:Dungeon.hero.props()){
            if (prop.IsMaxLevel())
                filter.add(prop.getClass());
        }
        ArrayList<Class<? extends Prop>> rare = new ArrayList<>(RARE);
        ArrayList<Class<? extends Prop>> unCommon = new ArrayList<>(UNCOMMON);
        ArrayList<Class<? extends Prop>> common = new ArrayList<>(COMMON);
        rare.removeAll(filter);
        unCommon.removeAll(filter);
        common.removeAll(filter);

        if (common.isEmpty()&&unCommon.isEmpty()&&rare.isEmpty())
            return new PropNone();

        switch(Random.chances(typeChances)){
            case 0: default:
                return randomCommon( common );
            case 1:
                return randomUncommon( unCommon );
            case 2:
                return randomRare( rare );
        }
    }
    private static Prop randomRare(ArrayList<Class<? extends Prop>> rare) {
        if (rare.isEmpty())
            return random();
        return Reflection.newInstance(Random.element(rare));
    }

    private static Prop randomUncommon(ArrayList<Class<? extends Prop>> unCommon) {
        if (unCommon.isEmpty())
            return random();
        return Reflection.newInstance(Random.element(unCommon));
    }

    private static Prop randomCommon(ArrayList<Class<? extends Prop>> common) {
        if (common.isEmpty())
            return random();
        return Reflection.newInstance(Random.element(common));
    }

    public static Prop randomBoss() {
        init();
        ArrayList<Class<? extends Prop>> filter = new ArrayList<>();
        for (Prop prop:Dungeon.hero.props()){
            if (prop.IsMaxLevel())
                filter.add(prop.getClass());
        }
        ArrayList<Class<? extends Prop>> boss = new ArrayList<>(BOSS);
        boss.removeAll(filter);
        if (boss.isEmpty())
            return random();
        return Reflection.newInstance(Random.element(boss));
    }
}
