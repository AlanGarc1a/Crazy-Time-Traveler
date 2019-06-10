package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class ParallaxIndustrial {

    CrazyTimeTraveler game;

    private TextureAtlas atlas;

    private TextureRegion backgroundImage;
    private TextureRegion farTowersImage;
    private TextureRegion unfinishedTowersImage;
    private TextureRegion buildingsImage;


    public ParallaxIndustrial(CrazyTimeTraveler game){
        this.game = game;

        atlas = game.assets.getTextureAtlas();

        backgroundImage = atlas.findRegion(Assets.INDUSTRIAL_BACKGROUND);
        farTowersImage = atlas.findRegion(Assets.INDUSTRIAL_FAR_BACK_TOWERS);
        unfinishedTowersImage = atlas.findRegion(Assets.INDUSTRIAL_UNFINISHED_TOWERS);
        buildingsImage = atlas.findRegion(Assets.INDUSTRIAL_BUILDINGS);
    }

    public void draw(SpriteBatch batch){
        batch.draw(backgroundImage, game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth() / 2, game.viewport.getScreenHeight() / 2);

        batch.draw(farTowersImage, game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth() / 2, game.viewport.getScreenHeight() / 2);

        batch.draw(unfinishedTowersImage, game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth() / 2, game.viewport.getScreenHeight() / 2);

        batch.draw(buildingsImage, game.viewport.getScreenX(), game.viewport.getScreenY(),
                game.viewport.getScreenWidth() / 2, game.viewport.getScreenHeight() / 2);
    }
}
