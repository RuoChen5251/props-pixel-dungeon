package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.props.Prop;
import com.shatteredpixel.shatteredpixeldungeon.actors.props.PropGenerator;
import com.shatteredpixel.shatteredpixeldungeon.actors.props.PropNone;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.ui.IconButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.PropIcon;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;

public class WndProp extends Window {
    private static final int WIDTH		= 120;
    private static final int BTN_SIZE	= 24;
    private static final int BTN_GAP	= 4;
    private static final int GAP		= 2;

    private static final int NUM_PROPS = 4; //last one is a random choice
    private static Prop[] props = new Prop[NUM_PROPS];
    public static final String KILL_BOSS = "kill_boss";
    public static final String KILL_ENEMY = "kill_enemy";
    public static final String CLEAR_ROOM = "clear_room";
    public static final String OTHER = "other";
    public WndProp(String text){

        IconTitle titlebar = new IconTitle();
        titlebar.icon(new PropIcon(new PropNone(),false));
        titlebar.label(Messages.get(this,"title"));
        titlebar.setRect(0, 0, WIDTH, 0);
        add( titlebar );

        RenderedTextBlock message = PixelScene.renderTextBlock( Messages.get(WndProp.class, text), 6 );
        message.maxWidth(WIDTH);
        message.setPos(0, titlebar.bottom() + GAP);
        add( message );

        for (int i = 0; i < NUM_PROPS; i++) {
            if (i==NUM_PROPS-1)
                props[i] = new PropNone();
            else
                props[i] = text.equals(KILL_BOSS)?PropGenerator.randomBoss():PropGenerator.random();
        }


        for (int i = 0; i < NUM_PROPS; i++){
            Prop cur = props[i];
            PropButton btnReward = new PropButton(cur,true ) {
                @Override
                protected void onClick() {
                    ShatteredPixelDungeon.scene().addToFront(new RewardWindow(cur));
                }
            };
            btnReward.setRect( (i+1)*(WIDTH - BTN_GAP) / NUM_PROPS - BTN_SIZE, message.top() + message.height() + BTN_GAP, BTN_SIZE, BTN_SIZE );
            add( btnReward );

        }

        resize(WIDTH, (int)(message.top() + message.height() + 2*BTN_GAP + BTN_SIZE));

    }
    @Override
    public void onBackPressed() {

    }

    private class RewardWindow extends WndInfoBuff {

        public RewardWindow( Prop item ) {
            super(item);

            RedButton btnConfirm = new RedButton(Messages.get(WndSadGhost.class, "confirm")){
                @Override
                protected void onClick() {
                    RewardWindow.this.hide();
                    WndProp.this.hide();

                    Dungeon.hero.add(item);
                }
            };
            btnConfirm.setRect(0, height+2, width/2-1, 16);
            add(btnConfirm);

            RedButton btnCancel = new RedButton(Messages.get(WndSadGhost.class, "cancel")){
                @Override
                protected void onClick() {
                    hide();
                }
            };
            btnCancel.setRect(btnConfirm.right()+2, height+2, btnConfirm.width(), 16);
            add(btnCancel);

            resize(width, (int)btnCancel.bottom());
        }

        @Override
        public void onBackPressed() {

        }
    }
}
