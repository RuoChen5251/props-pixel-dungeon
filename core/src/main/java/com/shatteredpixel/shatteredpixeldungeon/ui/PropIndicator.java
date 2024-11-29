package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.props.Prop;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoBuff;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.ui.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class PropIndicator extends Component {

    //transparent icon
    public static final int NONE = 127;
    public static final int DEFAULT = 0;

    public static final int SIZE_SMALL  = 7;
    public static final int SIZE_LARGE  = 16;

    private static PropIndicator heroInstance;

    private LinkedHashMap<Prop, PropButton> propButtons = new LinkedHashMap<>();
    private boolean needsRefresh;
    private Char ch;

    private boolean large = false;

    
    public PropIndicator( Char ch, boolean large ) {
        super();

        this.ch = ch;
        this.large = large;
        if (ch == Dungeon.hero) {
            heroInstance = this;
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        if (this == heroInstance) {
            heroInstance = null;
        }
    }
    @Override
    public synchronized void update() {
        super.update();
        if (needsRefresh){
            needsRefresh = false;
            layout();
        }
    }
    private boolean propsHidden = false;
    @Override
    protected void layout() {

        ArrayList<Prop> propArrayList = new ArrayList<>();
        for (Prop prop : ch.props()) {
            if (prop.icon() != NONE) {
                propArrayList.add(prop);
            }
        }

        int size = large ? SIZE_LARGE : SIZE_SMALL;

        //remove any icons no longer present
        for (Prop prop : propButtons.keySet().toArray(new Prop[0])){
            if (!propArrayList.contains(prop)){
                Image icon = propButtons.get( prop ).icon;
                icon.originToCenter();
                icon.alpha(0.6f);
                add( icon );
                add( new AlphaTweener( icon, 0, 0.6f ) {
                    @Override
                    protected void updateValues( float progress ) {
                        super.updateValues( progress );
                        image.scale.set( 1 + 5 * progress );
                    }

                    @Override
                    protected void onComplete() {
                        image.killAndErase();
                    }
                } );

                propButtons.get( prop ).destroy();
                remove(propButtons.get( prop ));
                propButtons.remove( prop );
            }
        }

        //add new icons
        for (Prop buff : propArrayList) {
            if (!propButtons.containsKey(buff)) {
                PropButton icon = new PropButton(buff, large);
                add(icon);
                propButtons.put( buff, icon );
            }
        }

        //layout
        int pos = 0;
        float lastIconLeft = 0;
        for (PropButton icon : propButtons.values()){
            icon.updateIcon();
            //button areas are slightly oversized, especially on small buttons
            icon.setRect(x + pos * (size + 1), y, size + 1, size + (large ? 0 : 5));
            PixelScene.align(icon);
            pos++;

            icon.visible = icon.left() <= right();
            lastIconLeft = icon.left();
        }
        propsHidden = false;
        //squish buff icons together if there isn't enough room
        float excessWidth = lastIconLeft - right();
        if (excessWidth > 0) {
            float leftAdjust = excessWidth/(propButtons.size()-1);
            //can't squish by more than 50% on large and 62% on small
            if (large && leftAdjust >= size*0.48f) leftAdjust = size*0.5f;
            if (!large && leftAdjust >= size*0.62f) leftAdjust = size*0.65f;
            float cumulativeAdjust = leftAdjust * (propButtons.size()-1);

            ArrayList<PropButton> buttons = new ArrayList<>(propButtons.values());
            Collections.reverse(buttons);
            for (PropButton icon : buttons) {
                icon.setPos(icon.left() - cumulativeAdjust, icon.top());
                icon.visible = icon.left() <= right();
                if (!icon.visible) propsHidden = true;
                PixelScene.align(icon);
                bringToFront(icon);
                icon.givePointerPriority();
                cumulativeAdjust -= leftAdjust;
            }
        }
    }

    public boolean allPropsVisible(){
        return !propsHidden;
    }

    public static void refreshHero() {
        if (heroInstance != null) {
            heroInstance.needsRefresh = true;
        }
    }



}
