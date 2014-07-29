package com.undeadstudio.gdungeon.ashley.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableIntMap;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.undeadstudio.gdungeon.ashley.components.InputComponent;

public class InputSystem extends EntitySystem {
	InputMultiplexer input;
	InputComponent inputComponent;

	private ImmutableIntMap<Entity> entities;

	int i = 0;

	public InputSystem(InputMultiplexer input) {
		this.input = input;
	}

	@Override
	public void addedToEngine(Engine engine) {
		entities = engine.getEntitiesFor(Family
				.getFamilyFor(InputComponent.class));

	}

	@Override
	public void removedFromEngine(Engine engine) {
	}

	@Override
	public void update(float deltaTime) {
		for (Entity entity : entities.values()) {
			inputComponent = entity.getComponent(InputComponent.class);
			if (!inputComponent.isAdded()) {
				input.addProcessor(inputComponent.getInput());
				Gdx.app.log("Game", "processor #" + ++i);

				inputComponent.setAdded(true);
				Gdx.app.log("Game", "I hear you loud and clear! Entity#" + ++i);

			}

		}
	}

}
