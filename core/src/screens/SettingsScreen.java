package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.CrazyTimeTraveler;

public class SettingsScreen extends AbstractScreen {

    private Stage settingsStage;
    private Table settingsTable;

    private Label settingsLabel;
    private TextButton soundsButton;
    private TextButton musicButton;
    private TextButton backButton;

    public SettingsScreen(CrazyTimeTraveler game) {
        super(game);
    }

    @Override
    public void show() {
        settingsStage = new Stage(game.viewport);
        settingsTable = new Table();

        settingsLabel = new Label("Settings", style);
        soundsButton = new TextButton("Sound On", buttonStyle);
        musicButton = new TextButton("Music On", buttonStyle);
        backButton = new TextButton("BACK", buttonStyle);

        root.setFillParent(true);

        root.add(settingsLabel).padBottom(30f);
        root.row();
        root.add(soundsButton).padBottom(25f);
        root.row();
        root.add(musicButton).padBottom(25f);
        root.row();
        root.add(backButton);

        settingsStage.addActor(root);

        Gdx.input.setInputProcessor(settingsStage);
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

        backButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new MenuScreen(game));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }
}
