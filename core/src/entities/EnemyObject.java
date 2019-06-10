package entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.game.CrazyTimeTraveler;

public abstract class EnemyObject implements Pool.Poolable {

    protected Vector2 position;
    protected int WIDTH;
    protected int HEIGHT;
    protected float elapsedTime;

    protected CrazyTimeTraveler game;
    protected TextureAtlas atlas;

    public EnemyObject(CrazyTimeTraveler game){
        this.game = game;
        atlas = game.assets.getTextureAtlas();
    }

    public abstract void update();
    public abstract void draw(SpriteBatch batch);

    @Override
    public abstract void reset();

    public Vector2 getPosition(){
        return position;
    }

    public Rectangle getRectangle(){
        return new Rectangle(position.x, position.y, WIDTH,HEIGHT);
    }
}
