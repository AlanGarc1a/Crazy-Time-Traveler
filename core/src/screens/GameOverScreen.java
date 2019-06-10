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

public class GameOverScreen extends AbstractScreen {

    private Stage gameOverStage;
    private Table gameOverTable;
    private Label gameOverLabel;
    private TextButton playAgainButton;
    private TextButton mainMenuButton;

    public GameOverScreen(CrazyTimeTraveler game) {
        super(game);
    }

    @Override
    public void show() {
        gameOverStage = new Stage(game.viewport);
        gameOverTable = new Table();

        gameOverLabel = new Label("GAME OVER", style);
        playAgainButton = new TextButton("PLAY AGAIN", buttonStyle);
        mainMenuButton = new TextButton("MAIN MENU", buttonStyle);

        root.setFillParent(true);
        root.add(gameOverLabel).padBottom(20f);
        root.row();
        gameOverTable.add(mainMenuButton).padRight(10f);
        gameOverTable.add(playAgainButton).padLeft(15f);
        root.add(gameOverTable);

        gameOverStage.addActor(root);

        Gdx.input.setInputProcessor(gameOverStage);
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateScreen();

        gameOverStage.act();
        gameOverStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameOverStage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        gameOverStage.dispose();
    }

    private void updateScreen(){
        playAgainButton.addListener(new InputListener(){

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

        mainMenuButton.addListener(new InputListener(){

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
