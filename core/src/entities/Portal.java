package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class Portal{

    private CrazyTimeTraveler game;
    private TextureAtlas atlas2;
    private Animation<TextureRegion> portalRegions;
    private float elapsedTime;
    private float speed = 45f;
    private Vector2 position;
    private int width, height;

    public Portal(CrazyTimeTraveler game) {
        this.game = game;
        atlas2 = game.assets.getTextureAtlas2();
        portalRegions = new Animation<TextureRegion>(1 / 15f, atlas2.findRegions(Assets.PORTAL), Animation.PlayMode.LOOP);
        width = 64;
        height = 64;
        position = new Vector2();
        position.x = MathUtils.random(CrazyTimeTraveler.GAME_WIDTH, 610);
        position.y = MathUtils.random(200, 350);
    }

    public void update() {

        position.x -= speed * Gdx.graphics.getDeltaTime();
    }

    public void draw(SpriteBatch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.draw(portalRegions.getKeyFrame(elapsedTime, true), position.x, position.y, width ,height);
    }

    public Rectangle getBounds(){ return new Rectangle(position.x, position.y, width, height); }
}
