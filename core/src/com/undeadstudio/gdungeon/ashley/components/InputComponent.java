package com.undeadstudio.gdungeon.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.InputProcessor;

public class InputComponent extends Component {

	private InputProcessor input;
	private boolean added = false;

	public InputComponent(InputProcessor input) {
		this.input = input;
	}

	public InputProcessor getInput() {
		return input;
	}

	public boolean isAdded() {
		return added;
	}

	public void setAdded(boolean added) {
		this.added = added;
	}

}
