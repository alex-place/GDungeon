package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.undeadstudio.gdungeon.Main;
import com.undeadstudio.gdungeon.Player;

public class GameScreen implements Screen {

	Main main;
	Player player;

	@Override
	public void render(float delta) {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.3f, 0.3f, 0.3f, 1);

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
