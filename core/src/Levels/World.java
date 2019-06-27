package Levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.CrazyTimeTraveler;
import entities.PlaneEnemy;
import entities.Player;
import screens.GameOverScreen;

public class World {

    //array of planes
    private Array<PlaneEnemy> planes;
    private float timer = TimeUtils.nanoTime();

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

        planes = new Array<PlaneEnemy>();
        parallaxIndustrial = new ParallaxIndustrial(game);
        player = new Player(game, new Vector2(), new Vector2(0,0));
    }

    /*
        Gets a plane from the pool
        @param delta adds time to the timer
        @time variable when time is greater than two it adds the plane from the pool to the
        array of plane enemies and resets the timer to zero.
    */
    private void createPlanes(){
        PlaneEnemy plane1 = planePool.obtain();

         if(TimeUtils.nanoTime() - timer > 1000000000) {
             planes.add(plane1);
             timer = TimeUtils.nanoTime();
         }
    }

    public void update(){
        createPlanes();

        parallaxIndustrial.update();
        player.update();

        for(PlaneEnemy planeEnemy : planes) {
            planeEnemy.update();
            if(player.getBounds().overlaps(planeEnemy.getRectangle())){
                planeEnemy.die();
                player.die();
                planes.removeValue(planeEnemy, false);
                planePool.free(planeEnemy);
                if(player.state == Player.STATE.DEAD && player.getStateTimer() < 0)
                    game.setScreen(new GameOverScreen(game, player.getScore()));
            }
            if(planeEnemy.getPosition().x + planeEnemy.getWIDTH() < 0) {
                player.addToScore();
                //game.assets.pickUpSound.play(1f);
                planes.removeValue(planeEnemy, false);
                planePool.free(planeEnemy);
            }
        }
    }

    public void draw(SpriteBatch batch){

        parallaxIndustrial.draw(batch);
        game.assets.silverFont.draw(game.batch, "" + player.getScore(), 20, 460);

        player.draw(batch);

        for(PlaneEnemy plane : planes)
            plane.draw(game.batch);

    }
}
