package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;
import screens.GameOverScreen;
import screens.GameScreen;
import utils.Assets;

public class Player extends GameObject {

    public enum STATE {
        ALIVE,
        DEAD,
    }

    public STATE state;

    private float elapsedTime;
    private Animation<TextureRegion> flyingAnimation;
    private Animation<TextureRegion> deathAnimation;

    private static final int SPEED = 200;
    private static final float GRAVITY = 150f;

    private float STARTING_X;
    private float STARTING_Y;

    private int deathTime = 8;

    Sound explosionSound;

    //players score
    private int Score;

    public Player(CrazyTimeTraveler game, Vector2 position, Vector2 direction) {
        super(game, position, direction);

        state = STATE.ALIVE;

        WIDTH = 45;
        HEIGHT = 50;
        STARTING_X = 50;
        STARTING_Y = 150;

        this.position = new Vector2(STARTING_X,STARTING_Y);

        flyingAnimation = new Animation(1/15f, atlas.findRegions(Assets.JET), Animation.PlayMode.LOOP);
        deathAnimation = new Animation<TextureRegion>(1/15f, atlas.findRegions(Assets.JET_DEATH), Animation.PlayMode.LOOP);

        explosionSound = game.assets.getAssetManager().get(Assets.EXPLOSION_SOUND);
        Score = 0;
    }

    public int getDeathTime(){
        return deathTime;
    }

    public void setDeadState(){
        state = STATE.DEAD;
    }

    @Override
    public void update() {

        position.add(direction);

        if(position.y <= 0) {
            setDirection(0,0);
            //position.y = 0;
            explosionSound.play(0.1f);
            state = STATE.DEAD;
            deathTime -= 1;
            if(deathTime < 0)
                game.setScreen(new GameOverScreen(game, Score));
        }

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

        switch(state){
            case ALIVE:
                batch.draw(flyingAnimation.getKeyFrame(elapsedTime), position.x, position.y);
                break;
            case DEAD:
                batch.draw(deathAnimation.getKeyFrame(elapsedTime, false), position.x, position.y);
                break;
        }
    }

    public int getScore(){
        return Score;
    }

    public void addToScore() { Score++; }
}
