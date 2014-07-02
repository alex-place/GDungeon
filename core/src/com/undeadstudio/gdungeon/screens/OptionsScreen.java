package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

	public OptionsScreen(Main main) {
		this.main = main;
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0, 0, 0, 0);

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
		Gdx.input.setInputProcessor(input);

		if (batch == null)
			batch = new SpriteBatch();
		if (skin == null)
			skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

		stage = new Stage();

		input = new InputMultiplexer();
		input.addProcessor(stage);

		Gdx.input.setInputProcessor(input);

		displays = Gdx.graphics.getDisplayModes();

		loadOptions();

		// Add widgets to table then add them to scroll pane then add them
		// to
		// window
		Table table = new Table();
		// table.debug();
		table.center();
		table.defaults().expandX().space(5).align(Align.center);

		Label title = new Label("Options", skin);
		title.setAlignment(Align.center);
		table.add(title).colspan(3).expandY().align(Align.top).spaceTop(10)
				.row();

		Label resolutionLbl = new Label("Resolution", skin);
		table.add(resolutionLbl).align(Align.right);

		displayModes = new SelectBox<DisplayMode>(skin);
		displayModes.setItems(displays);
		displayModes.setSelected(displays[displays.length - 1]);

		table.add(displayModes).row();

		// Create the sfx slider
		Label sfx = new Label("Sound Effects", skin);
		table.add(sfx).align(Align.right).spaceRight(10);

		sfxSlider = new Slider(0, 100, 1, false, skin);
		sfxSlider.setValue(sfxVolume);
		table.add(sfxSlider).fillX().align(Align.center);

		sfxVolumeLbl = new Label(sfxVolume + "", skin);
		table.add(sfxVolumeLbl).align(Align.center).spaceLeft(10).row();

		// Create the music slider
		Label music = new Label("Music", skin);
		table.add(music).align(Align.right).spaceRight(10);

		musicSlider = new Slider(0, 100, 1, false, skin);
		musicSlider.setValue(musicVolume);
		table.add(musicSlider).fillX().align(Align.center);

		musicVolumeLbl = new Label(musicVolume + "", skin);
		table.add(musicVolumeLbl).align(Align.center).spaceLeft(10).row();

		fullscreenCheckBox = new CheckBox("Fullscreen", skin);
		fullscreenCheckBox.setChecked(fullscreen);
		fullscreenCheckBox.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);

			}
		});

		table.add(fullscreenCheckBox);

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

		table.add(vsyncCheckBox).colspan(2).row();

		// Create the Menu button
		TextButton saveBtn = new TextButton("Save", skin);
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

		table.add(saveBtn).align(Align.center).colspan(3).spaceTop(20)
				.fill(0.5f, 0f).row();

		// Create the Menu button
		TextButton backBtn = new TextButton("Back", skin);
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

		table.add(backBtn).align(Align.center).colspan(3).spaceTop(20)
				.fill(0.5f, 0f).row();

		scrollPane = new ScrollPane(table, skin);
		scrollPane.setFlickScroll(true);
		scrollPane.setFadeScrollBars(true);
		scrollPane.setOverscroll(true, false);
		scrollPane.setSmoothScrolling(true);
		scrollPane.setScrollingDisabled(true, false);

		Table window = new Table(skin);
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
