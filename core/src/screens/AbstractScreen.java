package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.CrazyTimeTraveler;

public abstract class AbstractScreen implements Screen {

    protected CrazyTimeTraveler game;
    protected TextureAtlas atlas;
    protected Table root;

    protected Label.LabelStyle style;
    protected TextButton.TextButtonStyle buttonStyle;


    public AbstractScreen(final CrazyTimeTraveler game){
        this.game = game;
        root = new Table();

        style = new Label.LabelStyle();
        style.font = game.assets.silverFont;
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.assets.silverFont;
    }

    @Override
    public abstract void show();

    @Override
    public abstract void render(float delta);

    @Override
    public abstract void resize(int width, int height);

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public abstract void hide();

    @Override
    public abstract void dispose();
}
