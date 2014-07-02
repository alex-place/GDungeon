package com.undeadstudio.gdungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager {

	private Preferences options;

	public PreferencesManager() {
		options = Gdx.app.getPreferences("options");
	}

	public Preferences getOptions() {
		return options;
	}

}
