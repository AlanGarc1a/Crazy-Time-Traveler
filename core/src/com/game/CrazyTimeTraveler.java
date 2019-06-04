package com.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import utils.Assets;

public class CrazyTimeTraveler extends Game {

	public static int GAME_WIDTH = 300;
	public static int GAME_HEIGHT = 200;

	public FreeTypeFontGenerator generator;
	public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
	public BitmapFont font;

	public OrthographicCamera camera;
	public Viewport viewport;
	public SpriteBatch batch;

	public Assets assets;

	/*
	   this function gets called immediately when our app starts
	*/
	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		assets = new Assets();

		//create our font
		generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Gravity2.ttf"));
		parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 22;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:%-";
		font = generator.generateFont(parameter);
		font.setColor(Color.WHITE);

		//create our batch for drawing to the screen
		batch = new SpriteBatch();

		//setup our camera and viewport
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GAME_WIDTH, GAME_HEIGHT);
		viewport = new ExtendViewport(GAME_WIDTH, GAME_HEIGHT, camera);

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
		generator.dispose();
		font.dispose();
		assets.dispose();
	}
}
