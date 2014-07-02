package com.undeadstudio.gdungeon.screens;

import com.undeadstudio.gdungeon.Main;

public class Options {
	Main main;
	
	OptionsScreen optionsScreen;

	public Options(Main main) {
		this.main = main;
		optionsScreen = main.manager.getOptionsScreen();
	}

}
