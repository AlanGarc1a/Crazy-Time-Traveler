package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.game.CrazyTimeTraveler;

public abstract class AbstractLevel {

    protected CrazyTimeTraveler game;
    protected TextureAtlas atlas;

    protected TextureRegion[] layers;
    protected float[] layersX;
    protected float[] layersY;
    protected float[] layersWidth;
    protected float[] layersHeight;

    protected int timerr;

    public AbstractLevel(final CrazyTimeTraveler game){
        this.game = game;

        atlas = game.assets.getTextureAtlas();
    }

    public void changeLevel(){

    }

    public abstract void update(float delta);
    public abstract void draw(SpriteBatch batch);
}
