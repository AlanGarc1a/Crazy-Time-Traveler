package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class AlienEnemy extends EnemyObject {

    ENEMY_STATE enemy_state = ENEMY_STATE.ALIVE;

    private Animation<TextureRegion> flyingAnimation;

    private float randomX;
    private float randomY;


    public AlienEnemy(CrazyTimeTraveler game) {
        super(game);

        flyingAnimation = new Animation<TextureRegion>(1 / 15f, atlas.findRegions(Assets.ALIEN_ENEMY), Animation.PlayMode.LOOP);

        randomX = MathUtils.random(595, CrazyTimeTraveler.GAME_WIDTH );
        randomY = MathUtils.random(0, 445 - HEIGHT );

        position = new Vector2(randomX, randomY);

        WIDTH = 55;
        HEIGHT = 55;
    }

    @Override
    public void update() {
        position.x -= 150 * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void draw(SpriteBatch batch) {

        elapsedTime += Gdx.graphics.getDeltaTime();

        enemy_state = getState();

        switch (enemy_state){
            case ALIVE:
                batch.draw(flyingAnimation.getKeyFrame(elapsedTime), position.x, position.y, WIDTH, HEIGHT);
                break;
            case DEAD:
                break;
        }
    }

    public ENEMY_STATE getState(){

        if(isDead)
            return ENEMY_STATE.DEAD;
        else
            return ENEMY_STATE.ALIVE;
    }

    public void die(){

        if(!isDead()){
            isDead = true;
        }
    }

    @Override
    public void reset() {
        position.x = MathUtils.random(595, CrazyTimeTraveler.GAME_WIDTH);
        position.y = MathUtils.random(50, 425);
    }
}
