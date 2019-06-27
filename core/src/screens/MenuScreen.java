package screens;

import Levels.ParallaxIndustrial;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.CrazyTimeTraveler;

public class MenuScreen extends AbstractScreen {

    private Stage menuStage;
    private Table menuTable;

    private Label title;
    private TextButton play;
    private TextButton about;
    private TextButton settings;
    private Label highScore;
    private ParallaxIndustrial parallaxIndustrial;

    public MenuScreen(CrazyTimeTraveler game) {
        super(game);
        parallaxIndustrial = new ParallaxIndustrial(game);
    }

    @Override
    public void show() {
        if(game.preferences.isMusicEnabled()){
            game.assets.gameMusic.setVolume(1f);
            game.assets.gameMusic.setLooping(true);
            game.assets.gameMusic.play();
        }

        createMenu();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(game.camera.combined);

        updateScreen();

        game.batch.begin();
        parallaxIndustrial.draw(game.batch);
        game.batch.end();

        menuStage.act();
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuStage.getViewport().update(width, height, true);
        game.camera.update();
    }

    @Override
    public void hide(){
        dispose();
    }

    @Override
    public void dispose() {
        menuStage.dispose();
    }

    private void updateScreen(){

        about.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new AboutScreen(game));
            }
        });

        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new SettingsScreen(game));
            }
        });

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.assets.gameMusic.stop();
                game.setScreen(new GameScreen(game));
            }
        });
    }

    private void createMenu(){
        menuStage = new Stage(game.viewport);
        menuTable = new Table();

        title = new Label("CRAZY TIME TRAVELER", style);
        highScore = new Label("HighScore: " + highScore, style);
        play = new TextButton("PLAY", buttonStyle);
        about = new TextButton("ABOUT", buttonStyle);
        settings = new TextButton("SETTINGS", buttonStyle);

        root.setFillParent(true);
        root.add(title).padBottom(50f);
        root.row();
        menuTable.defaults().center().padLeft(20).padRight(20).uniformX();
        menuTable.add(settings);
        menuTable.add(play);
        menuTable.add(about);
        root.add(menuTable);

        menuStage.addActor(root);

        Gdx.input.setInputProcessor(menuStage);
    }
}
