package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;

public abstract class GameObject {

    protected Vector2 position;
    protected Vector2 direction;

    protected CrazyTimeTraveler game;
    protected TextureAtlas atlas;

    public GameObject(CrazyTimeTraveler game, Vector2 position, Vector2 direction){
        this.game = game;
        this.position = position;
        this.direction = direction;

        atlas = game.assets.getTextureAtlas();
    }

    public abstract void update();
    public abstract void draw(SpriteBatch batch);

    public Rectangle getBounds(){
        return new Rectangle(position.x, position.y, 32,32);
    }

    public void setDirection(float x, float y){
        direction.set(x, y);

        direction.scl(Gdx.graphics.getDeltaTime());
    }

    public float getPosition(){ return position.x; }
}
