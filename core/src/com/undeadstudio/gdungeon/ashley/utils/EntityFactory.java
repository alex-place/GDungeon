package com.undeadstudio.gdungeon.ashley.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.undeadstudio.gdungeon.ashley.components.PositionComponent;
import com.undeadstudio.gdungeon.ashley.components.VisualComponent;

public class EntityFactory {

	public static final EntityFactory instance = new EntityFactory();

	private EntityFactory() {
	}

	public Entity newStaticTile(int x, int y, TextureRegion region, Color color) {

		Entity entity = new Entity();
		entity.add(new VisualComponent(region, color));
		entity.add(new PositionComponent(x, y));
		return entity;
	}

}
