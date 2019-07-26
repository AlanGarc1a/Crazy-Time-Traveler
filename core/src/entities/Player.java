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
    private boolean isDead;
    private int stateTimer;

    //players score
    private int Score;


    public Player(CrazyTimeTraveler game, Vector2 position, Vector2 direction) {
        super(game, position, direction);

        state = STATE.ALIVE;

        WIDTH = 40;
        HEIGHT = 40;
        STARTING_X = 50;
        STARTING_Y = 225;

        this.position = new Vector2(STARTING_X,STARTING_Y);

        flyingAnimation = new Animation<TextureRegion>(1/15f, atlas.findRegions(Assets.JET), Animation.PlayMode.LOOP);
        deathAnimation = new Animation<TextureRegion>(1/15f, atlas.findRegions(Assets.JET_DEATH), Animation.PlayMode.LOOP);

        stateTimer = 6;
        Score = 0;
    }

    @Override
    public void update() {

        position.add(direction);

        if(position.y <= 0) {
            die();
            position.y = 0;
            setDirection(0,position.y);
            stateTimer--;
            if(stateTimer < 0)
                game.setScreen(new GameOverScreen(game, Score));
        }

        if(position.y >= 460) {
            die();
            position.y = 460;
            setDirection(0, position.y);
            stateTimer--;
            if(stateTimer < 0)
                game.setScreen(new GameOverScreen(game, Score));
        }

        if(Gdx.input.isTouched() && state == STATE.ALIVE){
            setDirection(0, SPEED);
        } else {
            if(state == STATE.DEAD) {
                setDirection(0, position.y);
                stateTimer--;
                if(stateTimer < 0 && isDead()) {
                    game.setScreen(new GameOverScreen(game, Score));
                }
            }

            setDirection(0, -GRAVITY);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        elapsedTime += Gdx.graphics.getDeltaTime();

        state = getState();

        switch(state){
            case ALIVE:
                batch.draw(flyingAnimation.getKeyFrame(elapsedTime), position.x, position.y);
                break;
            case DEAD:
                batch.draw(deathAnimation.getKeyFrame(elapsedTime, false), position.x, position.y);
                break;
        }
    }

    public STATE getState(){

        if(isDead)
            return STATE.DEAD;
        else
            return STATE.ALIVE;
    }

    public void die(){

        if(!isDead()){
            state = STATE.DEAD;
            game.assets.explosion.play(1f);
            isDead = true;
        }
    }

    public int getStateTimer(){
        return stateTimer;
    }

    public boolean isDead(){
        return isDead;
    }

    public int getScore(){
        return Score;
    }

    public void addToScore() { Score++; }
}
