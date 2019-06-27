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

    private int backgroundWidth, backgroundHeight;
    private int farTowersWidth, farTowersHeight;
    private int unfinishedTowersWidth, unfinishedTowersHeight;
    private int buildingsWidth, buildingsHeight;
    private int buildingsX, buildingsY, farTowersX, farTowersY, unfinishedTowersX, unfinishedTowersY;


    public ParallaxIndustrial(CrazyTimeTraveler game){
        this.game = game;

        atlas = game.assets.getTextureAtlas();

        backgroundImage = atlas.findRegion(Assets.INDUSTRIAL_BACKGROUND);
        farTowersImage = atlas.findRegion(Assets.INDUSTRIAL_FAR_BACK_TOWERS);
        unfinishedTowersImage = atlas.findRegion(Assets.INDUSTRIAL_UNFINISHED_TOWERS);
        buildingsImage = atlas.findRegion(Assets.INDUSTRIAL_BUILDINGS);

        backgroundWidth = backgroundImage.getRegionWidth() * 3 ;
        backgroundHeight = backgroundImage.getRegionHeight() * 3 ;

        farTowersWidth = farTowersImage.getRegionWidth() * 3;
        farTowersHeight = farTowersImage.getRegionHeight()  * 3;

        unfinishedTowersWidth = unfinishedTowersImage.getRegionWidth() * 3 ;
        unfinishedTowersHeight = unfinishedTowersImage.getRegionHeight() * 3 ;

        buildingsWidth = buildingsImage.getRegionWidth()  * 3;
        buildingsHeight = buildingsImage.getRegionHeight()* 3 ;

        buildingsX = 0;
        buildingsY = 0;

        farTowersX = 0;
        farTowersY = 0;

        unfinishedTowersX = 0;
        unfinishedTowersY = 0;
    }

    public void update(){
        buildingsX -= 2;
        unfinishedTowersX -= 1;

        if(buildingsX < -buildingsWidth){
            buildingsX = 0;
        }

        if(unfinishedTowersX < -unfinishedTowersWidth){
            unfinishedTowersX = 0;
        }
    }

    public void draw(SpriteBatch batch){

        batch.draw(backgroundImage, 0 ,0, backgroundWidth, backgroundHeight);

        batch.draw(farTowersImage, farTowersX, farTowersY, farTowersWidth, farTowersHeight);

        batch.draw(unfinishedTowersImage, unfinishedTowersX, unfinishedTowersY, unfinishedTowersWidth, unfinishedTowersHeight);
        batch.draw(unfinishedTowersImage, unfinishedTowersX + unfinishedTowersWidth, unfinishedTowersY, unfinishedTowersWidth, unfinishedTowersHeight);

        batch.draw(buildingsImage, buildingsX, buildingsY, buildingsWidth, buildingsHeight);
        batch.draw(buildingsImage, buildingsX + buildingsWidth, buildingsY, buildingsWidth, buildingsHeight);
    }
}
