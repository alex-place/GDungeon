package com.undeadstudio.gdungeon.gen;

import com.badlogic.gdx.math.MathUtils;

public class Tile {

	public static final String AIR = " ";

	public static final String CHEST_OPENED = "&";

	public static final String CHEST_UNOPENED = "*";

	public static final String CORRIDOR = "#";

	public static final String DOOR = "D";

	public static final String FLOOR_DIRT = ".";

	public static final String STAIRS_DOWN = ">";

	public static final String STAIRS_UP = "<";

	public static final String WALL_DIRT = "+";

	public static final String WALL_STONE = "O";

	public static final String UNKNOWN = "?";

	public static String randomType() {
		String[] types = new String[] { AIR, FLOOR_DIRT, STAIRS_UP,
				STAIRS_DOWN, WALL_DIRT, FLOOR_DIRT };
		return types[MathUtils.random(types.length - 1)];
	}

}