package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.CrazyTimeTraveler;
import utils.Assets;

public class CyberPunkWorld extends AbstractLevel {


    public CyberPunkWorld(CrazyTimeTraveler game) {
        super(game);

        layers = new TextureRegion[]{ atlas.findRegion(Assets.CYBER_FAR_BUILDINGS), atlas.findRegion(Assets.CYBER_BACK_BUILDINGS),
                                        atlas.findRegion(Assets.CYBER_FOREGROUND)};

        layersX = new float[3];
        layersY = new float[3];

        layersWidth = new float[3];
        layersHeight = new float[3];

        for( int i = 0; i < layersX.length; i++){
            layersX[i] = 0;
        }
        for( int i = 0; i < layersY.length; i++){
            layersY[i] = 0;
        }
        for( int i = 0; i < layersWidth.length; i++){
            layersWidth[i] = layers[i].getRegionWidth() * 2.7f;
        }
        for( int i = 0; i < layersHeight.length; i++){
            layersHeight[i] = layers[i].getRegionHeight() * 2.7f;
        }
    }

    @Override
    public void update(float delta) {
        for(int i = 0; i < layers.length; i++){

            layersX[2] -= 25 * delta;

            if(layersX[2] < -layersWidth[2])
                layersX[2] = 0;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        for(int i = 0; i < layers.length; i++){
            batch.draw(layers[i], layersX[i], layersY[i], layersWidth[i], layersHeight[i]);
        }

        batch.draw(layers[2], layersX[2] + layersWidth[2], layersY[2], layersWidth[2], layersHeight[2]);
    }
}
