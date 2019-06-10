package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.game.CrazyTimeTraveler;
import entities.PlaneEnemy;
import entities.Player;
import screens.GameOverScreen;

public class World {

    private Array<PlaneEnemy> planes;
    private float timer = 0f;

    private int Score;

    //plane pool
    private final Pool<PlaneEnemy> planePool = new Pool<PlaneEnemy>(2) {
        @Override
        protected PlaneEnemy newObject() {
            return new PlaneEnemy(game);
        }
    };

    private Player player;
    private ParallaxIndustrial parallaxIndustrial;
    private CrazyTimeTraveler game;

    public World(final CrazyTimeTraveler game){
        this.game = game;

        Score = 0;
        planes = new Array<PlaneEnemy>();
        parallaxIndustrial = new ParallaxIndustrial(game);
        player = new Player(game, new Vector2(250,  100), new Vector2(0,0));
    }

    private void createPlanes(float delta){
        PlaneEnemy plane1 = planePool.obtain();

         timer += delta;
         if(timer > 2) {
             planes.add(plane1);
             timer = 0;
         }
    }

    public void update(float delta){
        createPlanes(delta);
        player.update();

        for(PlaneEnemy planeEnemy : planes) {
            planeEnemy.update();
            if(player.getBounds().overlaps(planeEnemy.getRectangle())){
                planes.removeValue(planeEnemy, false);
                planePool.free(planeEnemy);
                game.setScreen(new GameOverScreen(game));
            }
            if(planeEnemy.getPosition().x < 0) {
                planes.removeValue(planeEnemy, false);
                planePool.free(planeEnemy);
            }
            if(planeEnemy.getPosition().x <= player.getPosition()){
                Score++;
                //playSound
            }
        }
    }

    public void draw(SpriteBatch batch, float delta){

        batch.begin();

        parallaxIndustrial.draw(batch);

        for(PlaneEnemy plane : planes)
            plane.draw(game.batch);

        game.assets.silverFont.draw(game.batch, "Score: " + Score, 20, 460);
        player.draw(batch);

        batch.end();
    }
}
