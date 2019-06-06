package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;

public class Player extends GameObject {

    private float elapsedTime;
    private Animation<TextureRegion> flyingAnimation;
    private Animation<TextureRegion> deathAnimation;

    private static final int SPEED = 75;
    private static final float GRAVITY = 25f;

    private float STARTING_X;
    private float STARTING_Y;

    private float WIDTH = 22;
    private float HEIGHT = 22;

    public Player(CrazyTimeTraveler game, Vector2 position, Vector2 direction) {
        super(game, position, direction);

        STARTING_X = 50;
        STARTING_Y = 150;

        this.position = new Vector2(STARTING_X,STARTING_Y);

        flyingAnimation = new Animation(1/15f, atlas.findRegions("plane"), Animation.PlayMode.LOOP);
    }

    @Override
    public void update(float delta) {

        delta += Gdx.graphics.getDeltaTime();

        position.add(direction);

        if(position.y <= 0)
            position.y = 0;

        if(Gdx.input.isTouched()){
            setDirection(0, SPEED);
        } else {
            setDirection(0, -GRAVITY);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.draw(flyingAnimation.getKeyFrame(elapsedTime, true), position.x, position.y, WIDTH, HEIGHT);
    }
}
