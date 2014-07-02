package com.undeadstudio.gdungeon.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.undeadstudio.gdungeon.Main;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		DisplayMode[] modes = LwjglApplicationConfiguration.getDisplayModes();
		DisplayMode best = modes[modes.length-1];
		config.setFromDisplayMode(best);
		new LwjglApplication(new Main(), config);

	}
}
