package com.undeadstudio.gdungeon.gen;

import com.badlogic.gdx.math.MathUtils;

public class Tile {

	public static final String AIR = "air";

	public static final String CHEST_OPENED = "chest_opened";

	public static final String CHEST_UNOPENED = "chest_unopened";

	public static final String CORRIDOR = "corridor";

	public static final String DOOR = "door";

	public static final String FLOOR_DIRT = "floor_dirt";

	public static final String STAIRS_DOWN = "stairs_down";

	public static final String STAIRS_UP = "stairs_up";

	public static final String WALL_DIRT = "wall_dirt";

	public static final String WALL_STONE = "wall_stone";

	public static String randomType() {
		String[] types = new String[] { AIR, FLOOR_DIRT, STAIRS_UP,
				STAIRS_DOWN, WALL_DIRT, FLOOR_DIRT };
		return types[MathUtils.random(types.length - 1)];
	}

}