package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.undeadstudio.gdungeon.Main;

public class OptionsScreen implements Screen {

	Main main;
	Stage stage;
	Skin skin;
	SpriteBatch batch;
	Texture[] textures;
	ScrollPane scrollPane;
	InputMultiplexer input;

	DisplayMode[] displays;
	SelectBox<DisplayMode> displayModes;

	Slider sfxSlider;
	private float sfxVolume = 100;
	Label sfxVolumeLbl;

	Slider musicSlider;
	private float musicVolume = 100;
	Label musicVolumeLbl;

	CheckBox vsyncCheckBox;
	boolean vsync = false;
	CheckBox fullscreenCheckBox;
	boolean fullscreen = false;
	private Table table;
	private Label title;
	private Label resolutionLbl;
	private Label sfx;
	private Label music;
	private ButtonGroup fontGroup;
	private TextButton saveBtn;
	private TextButton backBtn;
	private Table window;

	public OptionsScreen(Main main) {
		this.main = main;
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.3f, 0.3f, 0.3f, 1);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);

	}

	public void update() {
		sfxVolume = sfxSlider.getVisualValue();
		sfxVolumeLbl.setText(sfxVolume + "");

		musicVolume = musicSlider.getValue();
		musicVolumeLbl.setText(musicVolume + "");

		if (fullscreenCheckBox.isChecked()) {
			fullscreen = true;
		} else {

			fullscreen = false;
		}

		if (vsyncCheckBox.isChecked()) {
			vsync = true;
		} else {
			vsync = false;
		}

	}

	@Override
	public void resize(int width, int height) {
		show();
	}

	@Override
	public void show() {
		loadOptions();

		batch = new SpriteBatch();

		skin = new Skin();
		skin.add("default-font", main.options.getPrefferedFont());

		skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
		skin.load(Gdx.files.internal("ui/uiskin.json"));

		stage = new Stage();
		input = new InputMultiplexer();
		input.addProcessor(stage);
		Gdx.input.setInputProcessor(input);

		displays = Gdx.graphics.getDisplayModes();

		table = new Table();
		// table.debug();
		table.center();
		table.defaults().align(Align.center).fill().expand();

		title = new Label("Options", skin);
		title.setAlignment(Align.center);
		table.add(title).row();

		resolutionLbl = new Label("Resolution", skin);
		table.add(resolutionLbl).row();

		displayModes = new SelectBox<DisplayMode>(skin);
		displayModes.setItems(displays);
		displayModes.setSelected(displays[displays.length - 1]);

		table.add(displayModes).row();

		sfx = new Label("Sound Effects", skin);
		table.add(sfx).row();

		sfxSlider = new Slider(0, 100, 1, false, skin);
		sfxSlider.setValue(sfxVolume);
		table.add(sfxSlider).row();

		sfxVolumeLbl = new Label(sfxVolume + "", skin);
		table.add(sfxVolumeLbl).row();

		music = new Label("Music", skin);
		table.add(music).row();

		musicSlider = new Slider(0, 100, 1, false, skin);
		musicSlider.setValue(musicVolume);
		table.add(musicSlider).row();

		musicVolumeLbl = new Label(musicVolume + "", skin);
		table.add(musicVolumeLbl).row();

		fullscreenCheckBox = new CheckBox("Fullscreen", skin);
		fullscreenCheckBox.setChecked(fullscreen);
		fullscreenCheckBox.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);

			}
		});

		table.add(fullscreenCheckBox).row();

		vsyncCheckBox = new CheckBox("VSync", skin);
		vsyncCheckBox.setChecked(vsync);
		vsyncCheckBox.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				vsync = !vsync;
			}
		});

		table.add(vsyncCheckBox).row();

		CheckBox font12 = new CheckBox("Font 12", skin);
		font12.setUserObject(12);
		CheckBox font16 = new CheckBox("Font 16", skin);
		font16.setUserObject(16);
		CheckBox font32 = new CheckBox("Font 32", skin);
		font32.setUserObject(32);
		fontGroup = new ButtonGroup(font12, font16, font32);
		fontGroup.setChecked("Font 12");

		table.add(font12).row();
		table.add(font16).row();
		table.add(font32).row();

		saveBtn = new TextButton("Save", skin);
		saveBtn.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				saveOptions();
			}
		});

		table.add(saveBtn).row();

		backBtn = new TextButton("Back", skin);
		backBtn.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				main.setScreen(main.manager.getMenu());

			}
		});

		table.add(backBtn).row();

		scrollPane = new ScrollPane(table, skin);
		scrollPane.setFlickScroll(true);
		scrollPane.setFadeScrollBars(true);
		scrollPane.setOverscroll(true, false);
		scrollPane.setSmoothScrolling(true);
		scrollPane.setScrollingDisabled(true, false);

		window = new Table(skin);
		window.debug();
		window.setFillParent(true);
		window.row().fill().expandX();

		window.add(table).fill().expand().maxHeight(Gdx.graphics.getHeight());
		window.row();
		window.pack();

		stage.addActor(window);
		stage.act();

	}

	private void loadOptions() {
		Preferences options = main.preferencesManager.getOptions();
		musicVolume = options.getFloat("musicVolume", 100);
		sfxVolume = options.getFloat("sfxVolume", 100);
		fullscreen = options.getBoolean("fullscreen", false);
		vsync = options.getBoolean("vsync", false);

	}

	protected void saveOptions() {

		Preferences options = main.preferencesManager.getOptions();

		options.putFloat("musicVolume", musicVolume);
		options.putFloat("sfxVolume", sfxVolume);
		options.putBoolean("fullscreen", fullscreen);
		options.putBoolean("vsync", vsync);
		
options.putInteger("fontSize", (Integer) fontGroup.getChecked().getUserObject());		

		options.flush();

		applyOptions();

	}

	private void applyOptions() {
		Gdx.graphics.setVSync(vsync);

		DisplayMode selectedDisplay = displayModes.getSelected();
		Gdx.graphics.setDisplayMode(selectedDisplay.width,
				selectedDisplay.height, fullscreen);

	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		batch.dispose();
		for (Texture texture : textures)
			texture.dispose();
	}

}
