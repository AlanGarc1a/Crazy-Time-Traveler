package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class PlaneEnemy extends EnemyObject {

    private Animation<TextureRegion> flyingAnimation;
    private Animation<TextureRegion> deathAnimation;

    private float randomX;
    private float randomY;

    public PlaneEnemy(CrazyTimeTraveler game) {
        super(game);

        flyingAnimation = new Animation<TextureRegion>(1 / 15f, atlas.findRegions(Assets.PLANE), Animation.PlayMode.LOOP);
        deathAnimation = new Animation<TextureRegion>(1/15f, atlas.findRegions(Assets.PLANE));

        for (TextureRegion region : flyingAnimation.getKeyFrames()) {
            if (!region.isFlipX())
                region.flip(true, false);
        }

        randomX = MathUtils.random(595, CrazyTimeTraveler.GAME_WIDTH );
        randomY = MathUtils.random(0, game.camera.viewportHeight);

        position = new Vector2(randomX, randomY);

        WIDTH = 50;
        HEIGHT = 50;
    }

    @Override
    public void update() {
        position.x -= 150 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(SpriteBatch batch){

        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw(flyingAnimation.getKeyFrame(elapsedTime, true), position.x, position.y, WIDTH, HEIGHT);
    }

    @Override
    public void reset() {
        position.x = MathUtils.random(595, CrazyTimeTraveler.GAME_WIDTH);
        position.y = MathUtils.random(0, CrazyTimeTraveler.GAME_HEIGHT - HEIGHT);
    }
}
