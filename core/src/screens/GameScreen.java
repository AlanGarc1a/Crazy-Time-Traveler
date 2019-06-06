package screens;

import Levels.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.game.CrazyTimeTraveler;

public class GameScreen extends AbstractScreen {

    private World world;

    public GameScreen(CrazyTimeTraveler game) {
        super(game);

        world = new World(game);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(game.camera.combined);

        world.update(delta);

        world.draw(game.batch);
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
        game.viewport.getCamera().update();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
