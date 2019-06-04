package utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    public static final AssetDescriptor<TextureAtlas> textureAtlas = new AssetDescriptor<TextureAtlas>("images/images.atlas", TextureAtlas.class);

    //sprites
    public static final String ALIEN_ENEMY = "alien_enemy";
    public static final String JET = "plane";
    public static final String JET_DEATH = "plane_die";
    public static final String PLANE = "plane2";

    //environment
    public static final String INDUSTRIAL_BACKGROUND = "green-background";
    public static final String INDUSTRIAL_FAR_BACKTOWERS = "far-back-towers";
    public static final String INDUSTRIAL_UNFINISHED_TOWERS = "unfinished-towers";
    public static final String INDUSTRIAL_BUILDINGS = "buildings";

    private AssetManager assetManager;

    public Assets(){
        assetManager = new AssetManager();
    }

    public void loadAssets(){
        assetManager.load(textureAtlas);
        assetManager.finishLoading();
    }

    public TextureAtlas getTextureAtlas(){
        return assetManager.get(textureAtlas);
    }

    public float getProgress(){
        return assetManager.getProgress();
    }

    public boolean update(){
        return assetManager.update();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

}
