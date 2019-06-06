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

public class AboutScreen extends AbstractScreen {

    private Stage aboutStage;
    private Table aboutTable;

    private Label about;
    private Label developer;
    private TextButton back;

    public AboutScreen(CrazyTimeTraveler game) {
        super(game);
    }

    @Override
    public void show() {
        aboutStage = new Stage(game.viewport);
        aboutTable = new Table();

        about = new Label("ABOUT", style);
        developer = new Label("DEVELOPER:ALAN GARCIA", style);
        back = new TextButton("BACK", buttonStyle);

        root.setFillParent(true);
        root.add(about).padBottom(10f);
        root.row();
        root.add(developer).padBottom(10f);
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

        back.addListener(new InputListener(){

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
