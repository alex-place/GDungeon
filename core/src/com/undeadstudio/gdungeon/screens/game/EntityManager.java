package com.undeadstudio.gdungeon.screens.game;

import com.badlogic.gdx.math.Vector2;
import com.undeadstudio.gdungeon.screens.GameScreen;

public class EntityManager {

	GameScreen game;

	public EntityManager(GameScreen game) {
		this.game = game;
	}

	public void moveEntity(Entity entity, float x, float y) {
		moveEntity(entity, new Vector2(x, y));
	}

	public void moveEntity(Entity entity, Vector2 newPos) {
		entity.position = newPos;

	}
}
