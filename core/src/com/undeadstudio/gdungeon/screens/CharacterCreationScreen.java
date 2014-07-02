package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.undeadstudio.gdungeon.Main;

public class CharacterCreationScreen implements Screen {

	Main main;
	Stage stage;
	ExtendViewport viewport = new ExtendViewport(10, 10);
	Skin skin;
	SpriteBatch batch;
	String[] buttons = { "New Game", "Options", "Exit" };
	Texture[] textures;
	ScrollPane scrollPane;

	InputMultiplexer input;
	int animals = 8;

	public CharacterCreationScreen(Main main) {
		this.main = main;
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0, 0, 0, 0);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		if (batch == null)
			batch = new SpriteBatch();
		if (skin == null)
			skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		if (stage == null)
			stage = new Stage();

		input = new InputMultiplexer();
		input.addProcessor(stage);

		Gdx.input.setInputProcessor(input);

		// Add widgets to table then add them to scroll pane then add them to
		// window
		Table table = new Table();
		// table.debug();
		table.center();
		table.defaults().minSize(Gdx.graphics.getHeight() / 8).pad(20)
				.prefSize(Gdx.graphics.getHeight() - 100);

		Label title = new Label("Character Creation", skin);
		title.setAlignment(Align.center);
		table.add(title);
		table.row();

		// Create the new game button
		TextButton menu = new TextButton("Back", skin);
		menu.addListener(new InputListener() {
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

		table.add(menu).align(Align.center);

		table.row();

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
