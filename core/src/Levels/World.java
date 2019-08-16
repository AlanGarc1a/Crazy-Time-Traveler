package Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import com.game.CrazyTimeTraveler;
import entities.*;
import screens.GameOverScreen;

public class World {

    //array of enemies
    private Array<PlaneEnemy> planes;
    private Array<PoliceCar> policeCars;
    private Array<AlienEnemy> alienEnemies;

    private Array<Portal> portals;
    private float portalTimer = 0;

    public enum Level {
        INDUSTRIAL,
        CYBERPUNK,
        MOUNTAINS
    }

    //plane pool
    private final Pool<PlaneEnemy> planePool = new Pool<PlaneEnemy>(3) {
        @Override
        protected PlaneEnemy newObject() {
            return new PlaneEnemy(game);
        }
    };

    //police car pool
    private final Pool<PoliceCar> policeCarPool = new Pool<PoliceCar>(3) {
        @Override
        protected PoliceCar newObject() {
            return new PoliceCar(game);
        }
    };

    //alien pool
    private final Pool<AlienEnemy> alienEnemyPool = new Pool<AlienEnemy>(3) {
        @Override
        protected AlienEnemy newObject() {
            return new AlienEnemy(game);
        }
    };

    Level currentLevel = Level.INDUSTRIAL;

    protected float timer = TimeUtils.nanoTime();

    private Player player;
    private Portal portal;

    private ParallaxIndustrial parallaxIndustrial;
    private ParallaxMountains parallaxMountains;
    private CyberPunkWorld cyberPunkWorld;

    private CrazyTimeTraveler game;

    public World(final CrazyTimeTraveler game){
        this.game = game;

        policeCars = new Array<PoliceCar>();
        alienEnemies = new Array<AlienEnemy>();
        planes = new Array<PlaneEnemy>();

        player = new Player(game, new Vector2(), new Vector2(0,0));

        parallaxIndustrial = new ParallaxIndustrial(game);
        parallaxMountains = new ParallaxMountains(game);
        cyberPunkWorld = new CyberPunkWorld(game);
        portals = new Array<Portal>(1);
        portal = new Portal(game);
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

    private void createPoliceCars(){
        PoliceCar policeCar1 = policeCarPool.obtain();

        if(TimeUtils.nanoTime() - timer > 1000000000) {
            policeCars.add(policeCar1);
            timer = TimeUtils.nanoTime();
        }
    }

    private void createAlienEnemy(){
        AlienEnemy alienEnemy = alienEnemyPool.obtain();

        if(TimeUtils.nanoTime() - timer > 1000000000){
            alienEnemies.add(alienEnemy);
            timer = TimeUtils.nanoTime();
        }
    }

    public void update(float delta){

        switch(currentLevel){

            case INDUSTRIAL:
                parallaxIndustrial.update(delta);
                createPlanes();
                player.update();
                if(player. getScore() > 5){
                    portalTimer += Gdx.graphics.getDeltaTime();
                    if(portalTimer > 10){
                        portals.add(portal);
                        portal.update();
                        if(player.getBounds().overlaps(portal.getBounds())){
                            portals.removeValue(portal, false);
                            portal.reset();
                            portalTimer = 0;
                            currentLevel = Level.CYBERPUNK;
                        }
                    }
                }
                collisionWithPlanes();
                break;

            case CYBERPUNK:
                cyberPunkWorld.update(delta);
                createPoliceCars();
                player.update();
                if(player. getScore() > 5){
                    portalTimer += Gdx.graphics.getDeltaTime();
                    if(portalTimer > 10){
                        portals.add(portal);
                        portal.update();
                        if(player.getBounds().overlaps(portal.getBounds())){
                            portals.removeValue(portal, false);
                            portal.reset();
                            portalTimer = 0;
                            currentLevel = Level.MOUNTAINS;
                        }
                    }
                }
                collisionWithPoliceCar();
                break;

            case MOUNTAINS:
                parallaxMountains.update(delta);
                createAlienEnemy();
                player.update();
                if(player. getScore() > 5){
                    portalTimer += Gdx.graphics.getDeltaTime();
                    if(portalTimer > 10){
                        portals.add(portal);
                        portal.update();
                        if(player.getBounds().overlaps(portal.getBounds())){
                            portals.removeValue(portal, false);
                            portal.reset();
                            portalTimer = 0;
                            currentLevel = Level.INDUSTRIAL;
                        }
                    }
                }
                collisionWithAliens();
                break;
        }
    }

    public void draw(SpriteBatch batch){

        switch(currentLevel){

            case INDUSTRIAL:
                parallaxIndustrial.draw(batch);
                game.assets.silverFont.draw(game.batch, "" + player.getScore(), 20, 460);
                player.draw(batch);
                if(player. getScore() > 5){
                    portalTimer += Gdx.graphics.getDeltaTime();
                    if(portalTimer > 5){
                        portals.add(portal);
                        portal.draw(batch);
                        if(player.getBounds().overlaps(portal.getBounds())){
                            portals.removeValue(portal, false);
                            portal.reset();
                            portalTimer = 0;
                            currentLevel = Level.CYBERPUNK;
                        }
                    }
                }
                for(PlaneEnemy plane : planes)
                    plane.draw(batch);
                break;

            case CYBERPUNK:
                cyberPunkWorld.draw(batch);
                game.assets.silverFont.draw(game.batch, "" + player.getScore(), 20, 460);
                player.draw(batch);
                if(player. getScore() > 5){
                    portalTimer += Gdx.graphics.getDeltaTime();
                    if(portalTimer > 5){
                        portals.add(portal);
                        portal.draw(batch);
                        if(player.getBounds().overlaps(portal.getBounds())){
                            portals.removeValue(portal, false);
                            portal.reset();
                            portalTimer = 0;
                             currentLevel = Level.MOUNTAINS;
                        }
                    }
                }
                for(PoliceCar car : policeCars)
                    car.draw(batch);
                break;

            case MOUNTAINS:
                parallaxMountains.draw(batch);
                game.assets.silverFont.draw(game.batch, "" + player.getScore(), 20, 460);
                player.draw(batch);
                if(player. getScore() > 5){
                    portalTimer += Gdx.graphics.getDeltaTime();
                    if(portalTimer > 5){
                        portals.add(portal);
                        portal.draw(batch);
                        if(player.getBounds().overlaps(portal.getBounds())){
                            portals.removeValue(portal, false);
                            portal.reset();
                            portalTimer = 0;
                            currentLevel = Level.INDUSTRIAL;
                        }
                    }
                }
                for(AlienEnemy alienEnemy : alienEnemies)
                    alienEnemy.draw(batch);
                break;
        }
    }

    private void collisionWithPlanes(){

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
                planes.removeValue(planeEnemy, false);
                planePool.free(planeEnemy);
            }
        }
    }

    private void collisionWithPoliceCar(){

        for(PoliceCar carEnemy : policeCars){
            carEnemy.update();
            if(player.getBounds().overlaps(carEnemy.getRectangle())){
                carEnemy.die();
                player.die();
                policeCars.removeValue(carEnemy, false);
                policeCarPool.free(carEnemy);
                if(player.state == Player.STATE.DEAD && player.getStateTimer() < 0)
                    game.setScreen(new GameOverScreen(game, player.getScore()));
            }
            if(carEnemy.getPosition().x + carEnemy.getWIDTH() < 0) {
                player.addToScore();
                policeCars.removeValue(carEnemy, false);
                policeCarPool.free(carEnemy);
            }
        }
    }

    private void collisionWithAliens(){

        for(AlienEnemy alienEnemy : alienEnemies){
            alienEnemy.update();
            if(player.getBounds().overlaps(alienEnemy.getRectangle())){
                alienEnemy.die();
                player.die();
               alienEnemies.removeValue(alienEnemy, false);
               alienEnemyPool.free(alienEnemy);
                if(player.state == Player.STATE.DEAD && player.getStateTimer() < 0)
                    game.setScreen(new GameOverScreen(game, player.getScore()));
            }
            if(alienEnemy.getPosition().x + alienEnemy.getWIDTH() < 0) {
                player.addToScore();
                alienEnemies.removeValue(alienEnemy, false);
                alienEnemyPool.free(alienEnemy);
            }
        }
    }

}
