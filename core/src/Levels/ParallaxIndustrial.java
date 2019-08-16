package Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.CrazyTimeTraveler;
import entities.PlaneEnemy;
import entities.Player;
import entities.Portal;
import screens.GameOverScreen;
import utils.Assets;

public class ParallaxIndustrial extends AbstractLevel{

    public ParallaxIndustrial(final CrazyTimeTraveler game) {
        super(game);

        layers = new TextureRegion[]{ atlas.findRegion(Assets.INDUSTRIAL_BACKGROUND), atlas.findRegion(Assets.INDUSTRIAL_FAR_BACK_TOWERS),
                                        atlas.findRegion(Assets.INDUSTRIAL_UNFINISHED_TOWERS), atlas.findRegion(Assets.INDUSTRIAL_BUILDINGS)};

        layersX = new float[4];
        layersY = new float[4];

        layersWidth = new float[4];
        layersHeight = new float[4];

        for( int i = 0; i < layersX.length; i++){
           layersX[i] = 0;
        }
        for( int i = 0; i < layersY.length; i++){
            layersY[i] = 0;
        }
        for( int i = 0; i < layersWidth.length; i++){
            layersWidth[i] = layers[i].getRegionWidth() * 3f;
        }
        for( int i = 0; i < layersHeight.length; i++){
            layersHeight[i] = layers[i].getRegionHeight() * 3f;
        }
    }

    @Override
    public void update(float delta) {

        delta = Gdx.graphics.getDeltaTime();

        layersX[1] -= 20 * delta;
        layersX[2] -= 40 * delta;
        layersX[3] -= 65 * delta;

        if(layersX[1] < -layersWidth[1])
            layersX[1] = 0;

        if(layersX[2] < -layersWidth[2])
            layersX[2] = 0;

        if(layersX[3] < -layersWidth[3])
            layersX[3] = 0;
    }

    @Override
    public void draw(SpriteBatch batch) {

        for(int i = 0; i < layers.length; i++){
            batch.draw(layers[2], layersX[2] + layersWidth[2], layersY[2], layersWidth[2], layersHeight[2]);
            batch.draw(layers[i], layersX[i], layersY[i], layersWidth[i], layersHeight[i]);
        }

        batch.draw(layers[3], layersX[3] + layersWidth[3], layersY[3], layersWidth[3], layersHeight[3]);
    }
}
