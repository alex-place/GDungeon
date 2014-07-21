package com.undeadstudio.gdungeon.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePackerFileProcessor;
import com.badlogic.gdx.tools.texturepacker.TexturePackerTest;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.undeadstudio.gdungeon.Main;

public class DesktopLauncher {

	private static boolean rebuildAtlas = true;
	private static boolean drawDebugOutline = false;

	public static void main(String[] arg) {

		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.debug = drawDebugOutline;
			TexturePacker.processIfModified(settings, "assets-raw",
					"../android/assets/images", "dungeon.pack");
		
		
		}
		

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1196;
		config.height = 720;

		DisplayMode[] modes = LwjglApplicationConfiguration.getDisplayModes();
		DisplayMode best = modes[modes.length - 1];
		// config.setFromDisplayMode(best);
		new LwjglApplication(new Main(), config);

	}
}
