package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.actors.props.Prop;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoBuff;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.audio.Sample;

public class PropButton extends IconButton {

    private Prop prop;

    private boolean large;

    public BitmapText textValue; //only for large

    public PropButton( Prop prop, boolean large ){
        super( new PropIcon(prop, large));
        this.prop = prop;
        this.large = large;

        bringToFront(textValue);
    }

    @Override
    protected void createChildren() {
        super.createChildren();

        textValue = new BitmapText(PixelScene.pixelFont);
        add( textValue );
    }

    public void updateIcon(){
        ((PropIcon)icon).refresh(prop);
        //round up to the nearest pixel if <50% faded, otherwise round down
        if (!prop.iconTextDisplay().isEmpty()) {
            textValue.visible = true;
            textValue.alpha(0.7f);
            textValue.text(prop.iconTextDisplay());
            textValue.measure();
        }
    }

    @Override
    protected void layout() {
        super.layout();

        if (textValue.width > width()){
            textValue.scale.set(PixelScene.align(0.5f));
        } else {
            textValue.scale.set(1f);
        }
        textValue.x = this.x;
        textValue.y = this.y+width()-textValue.height;
    }

    @Override
    protected void onClick() {
        if (prop.icon() != PropIndicator.NONE) GameScene.show(new WndInfoBuff(prop));
    }

    @Override
    protected void onPointerDown() {
        //don't affect buff color
        Sample.INSTANCE.play( Assets.Sounds.CLICK );
    }

    @Override
    protected void onPointerUp() {
        //don't affect buff color
    }

    @Override
    protected String hoverText() {
        return Messages.titleCase(prop.name());
    }
}