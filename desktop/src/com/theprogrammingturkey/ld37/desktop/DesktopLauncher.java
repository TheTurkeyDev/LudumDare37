package com.theprogrammingturkey.ld37.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.theprogrammingturkey.ld37.LudumDare37;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "LudumDare 37";
		config.width = 1260;
		config.height = 720;
		new LwjglApplication(new LudumDare37(), config);
	}
}
