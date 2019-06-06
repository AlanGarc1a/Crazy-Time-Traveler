package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.game.CrazyTimeTraveler;

public class LoadingScreen extends AbstractScreen {

    private Stage loadingStage;
    private Table loadingTable;

    private Label.LabelStyle style;
    private Label loadingLabel;
    private Label percentageLabel;

    private float progress = 0f;
    private float timer = 0f;

    public LoadingScreen(CrazyTimeTraveler game) {
        super(game);

        style = new Label.LabelStyle();
        style.font = game.assets.silverFont;
    }

    @Override
    public void show() {
        loadingStage = new Stage(game.viewport);
        loadingTable = new Table();

        loadingLabel = new Label("Loading", style);
        percentageLabel = new Label(progress + "", style);

        loadingTable.setFillParent(true);
        loadingTable.add(loadingLabel);
        loadingTable.row();
        loadingTable.add(percentageLabel);

        loadingStage.addActor(loadingTable);

        game.assets.loadAssets();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        loadingStage.act();
        loadingStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        loadingStage.getViewport().update(width, height, true);
        loadingStage.getViewport().getCamera().update();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        loadingStage.dispose();
    }

    private void update(float delta){

        percentageLabel.setText(progress + "%");
        timer += delta;

        if(game.assets.update() && progress >= 99.99){
            if(timer > 3) {
                game.setScreen(new MenuScreen(game));
            }
        } else {
            progress = MathUtils.lerp(0, 100, game.assets.getProgress());
        }
    }
}
