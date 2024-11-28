package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.props.Prop;
import com.watabou.noosa.Image;
import com.watabou.noosa.TextureFilm;

public class PropIcon extends Image {

    private static TextureFilm smallFilm;
    private static final int SML_SIZE = 7;

    private static TextureFilm largeFilm;
    private static final int LRG_SIZE = 16;

    private final boolean large;

    public PropIcon(Prop prop,boolean large){
        super();
        this.large = large;
        refresh(prop);
    }

    public PropIcon(int icon,boolean large){
        super( large ? Assets.Interfaces.PROPS_LARGE : Assets.Interfaces.PROPS_SMALL );
        this.large = large;
        refresh(icon);
    }

    public void refresh(Prop prop){
        refresh(prop.icon());
        prop.tintIcon(this);
    }

    public void refresh(int icon){
        if (large){
            if (largeFilm == null) largeFilm = new TextureFilm(texture, LRG_SIZE, LRG_SIZE);
            frame(largeFilm.get(icon));
        } else {
            if (smallFilm == null ) smallFilm = new TextureFilm(texture, SML_SIZE, SML_SIZE);
            frame(smallFilm.get(icon));
        }
    }
}
