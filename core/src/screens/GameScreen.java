package screens;

import Levels.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.CrazyTimeTraveler;
import utils.Assets;

import java.awt.*;

public class GameScreen extends AbstractScreen {

    public enum GAMESTATE {
        RUNNING,
        PAUSED,
        RESUMED,
    }

    private Vector3 touchpoint;

    private Stage pauseStage;
    private Table pauseTable;
    private Label pauseLabel;
    private TextButton resumeButton;
    private TextButton menuButton;

    private Rectangle pauseBounds;
    private TextureRegion pauseButton;
    int width, height;

    GAMESTATE gameState;

    private World world;

    public GameScreen(CrazyTimeTraveler game) {
        super(game);

        touchpoint = new Vector3();
        gameState = GAMESTATE.RUNNING;

        world = new World(game);
        pauseButton = game.assets.getTextureAtlas().findRegion(Assets.PAUSE_BUTTON);
        width = pauseButton.getRegionWidth() * 2;
        height = pauseButton.getRegionHeight() * 2;
        pauseBounds = new Rectangle(560, 430, width, height);
        Gdx.input.setCatchBackKey(true);
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
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(game.camera.combined);

        if(Gdx.input.justTouched()){
            game.camera.unproject(touchpoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if(pauseBounds.contains(touchpoint.x, touchpoint.y)){
                world.getpickUpSound().play();
                gameState = GAMESTATE.PAUSED;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)) gameState = GAMESTATE.PAUSED;

        game.batch.begin();


        switch (gameState){
            case RUNNING:
                world.update();
                world.draw(game.batch);
                game.batch.draw(pauseButton, 560, 430, width, height);
                break;
            case PAUSED:
                updatePausedState();
                world.draw(game.batch);
                pauseStage.act();
                pauseStage.draw();
                break;
        }

        game.batch.end();
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
        if(gameState == GAMESTATE.PAUSED)
            gameState = GAMESTATE.RESUMED;
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        pauseStage.dispose();
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
