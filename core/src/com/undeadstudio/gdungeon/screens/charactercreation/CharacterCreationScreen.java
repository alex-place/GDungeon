package com.undeadstudio.gdungeon.screens.charactercreation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.undeadstudio.gdungeon.Assets;
import com.undeadstudio.gdungeon.Main;

public class CharacterCreationScreen implements Screen {

	Main main;
	Stage stage;
	Skin skin;
	SpriteBatch batch;
	ScrollPane scrollPane;

	Image character;
	Texture txtChar;

	InputMultiplexer input;

	int columns = 9;

	String rougeDescription = "For rogues, the only code is the contract, and their honor is purchased in gold. Free from the constraints of a conscience, these mercenaries rely on brutal and efficient tactics. Lethal assassins and masters of stealth, they will approach their marks from behind, piercing a vital organ and vanishing into the shadows before the victim hits the ground.";
	private Table table;
	private Label title;
	private TextButton left;
	private TextButton right;
	private TextField name;
	private Label classDescription;
	private TextButton menu;
	private Table window;

	public CharacterCreationScreen(Main main) {
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
		skin.add("default-font", Assets.instance.fonts.medium);
		skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
		skin.load(Gdx.files.internal("ui/uiskin.json"));

		stage = new Stage();
		input = new InputMultiplexer();
		input.addProcessor(stage);
		Gdx.input.setInputProcessor(input);

		table = new Table();
		table.debug();
		table.center();
		table.defaults().pad(5).prefSize(Gdx.graphics.getHeight() - 100);

		title = new Label("Character Creation", skin);
		title.setAlignment(Align.center);
		table.add(title).colspan(columns).row();

		Label className = new Label("Rouge", skin);
		className.setAlignment(Align.center);
		table.add();
		table.add(className).align(Align.center).row();

		left = new TextButton("<", skin);

		table.add(left).fill(0.5f, 1f).align(Align.right);

		character = new Image(Assets.instance.rouge.rouge_0_0);

		table.add(character).fill(false);// .fill(0.5f, 0f);

		right = new TextButton(">", skin);

		table.add(right).fill(0.5f, 1f).align(Align.left);

		name = new TextField("", skin);
		name.setMessageText("Name?");
		table.add(name).fill(0f, 0.5f).align(Align.left).row();

		classDescription = new Label("", skin);

		classDescription.setText(rougeDescription);
		classDescription.setWrap(true);
		classDescription.setAlignment(Align.center);

		ScrollPane desScroll = new ScrollPane(classDescription, skin);

		desScroll.setScrollbarsOnTop(false);
		desScroll.setFadeScrollBars(false);
		desScroll.setScrollingDisabled(true, false);

		table.add(desScroll).align(Align.center).colspan(columns).row();

		menu = new TextButton("Back", skin);
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

		table.add(menu).colspan(3);

		TextButton finished = new TextButton("Finished", skin);
		finished.addListener(new InputListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				super.touchUp(event, x, y, pointer, button);
			}
		});

		table.add(finished).colspan(3);

		table.row();

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
