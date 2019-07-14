package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.CrazyTimeTraveler;

public class AboutScreen extends AbstractScreen {

    private Stage aboutStage;

    private Label about;
    private Label developer;
    private Label gameArtist, musicArtist;
    private TextButton back, musicLink, fiverrAccount, ansimuzAccoount;

    public AboutScreen(CrazyTimeTraveler game) {
        super(game);

    }

    @Override
    public void show() {
        createAbout();
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

        ansimuzAccoount.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(ansimuzAccoount.isChecked()) {
                    ansimuzAccoount.setChecked(false);
                    Gdx.net.openURI("https://ansimuz.itch.io/");
                }
            }
        });

        fiverrAccount.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(fiverrAccount.isChecked()) {
                    fiverrAccount.setChecked(false);
                    Gdx.net.openURI("https://www.fiverr.com/deulamco");
                }
            }
        });

        musicLink.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(musicLink.isChecked()) {
                    musicLink.setChecked(false);
                    Gdx.net.openURI("http://ericSkiff.com/music");
                }
            }
        });

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    private void createAbout(){
        aboutStage = new Stage(game.viewport);

        about = new Label("ABOUT", style);
        developer = new Label("Developer: Alan Garcia", style);
        gameArtist = new Label("Game Artists: deulamco and ansimuz", style);
        musicArtist = new Label("Music: Eric Skiff - Song Name - Prologue", style);
        fiverrAccount = new TextButton("https://www.fiverr.com/deulamco", buttonStyle);
        ansimuzAccoount = new TextButton("https://ansimuz.itch.io/", buttonStyle);
        musicLink = new TextButton("http://EricSkiff.com/music", buttonStyle);
        back = new TextButton("BACK", buttonStyle);

        developer.setFontScale(0.8f);
        about.setFontScale(1.2f);
        musicLink.setChecked(false);
        musicArtist.setFontScale(0.8f);
        ansimuzAccoount.setChecked(false);
        fiverrAccount.setChecked(false);
        gameArtist.setFontScale(0.8f);

        root.setFillParent(true);
        root.add(about).pad(10f);
        root.row();
        root.add(developer).padBottom(10f);
        root.row();
        root.add(gameArtist).padBottom(10f);
        root.row();
        root.add(musicArtist).padBottom(12f);
        root.row();
        root.add(fiverrAccount).padBottom(10f);
        root.row();
        root.add(ansimuzAccoount).padBottom(10f);
        root.row();
        root.add(musicLink).padBottom(12f);
        root.row();
        root.add(back).padBottom(25f);
        aboutStage.addActor(root);

        Gdx.input.setInputProcessor(aboutStage);
    }
}
