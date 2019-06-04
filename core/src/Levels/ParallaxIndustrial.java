package Levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class ParallaxIndustrial {

    CrazyTimeTraveler game;

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;

    private TextureAtlas atlas;

    private TextureRegion background;
    private TextureRegion farTowers;
    private TextureRegion unfinishedTowers;
    private TextureRegion buildings;

    private float x1, x2;
    private int speed;
    private int goalSpeed;
    private float imageScale;
    private boolean speedFixed;

    public ParallaxIndustrial(CrazyTimeTraveler game){
        this.game = game;

        atlas = game.assets.getTextureAtlas();
        background = atlas.findRegion(Assets.INDUSTRIAL_BACKGROUND);
        farTowers = atlas.findRegion(Assets.INDUSTRIAL_FAR_BACKTOWERS);
        unfinishedTowers = atlas.findRegion(Assets.INDUSTRIAL_UNFINISHED_TOWERS);
        buildings = atlas.findRegion(Assets.INDUSTRIAL_BUILDINGS);

        x1 = 0;
        x2 = background.getRegionWidth();
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        imageScale = CrazyTimeTraveler.GAME_WIDTH;
        speedFixed = true;
    }

    public void updateAnddraw(SpriteBatch batch ,float deltaTime){
        //Speed adjustment to reach goal
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        x1 -= speed * deltaTime;
        x2 -= speed * deltaTime;

        //if image reached the left edge of the screen and is not visible, put it back
        if (x1 + background.getRegionWidth() * imageScale <= 0)
            x1 = x2 + background.getRegionWidth() * imageScale;

        if (x2 + background.getRegionWidth() * imageScale <= 0)
            x2 = x1 + background.getRegionWidth() * imageScale;

        //Render
        batch.draw(background, x1, 0, background.getRegionWidth() * imageScale, CrazyTimeTraveler.GAME_HEIGHT );
        batch.draw(background, x2, 0, background.getRegionWidth() * imageScale, CrazyTimeTraveler.GAME_HEIGHT );
    }

    public void setSpeed (int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }
}
