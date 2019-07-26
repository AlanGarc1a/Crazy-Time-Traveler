package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.CrazyTimeTraveler;

public abstract class AbstractLevel {

    protected CrazyTimeTraveler game;
    protected TextureAtlas atlas, atlas2;

    protected TextureRegion[] layers;
    protected float[] layersX;
    protected float[] layersY;
    protected float[] layersWidth;
    protected float[] layersHeight;


    public AbstractLevel(final CrazyTimeTraveler game){
        this.game = game;

        atlas = game.assets.getTextureAtlas();
        atlas2 = game.assets.getTextureAtlas2();
    }

    public abstract void update(float delta);
    public abstract void draw(SpriteBatch batch);
}
