package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

    FileHandleResolver resolver = new InternalFileHandleResolver();
    public static final AssetDescriptor<TextureAtlas> textureAtlas = new AssetDescriptor<TextureAtlas>("images/images.atlas", TextureAtlas.class);

    //sprites
    public static final String ALIEN_ENEMY = "alien_enemy";
    public static final String JET = "plane";
    public static final String JET_DEATH = "plane_die";
    public static final String PLANE = "plane2";

    //environment
    public static final String INDUSTRIAL_BACKGROUND = "green-background";
    public static final String INDUSTRIAL_FAR_BACK_TOWERS = "far-back-towers";
    public static final String INDUSTRIAL_UNFINISHED_TOWERS = "unfinished-towers";
    public static final String INDUSTRIAL_BUILDINGS = "buildings";

    //second environment
    public static final String CYBER_BACK_BUILDINGS = "back-buildings";
    public static final String CYBER_FAR_BUILDINGS = "far-buildings";
    public static final String CYBER_FOREGROUND = "foreground";

    //fonts
    public static final String SILVER_FONT = "font/Silver.ttf";
    public static final String GRAVITY_FONT = "font/Gravity2.ttf";

    //sounds
    public static final String PICKUP_SOUND = "sounds/pickup.wav";
    public static final String EXPLOSION_SOUND = "sounds/explosion.wav";
    public final Sound pickUpSound = Gdx.audio.newSound(Gdx.files.internal(PICKUP_SOUND));
    public final Sound explosion = Gdx.audio.newSound(Gdx.files.internal(EXPLOSION_SOUND));

    //music
    public static final String MUSIC = "music/Prologue.mp3";
    public final Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal(MUSIC));

    public static final String PAUSE_BUTTON = "pause";

    private FreetypeFontLoader.FreeTypeFontLoaderParameter smallFont;
    private FreetypeFontLoader.FreeTypeFontLoaderParameter bigFont;

    public BitmapFont silverFont;
    public BitmapFont gravityFont;

    private AssetManager assetManager;

    public Assets() {
        assetManager = new AssetManager();

        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        smallFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        bigFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        smallFont.fontFileName = "font/Silver.ttf";
        bigFont.fontFileName = "font/Gravity2.ttf";
        smallFont.fontParameters.size = 40;
        bigFont.fontParameters.size = 22;
        assetManager.load(SILVER_FONT, BitmapFont.class, smallFont);
        assetManager.finishLoading();

        silverFont = assetManager.get("font/Silver.ttf", BitmapFont.class);
    }

    public void loadAssets(){
        assetManager.load(GRAVITY_FONT, BitmapFont.class, bigFont);
        assetManager.load(textureAtlas);
        loadSounds();
        loadMusic();
        assetManager.finishLoading();
    }

    private void loadSounds(){
        assetManager.load(PICKUP_SOUND, Sound.class);
        assetManager.load(EXPLOSION_SOUND, Sound.class);
    }

    private void loadMusic(){
        assetManager.load(MUSIC, Music.class);
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

    public AssetManager getAssetManager(){
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
