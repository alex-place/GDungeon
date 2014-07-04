package com.undeadstudio.gdungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	public AssetManager assetManager;

	public static final String TEXTURE_ATLAS_OBJECTS = "images/dungeon.pack";

	public AssetFonts fonts;
	public AssetFloor floor;
	public AssetWall wall;
	public AssetDoor door;
	public AssetCorridor corridor;
	public AssetChest chest;
	public AssetStairsUp stairsUp;
	public AssetStairsDown stairsDown;
	public AssetMonster monster;

	public AssetRouge rouge;

	private void Assets() {
	}

	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		assetManager.load(TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		// start loading assets and wait until finished
		assetManager.finishLoading();

		Gdx.app.debug(TAG,
				"# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset: " + a);
		}

		TextureAtlas atlas = assetManager.get(TEXTURE_ATLAS_OBJECTS);

		fonts = new AssetFonts();

		chest = new AssetChest(atlas);
		floor = new AssetFloor(atlas);
		wall = new AssetWall(atlas);
		door = new AssetDoor(atlas);
		corridor = new AssetCorridor(atlas);
		stairsUp = new AssetStairsUp(atlas);
		stairsDown = new AssetStairsDown(atlas);
		monster = new AssetMonster(atlas);
		rouge = new AssetRouge(atlas);

	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		String filename = asset.fileName;
		Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'",
				(Exception) throwable);

	}

	@Override
	public void dispose() {
		assetManager.dispose();

	}

	public class AssetFonts {

		public final BitmapFont small;
		public final BitmapFont medium;
		public final BitmapFont large;

		public AssetFonts() {
			FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
					Gdx.files.internal("fonts/emulogic.ttf"));
			FreeTypeFontParameter parameter = new FreeTypeFontParameter();
			parameter.size = 12;
			small = generator.generateFont(parameter); // font size 12 pixels

			parameter.size = 16;
			medium = generator.generateFont(parameter);

			parameter.size = 32;
			large = generator.generateFont(parameter);

			generator.dispose(); // don't forget to dispose to avoid memory
									// leaks!
		}

	}

	public class AssetFloor {
		public final AtlasRegion reg;

		public AssetFloor(TextureAtlas atlas) {
			reg = atlas.findRegion("floor");
		}
	}

	public class AssetWall {
		public final AtlasRegion reg;

		public AssetWall(TextureAtlas atlas) {
			reg = atlas.findRegion("wall");
		}
	}

	public class AssetDoor {
		public final AtlasRegion reg;

		public AssetDoor(TextureAtlas atlas) {
			reg = atlas.findRegion("door");
		}
	}

	public class AssetCorridor {
		public final AtlasRegion reg;

		public AssetCorridor(TextureAtlas atlas) {
			reg = atlas.findRegion("corridor");
		}
	}

	public class AssetChest {
		public final AtlasRegion reg;
		public final AtlasRegion open;

		public AssetChest(TextureAtlas atlas) {
			reg = atlas.findRegion("chest");
			open = atlas.findRegion("open");
		}
	}

	public class AssetStairsUp {
		public final AtlasRegion reg;

		public AssetStairsUp(TextureAtlas atlas) {
			reg = atlas.findRegion("stairsup");
		}
	}

	public class AssetStairsDown {
		public final AtlasRegion reg;

		public AssetStairsDown(TextureAtlas atlas) {
			reg = atlas.findRegion("stairsdown");
		}
	}

	public class AssetMonster {
		public final AtlasRegion reg;

		public AssetMonster(TextureAtlas atlas) {
			reg = atlas.findRegion("monster");
		}

	}

	public class AssetRouge {
		public final AtlasRegion rouge_0_0;
		public final AtlasRegion rouge_0_1;
		public final AtlasRegion rouge_0_2;
		public final AtlasRegion rouge_1_0;
		public final AtlasRegion rouge_1_1;
		public final AtlasRegion rouge_1_2;
		public final AtlasRegion rouge_2_0;
		public final AtlasRegion rouge_2_1;
		public final AtlasRegion rouge_2_2;
		public final AtlasRegion rouge_3_0;
		public final AtlasRegion rouge_3_1;
		public final AtlasRegion rouge_3_2;

		public AssetRouge(TextureAtlas atlas) {
			rouge_0_0 = atlas.findRegion("rouge-0-0");
			rouge_0_1 = atlas.findRegion("rouge-0-1");
			rouge_0_2 = atlas.findRegion("rouge-0-2");
			rouge_1_0 = atlas.findRegion("rouge-1-0");
			rouge_1_1 = atlas.findRegion("rouge-1-1");
			rouge_1_2 = atlas.findRegion("rouge-1-2");
			rouge_2_0 = atlas.findRegion("rouge-2-0");
			rouge_2_1 = atlas.findRegion("rouge-2-1");
			rouge_2_2 = atlas.findRegion("rouge-2-2");
			rouge_3_0 = atlas.findRegion("rouge-3-0");
			rouge_3_1 = atlas.findRegion("rouge-3-1");
			rouge_3_2 = atlas.findRegion("rouge-3-2");
		}
	}

}
