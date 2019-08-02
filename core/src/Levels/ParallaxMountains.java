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
            layersWidth[i] = layers[i].getRegionWidth() * 3.1f;
        }
        for( int i = 0; i < layersHeight.length; i++){
            layersHeight[i] = layers[i].getRegionHeight() * 3.1f;
        }
    }

    @Override
    public void update(float delta) {
        delta = Gdx.graphics.getDeltaTime();

        layersX[2] -= 20 * delta;
        layersX[3] -= 35 * delta;
        layersX[4] -= 45 * delta;


        if(layersX[2] < -layersWidth[2])
            layersX[2] = 0;
        if(layersX[3] < -layersWidth[3])
            layersX[3] = 0;
        if(layersX[4] < -layersWidth[4])
            layersX[4] = 0;
    }

    @Override
    public void draw(SpriteBatch batch) {

        for(int i = 0; i < layers.length; i++){
            batch.draw(layers[2], layersX[2] + layersWidth[2], layersY[2], layersWidth[2], layersHeight[2]);
            batch.draw(layers[3], layersX[3] + layersWidth[3], layersY[3], layersWidth[3], layersHeight[3]);
            batch.draw(layers[i], layersX[i], layersY[i], layersWidth[i], layersHeight[i]);
        }
        batch.draw(layers[4], layersX[4] + layersWidth[4], layersY[4], layersWidth[4], layersHeight[4]);
    }
}
