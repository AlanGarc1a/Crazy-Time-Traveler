package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.game.CrazyTimeTraveler;

public class AboutScreen extends AbstractScreen {

    private Stage aboutStage;

    private Label about;
    private Label developer;
    private Label gameArtist, musicArtist;
    private Label fiverrAccount;
    private TextButton back;

    public AboutScreen(CrazyTimeTraveler game) {
        super(game);
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        //requestBuilder.url()
    }

    @Override
    public void show() {
        aboutStage = new Stage(game.viewport);

        about = new Label("ABOUT", style);
        developer = new Label("Developer: Alan Garcia", style);
        gameArtist = new Label("Game Artists: deulamco", style);
        fiverrAccount = new Label("https://www.fiverr.com/deulamco", style);
        musicArtist = new Label("Music: Eric Skiff - Song Name - Prologue - Available at http://EricSkiff.com/music", style);
        musicArtist.setFontScale(0.5f);
        back = new TextButton("BACK", buttonStyle);

        root.setFillParent(true);
        root.add(about).padBottom(20f);
        root.row();
        root.add(developer).padBottom(10f);
        root.row();
        root.add(gameArtist).padBottom(10f);
        root.row();
        root.add(fiverrAccount).padBottom(10);
        root.row();
        root.add(musicArtist).padBottom(12f);
        root.row();
        root.add(back);
        aboutStage.addActor(root);

        Gdx.input.setInputProcessor(aboutStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        aboutStage.act();
        aboutStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        aboutStage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        aboutStage.dispose();
    }

    private void updateScreen(){

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }
}
