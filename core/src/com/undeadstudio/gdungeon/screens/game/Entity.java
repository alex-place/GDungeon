package com.undeadstudio.gdungeon.screens.game;

import com.badlogic.gdx.math.Vector2;

public class Entity {
	public Vector2 position;

	public Entity() {
		position = new Vector2();
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public void setPosition(float x, float y) {
		setPosition(new Vector2(x, y));
	}

}
