package Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class ParallaxMountains extends AbstractLevel {

    public ParallaxMountains(CrazyTimeTraveler game) {
        super(game);

        layers = new TextureRegion[]{ atlas2.findRegion(Assets.MOUNTAIN_BG), atlas2.findRegion(Assets.MOUNTAIN_MOUNTAIN_FAR), atlas2.findRegion(Assets.MOUNTAIN_MOUNTAINS),
                                      atlas2.findRegion(Assets.MOUNTAIN_TREES), atlas2.findRegion(Assets.MOUNTAIN_FOREGROUND)};

        layersX = new float[5];
        layersY = new float[5];

        layersWidth = new float[5];
        layersHeight = new float[5];

        for( int i = 0; i < layersX.length; i++){
            layersX[i] = 0;
        }
        for( int i = 0; i < layersY.length; i++){
            layersY[i] = 0;
        }
        for( int i = 0; i < layersWidth.length; i++){
            layersWidth[i] = layers[i].getRegionWidth() * 3.0f;
        }
        for( int i = 0; i < layersHeight.length; i++){
            layersHeight[i] = layers[i].getRegionHeight() * 3.0f;
        }
    }

    @Override
    public void update(float delta) {
        delta = Gdx.graphics.getDeltaTime();

        for(int i = 0; i < layers.length; i++){

            layersX[4] -= 25 * delta;

            if(layersX[4] < -layersWidth[4])
                layersX[4] = 0;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {

        for(int i = 0; i < layers.length; i++){
            batch.draw(layers[i], layersX[i], layersY[i], layersWidth[i], layersHeight[i]);
        }
        batch.draw(layers[4], layersX[4] + layersWidth[4], layersY[4], layersWidth[4], layersHeight[4]);
    }
}
