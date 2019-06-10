package screens;

import Levels.World;
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

public class GameScreen extends AbstractScreen {

    public enum GAMESTATE {
        RUNNING,
        PAUSED,
        RESUMED,
    }

    private Stage pauseStage;
    private Table pauseTable;
    private Label pauseLabel;
    private TextButton resumeButton;
    private TextButton menuButton;

    GAMESTATE gameState;

    private World world;

    public GameScreen(CrazyTimeTraveler game) {
        super(game);

        gameState = GAMESTATE.RUNNING;
        world = new World(game);
    }

    @Override
    public void show() {
        pauseStage = new Stage(game.viewport);
        pauseTable = new Table();

        pauseLabel = new Label("PAUSED", style);
        resumeButton = new TextButton("RESUME", buttonStyle);
        menuButton = new TextButton("MAIN MENU", buttonStyle);

        root.setFillParent(true);
        root.add(pauseLabel).padBottom(30f);
        root.row();
        pauseTable.add(menuButton).padRight(15f);
        pauseTable.add(resumeButton);
        root.add(pauseTable);

        pauseStage.addActor(root);

        Gdx.input.setInputProcessor(pauseStage);
    }

    @Override
    public void render(float delta) {
        game.camera.update();
        Gdx.gl.glClearColor(1,0.3f,0.2f,0.9f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            gameState = GAMESTATE.PAUSED;
        }
        game.batch.setProjectionMatrix(game.camera.combined);

        switch (gameState){
            case RUNNING:
                world.update(delta);
                world.draw(game.batch, delta);
                break;
            case PAUSED:
                updatePausedState();
                world.draw(game.batch, delta);
                pauseStage.act();
                pauseStage.draw();
                break;
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height, true);
    }

    @Override
    public void pause() {
        if(gameState == GAMESTATE.RUNNING)
            gameState = GAMESTATE.PAUSED;
    }

    @Override
    public void resume() {
        gameState = GAMESTATE.RESUMED;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void updatePausedState(){
        resumeButton.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                gameState = GAMESTATE.RUNNING;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                super.touchUp(event, x, y, pointer, button);
            }
        });

        menuButton.addListener(new InputListener(){

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
