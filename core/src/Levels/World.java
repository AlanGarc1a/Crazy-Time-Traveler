package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.CrazyTimeTraveler;
import entities.Player;

public class World {

    private Player player;
    private CrazyTimeTraveler game;


    public World(CrazyTimeTraveler game){
        this.game = game;
        player = new Player(game, new Vector2(250,  100), new Vector2(0,0));
    }

    public void update(float delta){
        player.update(delta);
    }

    public void draw(SpriteBatch batch){

        batch.begin();

        player.draw(batch);

        batch.end();
    }
}
