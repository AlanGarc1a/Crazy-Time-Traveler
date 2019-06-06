package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.CrazyTimeTraveler;

public class MenuScreen extends AbstractScreen {

    private Stage menuStage;
    private Table menuTable;

    private Label title;
    private TextButton play;
    private TextButton about;
    private TextButton settings;

    public MenuScreen(CrazyTimeTraveler game) {
        super(game);

    }

    @Override
    public void show() {
        menuStage = new Stage(game.viewport);
        menuTable = new Table();

        title = new Label("CRAZY TIME TRAVELER", style);
        play = new TextButton("PLAY", buttonStyle);
        about = new TextButton("ABOUT", buttonStyle);
        settings = new TextButton("SETTINGS", buttonStyle);

        root.setFillParent(true);
        root.add(title).padBottom(30f);
        root.row();
        menuTable.add(settings).padRight(25f);
        menuTable.add(play).pad(10f);
        menuTable.add(about).padLeft(25f);
        root.add(menuTable);

        menuStage.addActor(root);

        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        menuStage.act();
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuStage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        menuStage.dispose();
    }

    private void updateScreen(){

        about.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new AboutScreen(game));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                super.touchUp(event, x, y, pointer, button);
            }
        });

        play.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new GameScreen(game));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                super.touchUp(event, x, y, pointer, button);
            }
        });
    }
}
