package com.undeadstudio.gdungeon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.undeadstudio.gdungeon.screens.ScreenManager;

public class Main extends Game {

	public static final boolean DEBUG = true;
	public VersionManager versionManager;
	public PreferencesManager preferencesManager;
	public static String LOG;
	public ScreenManager manager = new ScreenManager(this);

	@Override
	public void create() {
		versionManager = new VersionManager();
		preferencesManager = new PreferencesManager();
		LOG = "GDungeon v" + versionManager.getVersion();
		Gdx.graphics.setTitle(Main.LOG);
		versionManager.buildProject();

		Gdx.app.log(LOG, "Launched successfully");
		setScreen(manager.getOptionsScreen());

	}

}