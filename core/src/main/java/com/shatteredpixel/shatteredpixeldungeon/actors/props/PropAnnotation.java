package com.shatteredpixel.shatteredpixeldungeon.actors.props;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PropAnnotation {

    HeroClass heroClass() default HeroClass.WARRIOR;

    boolean isSomeHeroClassOnly() default false;

    PropRareType type() default PropRareType.Common;

}
