package com.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.CrazyTimeTraveler;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1040;
		config.height = 640;
		config.title = "Crazy-Time-Traveler";
		new LwjglApplication(new CrazyTimeTraveler(), config);
	}
}
