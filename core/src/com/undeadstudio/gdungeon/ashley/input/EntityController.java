package com.undeadstudio.gdungeon.ashley.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.undeadstudio.gdungeon.ashley.components.PositionComponent;

public class EntityController extends InputAdapter implements GestureListener {

	OrthographicCamera camera;
	private Entity entity;
	PositionComponent pos;
	float speed = 3;

	public EntityController(OrthographicCamera camera, Entity entity) {
		this.entity = entity;
		this.camera = camera;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector3 tmp = new Vector3(screenX, screenY, 0);
		tmp = camera.unproject(tmp);
		touchDown(tmp.x, tmp.y, pointer, button);

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector3 tmp = new Vector3(screenX, screenY, 0);
		tmp = camera.unproject(tmp);
		// tap(tmp.x, tmp.y, 0, 0);

		return false;
	}

	// GestureListeners implementation of touchDown
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		move(x, y);

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		PositionComponent temp = entity.getComponent(PositionComponent.class);

		switch (keycode) {
		case Keys.SPACE:
			camera.position.set(new Vector3(temp.x + 1, temp.y + 1, 0));

			break;

		case Keys.UP:
			camera.position.add(0, 1, 0);

		default:
			break;
		}

		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		move(x, y);
		return false;
	}

	public void move(float x, float y) {
		pos = entity.getComponent(PositionComponent.class);
		pos.x = x;
		pos.y = y;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
