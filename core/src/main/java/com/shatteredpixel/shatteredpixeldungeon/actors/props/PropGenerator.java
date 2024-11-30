package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.watabou.utils.Random;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PropGenerator {

    public static Class<? extends Prop>[] COMMON = new Class[]{
            BurningBlood.class, //燃烧之血
            UnyieldingHeart.class,//不屈之心
    };
    public static Class<? extends Prop>[] UNCOMMON = new Class[]{
            MembershipCard.class,//会员卡
            WholesalerCertification.class,//批发商证明
    };
    public static Class<? extends Prop>[] RARE = new Class[]{
            CorpseVine.class,//食尸藤
            DarkBlood.class,//黑暗之血
    };
    public static Class<? extends Prop>[] BOSS = new Class[]{

    };
    public static final float[] typeChances = new float[]{
            50,
            35,
            15
    };
    public static Prop random() {
        ArrayList<Class<? extends Prop>> filter = new ArrayList<>();
        for (Prop prop:Dungeon.hero.props()){
            if (!prop.canSuperposition())
                filter.add(prop.getClass());
        }
        ArrayList<Class<? extends Prop>> rare = new ArrayList<>(Arrays.asList(RARE));
        ArrayList<Class<? extends Prop>> unCommon = new ArrayList<>(Arrays.asList(UNCOMMON));
        ArrayList<Class<? extends Prop>> common = new ArrayList<>(Arrays.asList(COMMON));
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
        ArrayList<Class<? extends Prop>> filter = new ArrayList<>();
        for (Prop prop:Dungeon.hero.props()){
            if (!prop.canSuperposition())
                filter.add(prop.getClass());
        }
        ArrayList<Class<? extends Prop>> boss = new ArrayList<>(Arrays.asList(BOSS));
        boss.removeAll(filter);
        if (boss.isEmpty())
            return random();
        return Reflection.newInstance(Random.element(boss));
    }
}
