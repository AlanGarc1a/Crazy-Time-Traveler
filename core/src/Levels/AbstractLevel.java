package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.CrazyTimeTraveler;
import entities.Player;
import entities.Portal;

public abstract class AbstractLevel {

    protected CrazyTimeTraveler game;
    protected TextureAtlas atlas, atlas2;

    protected TextureRegion[] layers;
    protected float[] layersX;
    protected float[] layersY;
    protected float[] layersWidth;
    protected float[] layersHeight;
    protected Portal portal;

    public AbstractLevel(final CrazyTimeTraveler game){
        this.portal = new Portal(game);
        this.game = game;

        atlas = game.assets.getTextureAtlas();
        atlas2 = game.assets.getTextureAtlas2();
    }

    public abstract void update(float delta);
    public abstract void draw(SpriteBatch batch);
}
