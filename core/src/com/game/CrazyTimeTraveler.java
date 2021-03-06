package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.*;
import screens.*;
import utils.Assets;
import utils.GamePreferences;

import javax.swing.text.View;

public class CrazyTimeTraveler extends Game {

	public static int GAME_WIDTH = 600;
	public static int GAME_HEIGHT = 480;

	public OrthographicCamera camera;
	public Viewport viewport;
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;

	public Assets assets;
	public GamePreferences preferences;

	public AdsController adsController;

	//constructor of this class goes here
	/*public CrazyTimeTraveler(AdsController adsController){
		this.adsController = adsController;
	}*/

	/*
	   this function gets called immediately when our app starts
	*/
	@Override
	public void create () {

		//adsController.showBannerAd();
		preferences = new GamePreferences(this);

		assets = new Assets();

		//create our batch for drawing to the screen
		batch = new SpriteBatch();

		shapeRenderer = new ShapeRenderer();

		//setup our camera and viewport
		camera = new OrthographicCamera();
		viewport = new StretchViewport(GAME_WIDTH, GAME_HEIGHT, camera);
		viewport.apply();

		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
		//camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
	}

	@Override
	public void render () {
		super.render();
	}

	/*
	* this function gets called when our app closes
	*/
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		assets.dispose();
	}
}
