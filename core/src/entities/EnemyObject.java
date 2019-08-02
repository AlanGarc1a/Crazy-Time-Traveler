package entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.game.CrazyTimeTraveler;

public abstract class EnemyObject implements Pool.Poolable {

    public enum ENEMY_STATE{
        ALIVE,
        DEAD
    }

    protected Vector2 position;
    protected int WIDTH;
    protected int HEIGHT;
    protected float elapsedTime;
    protected boolean isDead;

    protected CrazyTimeTraveler game;
    protected TextureAtlas atlas, atlas2;

    public EnemyObject(CrazyTimeTraveler game){
        this.game = game;
        atlas = game.assets.getTextureAtlas();
        atlas2 = game.assets.getTextureAtlas2();
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

    public int getWIDTH(){
        return WIDTH;
    }

    public int getHEIGHT(){
        return HEIGHT;
    }

    public boolean isDead(){
        return isDead;
    }
}
