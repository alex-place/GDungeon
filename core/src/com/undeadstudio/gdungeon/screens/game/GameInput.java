package com.undeadstudio.gdungeon.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.undeadstudio.gdungeon.screens.GameScreen;

public class GameInput implements InputProcessor, GestureListener {

	GameScreen game;
	EntityManager manager;

	public GameInput(GameScreen game) {
		this.game = game;
		manager = game.manager;
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector3 worldCoordinates = new Vector3(x, y, 0);
		Vector3 gamePos = game.getCamera().unproject(worldCoordinates);
		manager.moveEntity(game.getPlayer(), gamePos.x, gamePos.y);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		Vector3 touch = game.getCamera().project(new Vector3(x, y, 0));
		game.getPlayer().setPosition(new Vector2(touch.x, touch.y));
		Gdx.app.log("GAME", "Input recieved! " + touch.x + " " + touch.y);

		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		game.getPlayer().setPosition(new Vector2(x, y));
		Gdx.app.log("GAME", "Input recieved! " + x + " " + y);

		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		Gdx.app.log("GAME", "Input recieved!");
		return false;
	}

}
