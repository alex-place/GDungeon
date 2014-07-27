package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Screen;
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
	private LoadScreen load;
	private AshleyGameScreen ashleyGame;

	public ScreenManager(Main main) {
		menu = new MenuScreen(main);
		newGame = new CharacterCreationScreen(main);
		options = new OptionsScreen(main);
		load = new LoadScreen(main);
		ashleyGame = new AshleyGameScreen(main);
		this.main = main;
	}

	public MenuScreen getMenu() {
		return menu;
	}

	public CharacterCreationScreen getNewGameScreen() {
		return newGame;
	}

	public OptionsScreen getOptionsScreen() {
		return options;
	}

	public AshleyGameScreen getAshleyGame() {
		return ashleyGame;
	}

	public Screen getLoadGame() {
		return load;
	}

}
