package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class Player extends GameObject {

    private float elapsedTime;
    private Animation<TextureRegion> flyingAnimation;
    private Animation<TextureRegion> deathAnimation;

    private static final int SPEED = 200;
    private static final float GRAVITY = 150f;

    private float STARTING_X;
    private float STARTING_Y;

    private float WIDTH = 50;
    private float HEIGHT = 50;

    private boolean alive;
    private int deathTime = 5;

    public Player(CrazyTimeTraveler game, Vector2 position, Vector2 direction) {
        super(game, position, direction);

        alive = true;

        STARTING_X = 50;
        STARTING_Y = 150;

        this.position = new Vector2(STARTING_X,STARTING_Y);

        flyingAnimation = new Animation(1/15f, atlas.findRegions(Assets.JET), Animation.PlayMode.LOOP);
        deathAnimation = new Animation<TextureRegion>(1/15f, atlas.findRegions(Assets.JET_DEATH), Animation.PlayMode.LOOP);
    }

    @Override
    public void update() {

        position.add(direction);

        if(position.y <= 0)
            position.y = 0;

        if(position.y >= game.camera.viewportHeight)
            position.y = game.camera.viewportHeight - HEIGHT;

        if(Gdx.input.isTouched()){
            setDirection(0, SPEED);
        } else {
            setDirection(0, -GRAVITY);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        if(alive) {
            batch.draw(flyingAnimation.getKeyFrame(elapsedTime, true), position.x, position.y, WIDTH, HEIGHT);
        } else {
            alive = false;
            deathTime--;
            if(deathTime < 0) {
                batch.draw(deathAnimation.getKeyFrame(elapsedTime, true), position.x, position.y);
            }
        }
    }
}
