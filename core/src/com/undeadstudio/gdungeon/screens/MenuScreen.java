package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.undeadstudio.gdungeon.Main;

public class MenuScreen implements Screen {

	Main main;
	Stage stage;
	ExtendViewport viewport = new ExtendViewport(10, 10);
	Skin skin;
	SpriteBatch batch;
	ScrollPane scrollPane;

	InputMultiplexer input;
	int animals = 8;

	public MenuScreen(Main main) {
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
		Table table = new Table();
		// table.debug();
		table.center();
		table.defaults().align(Align.center).fill(0.8f, 0.8f).expand().space(10f);// .prefSize(Gdx.graphics.getHeight()

		Label title = new Label("GDungeon Build #"
				+ main.versionManager.getVersion(), skin);
		title.setAlignment(Align.center);
//		table.add(title);
//		table.row();

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

		table.add(newGame);
		table.row();// Create the new game button
		TextButton loadGame = new TextButton("Load Game", skin);
		loadGame.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				main.setScreen(main.manager.getLoadGame());

			}
		});

		table.add(loadGame);
		table.row();

		// Create the options button
		TextButton options = new TextButton("Options", skin);
		options.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				main.setScreen(main.manager.getOptionsScreen());

			}
		});

		table.add(options);
		table.row();

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
				Gdx.app.exit();
			}
		});

		table.add(exit);
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
		window.defaults();//.fill().expand((int) (Gdx.graphics.getWidth() * 0.8), Gdx.graphics.getHeight());

		window.add(title).fillY().expandY().row();
		window.add(scrollPane).align(Align.center)
		.expand(Gdx.graphics.getWidth() - 20, 1).fill(0.8f, 1f).row();;//.maxHeight(Gdx.graphics.getHeight());
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
	}

}
