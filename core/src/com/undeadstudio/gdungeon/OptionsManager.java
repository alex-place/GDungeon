package com.undeadstudio.gdungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class OptionsManager {
	Main main;
	private BitmapFont preferredFont;

	public OptionsManager(Main main) {
		this.main = main;
	}

	public BitmapFont getPrefferedFont() {
		int fontSize = main.preferencesManager.getOptions().getInteger(
				"fontSize", 12);
		switch (fontSize) {
		case 12:
			preferredFont = Assets.instance.fonts.small;
			break;
		case 16:
			preferredFont = Assets.instance.fonts.medium;
			break;
		case 32:
			preferredFont = Assets.instance.fonts.large;
			break;
		default:
			preferredFont = Assets.instance.fonts.small;

		}

		return preferredFont;
	}

	public void setPreferredFont(BitmapFont preferredFont) {
		this.preferredFont = preferredFont;
	}
}
