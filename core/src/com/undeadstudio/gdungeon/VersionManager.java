package com.undeadstudio.gdungeon;

import java.text.DecimalFormat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class VersionManager {

	private double version = 0;
	DecimalFormat df = new DecimalFormat("#.##");

	public void buildProject() {
		FileHandle file = Gdx.files.local("ver.txt");
		if (!file.exists()) {
			file.writeString(String.valueOf(version), false);
		} else {
			version = Double.valueOf(file.readString());
			version += 1;
			file.writeString(df.format(version), false);
		}
	}

	public String getVersion() {
		FileHandle file = Gdx.files.local("ver.txt");
		if (!file.exists()) {
			version = 0;
		} else {
			version = Double.valueOf(file.readString());
		}
		return df.format(version);
	}
}
