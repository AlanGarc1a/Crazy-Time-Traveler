package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.CrazyTimeTraveler;

public class SplashScreen extends AbstractScreen {

    private Stage splashStage;
    private Table splashTable;

    private Label.LabelStyle style;
    private Label libgdxLabel;

    private float timer = 0f;

    public SplashScreen(CrazyTimeTraveler game) {
        super(game);

        style = new Label.LabelStyle();
        style.font = game.assets.silverFont;
    }

    @Override
    public void show() {
        splashStage = new Stage(game.viewport);
        splashTable = new Table();

        libgdxLabel = new Label("Powered by Libgdx", style);

        splashTable.setFillParent(true);
        splashTable.add(libgdxLabel);

        splashStage.addActor(splashTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timer += delta;

        if(timer > 2){
            game.setScreen(new LoadingScreen(game));
        }

        splashStage.act();
        splashStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        splashStage.getViewport().update(width, height, true);
        splashStage.getCamera().update();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        splashStage.dispose();
    }
}
