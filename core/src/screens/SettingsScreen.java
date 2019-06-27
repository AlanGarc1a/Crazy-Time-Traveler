package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

public class SettingsScreen extends AbstractScreen {

    private Stage settingsStage;
    private Table settingsTable;

    private Label settingsLabel;
    private TextButton musicButton;
    private TextButton backButton;

    public SettingsScreen(CrazyTimeTraveler game) {
        super(game);

        settingsLabel = new Label("Settings", style);
        musicButton = new TextButton(null, buttonStyle);
        backButton = new TextButton("Back", buttonStyle);

        musicButton.setChecked(!game.preferences.isMusicEnabled());

        if(game.preferences.isMusicEnabled()){
            musicButton.setText("Music On");
        } else {
            musicButton.setColor(Color.RED);
            musicButton.setText("Music Off");
        }
    }

    @Override
    public void show() {
        createSettingsMenu();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        settingsStage.act();
        settingsStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        settingsStage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        settingsStage.dispose();
    }

    private void updateScreen(){

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });

        musicButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                if(musicButton.isChecked()){
                    game.preferences.setMusicState(false);
                    musicButton.setText("Music Off");
                    game.assets.gameMusic.stop();
                }
                else {
                    game.preferences.setMusicState(true);
                    musicButton.setText("Music On");
                    game.assets.gameMusic.setVolume(1f);
                    game.assets.gameMusic.play();
                }
            }
        });
    }

    private void createSettingsMenu(){
        settingsStage = new Stage(game.viewport);
        settingsTable = new Table();

        root.setFillParent(true);
        root.add(settingsLabel).padBottom(30f);
        root.row();
        root.add(musicButton).padBottom(25f);
        root.row();
        root.add(backButton);

        settingsStage.addActor(root);

        Gdx.input.setInputProcessor(settingsStage);
    }
}
