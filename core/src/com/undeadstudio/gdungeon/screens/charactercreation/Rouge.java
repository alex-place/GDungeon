package com.undeadstudio.gdungeon.screens.charactercreation;

import com.badlogic.gdx.graphics.Texture;
import com.undeadstudio.gdungeon.Assets;

public class Rouge {

	Texture up;
	Texture down;
	Texture left;
	Texture right;

	public Rouge() {
		up = Assets.instance.rouge.rouge_3_0.getTexture();
		left = Assets.instance.rouge.rouge_1_0.getTexture();
		right = Assets.instance.rouge.rouge_2_0.getTexture();
		down = Assets.instance.rouge.rouge_0_0.getTexture();
		
	}
}
