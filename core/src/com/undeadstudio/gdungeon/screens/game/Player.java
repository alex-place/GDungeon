package com.undeadstudio.gdungeon.screens.game;

import com.badlogic.gdx.math.Vector2;

public class Player {
	private String name;

	public Vector2 position;

	public Player(String name, Vector2 position) {
		this.name = name;
		this.position = position;
	}

	public Player(String name) {
		this(name, new Vector2());
		
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	enum State {
		up, down, left, right
	}
}
