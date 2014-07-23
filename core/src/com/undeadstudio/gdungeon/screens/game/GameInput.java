package com.undeadstudio.gdungeon.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
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
	public boolean keyDown(int key) {

		// switch (key) {
		// case Keys.SPACE:
		// if (game.getHelper().hasTarget(game.getPlayer())) {
		// game.getHelper().setTarget(null);
		// } else {
		// game.getHelper().setTarget(game.getPlayer());
		// }
		// break;
		// default:
		// break;
		//
		// }

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		Vector3 worldCoordinates = new Vector3(x, y, 0);
		Vector3 gamePos = game.getCamera().unproject(worldCoordinates);
		manager.moveEntity(game.getPlayer(), MathUtils.round(gamePos.x - 0.5f),
				MathUtils.round(gamePos.y - 0.5f));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
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

		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		return false;
	}

}
