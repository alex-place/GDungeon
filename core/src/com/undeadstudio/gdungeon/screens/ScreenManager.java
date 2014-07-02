package com.undeadstudio.gdungeon.screens;

import com.undeadstudio.gdungeon.Main;

/**
 * Overly complex way of managing a single instance of any given screen while
 * not crowding the main class.
 * */
public class ScreenManager {

	Main main;
	private MenuScreen menu;
	private CharacterCreationScreen newGame;
	private OptionsScreen options;

	public ScreenManager(Main main) {
		menu = new MenuScreen(main);
		newGame = new CharacterCreationScreen(main);
		options = new OptionsScreen(main);
		this.main = main;
	}

	public MenuScreen getMenu() {
		return menu;
	}

	public CharacterCreationScreen getNewGameScreen() {
		return newGame;
	}

	public OptionsScreen getOptionsScreen() {
		// TODO Auto-generated method stub
		return options;
	}

}
