package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.CrazyTimeTraveler;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1640;
		config.height = 1200;
		config.title = "Crazy-Time-Traveler";
		new LwjglApplication(new CrazyTimeTraveler(), config);
	}
}