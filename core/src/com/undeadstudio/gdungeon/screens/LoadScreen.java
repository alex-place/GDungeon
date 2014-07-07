package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.undeadstudio.gdungeon.Assets;
import com.undeadstudio.gdungeon.Main;
import com.undeadstudio.gdungeon.Player;

public class LoadScreen implements Screen {

	Main main;
	Stage stage;
	ExtendViewport viewport = new ExtendViewport(10, 10);
	Skin skin;
	SpriteBatch batch;
	ScrollPane scrollPane;
	Table table;

	InputMultiplexer input;
	int animals = 8;

	public LoadScreen(Main main) {
		this.main = main;
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.3f, 0.3f, 0.3f, 1);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);

	}

	@Override
	public void resize(int width, int height) {
		show();
	}

	@Override
	public void show() {
		batch = new SpriteBatch();

		skin = new Skin();
		skin.add("default-font", main.options.getPrefferedFont());
		skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
		skin.load(Gdx.files.internal("ui/uiskin.json"));

		stage = new Stage();
		input = new InputMultiplexer();
		input.addProcessor(stage);
		Gdx.input.setInputProcessor(input);

		// Add widgets to table then add them to scroll pane then add them to
		// window
		table = new Table();
		// table.debug();
		table.center();
		table.defaults().expandX().fill(0.5f, 1f).space(5);

		Label title = new Label("Load Game", skin);
		title.setAlignment(Align.center);
		table.add(title);
		table.row();

		// Create the new game button
		TextButton newGame = new TextButton("New Game", skin);
		newGame.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				main.setScreen(main.manager.getNewGameScreen());

			}
		});

		table.add(newGame).align(Align.center);
		table.row();

		addSaves();

		// Create the exit button
		TextButton exit = new TextButton("Exit", skin);
		exit.addListener(new InputListener() {
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

		table.add(exit).align(Align.center);
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

		window.add(scrollPane).fill().expand()
				.maxHeight(Gdx.graphics.getHeight());
		window.row();
		window.pack();

		stage.addActor(window);
		stage.act();
	}

	public void addSaves() {
		FileHandle savePath = Gdx.files.local("saves");
		FileHandle[] saves = savePath.list();
		Player player;
		TextButton button;
		Json json = new Json();

		for (FileHandle handle : saves) {

			player = json.fromJson(Player.class, handle);

			// Create the exit button
			button = new TextButton(player.getName(), skin);
			button.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					System.out.println("down");
					return true;
				}

				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					System.out.println("up");
					main.setScreen(main.manager.getGame());
					// main.manager.getGame().setPlayer(player);
				}
			});

			table.add(button).align(Align.center).row();
		}
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
	}

}
