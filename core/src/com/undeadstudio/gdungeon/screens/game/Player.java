package com.undeadstudio.gdungeon.screens.game;

import com.badlogic.gdx.Gdx;

public class Player {

	private String name;

	private int x;

	private int y;

	private float health;

	private float sp;

	private float ap;

	private String startingClass;

	private String level;

	public Player() {
		level = Gdx.files.local("levels/1.lvl").readString();
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getStartingClass() {
		return startingClass;
	}

	public void setStartingClass(String startingClass) {
		this.startingClass = startingClass;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public float getSp() {
		return sp;
	}

	public void setSp(float sp) {
		this.sp = sp;
	}

	public float getAp() {
		return ap;
	}

	public void setAp(float ap) {
		this.ap = ap;
	}

}
