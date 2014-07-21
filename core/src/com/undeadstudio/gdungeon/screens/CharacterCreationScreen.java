package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.undeadstudio.gdungeon.Assets;
import com.undeadstudio.gdungeon.Main;
import com.undeadstudio.gdungeon.gen.DunGen;
import com.undeadstudio.gdungeon.screens.game.Player;

public class CharacterCreationScreen implements Screen {

	Main main;
	Stage stage;
	Skin skin;
	SpriteBatch batch;
	ScrollPane scrollPane;

	Image character;
	Texture txtChar;

	InputMultiplexer input;

	int columns = 3;

	String rougeDescription = "For rogues, the only code is the contract, and their honor is purchased in gold. Free from the constraints of a conscience, these mercenaries rely on brutal and efficient tactics. Lethal assassins and masters of stealth, they will approach their marks from behind, piercing a vital organ and vanishing into the shadows before the victim hits the ground.";
	String warriorDescription = "For as long as war has raged, heroes from every race have aimed to master the art of battle. Warriors combine strength, leadership, and a vast knowledge of arms and armor to wreak havoc in glorious combat. They can unleash their rage at the closest threat with a variety of deadly weapons.";
	String wizardDescription = "Students gifted with a keen intellect and unwavering discipline may walk the path of the mage. The arcane magic available to magi is both great and dangerous, and thus is revealed only to the most devoted practitioners. To keep enemies at bay, magi can summon bursts of fire to incinerate distant targets and cause entire areas to erupt, setting groups of foes ablaze.";

	private Table table;
	private Label title;
	private Label className;
	private TextField name;
	private Label classDescription;
	private TextButton menu;
	private Table window;
	private TextButton finished;

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
		skin.add("default-font", main.options.getPrefferedFont());
		skin.addRegions(new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
		skin.load(Gdx.files.internal("ui/uiskin.json"));

		OrthographicCamera camera = new OrthographicCamera(
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage(new ScreenViewport(camera));
		input = new InputMultiplexer();
		input.addProcessor(stage);
		Gdx.input.setInputProcessor(input);

		table = new Table();
		table.debug();
		table.center();
		table.defaults().align(Align.center).fill().expand();

		title = new Label("Choose your path", skin);
		title.setAlignment(Align.center);
		table.add(title).row();

		className = new Label("Rouge", skin);
		className.setAlignment(Align.center);

		table.add(className).align(Align.center).row();

		Table classTable = new Table();

		ButtonGroup classes = new ButtonGroup();

		Button rougeBtn = new Button(
				new Image(Assets.instance.rouge.rouge_0_0), skin);
		rougeBtn.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				classDescription.setText(rougeDescription);
				className.setText("Rouge");

			}

		});

		Button warriorBtn = new Button(new Image(
				Assets.instance.warrior.warrior_0_0), skin);
		warriorBtn.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				classDescription.setText(warriorDescription);
				className.setText("Warrior");

			}

		});

		warriorBtn.setColor(Color.GREEN);
		Button wizardBtn = new Button(new Image(
				Assets.instance.wizard.wizard_0_0), skin);
		wizardBtn.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				classDescription.setText(wizardDescription);
				className.setText("Wizard");

			}

		});

		classes.add(rougeBtn, warriorBtn, wizardBtn);
		classTable.defaults().fill().expand().fill(1f, 1f);
		classTable.add(rougeBtn, warriorBtn, wizardBtn);
		table.add(classTable).fillY().expandY().fill(0.8f, 1f);

		table.row();

		// table.add(right).row().align(Align.left).row();

		name = new TextField("", skin);
		name.setMessageText("Name?");
		name.setMaxLength(25);
		table.add(name).expandX().fillX().fill(0.8f, 0.5f).align(Align.center)
				.row();

		classDescription = new Label("", skin);

		classDescription.setText(rougeDescription);
		classDescription.setWrap(true);
		classDescription.setAlignment(Align.center);

		ScrollPane desScroll = new ScrollPane(classDescription, skin);

		table.add(desScroll).align(Align.center)
				.expand(Gdx.graphics.getWidth() - 20, 1).fill(0.8f, 1f).row();

		finished = new TextButton("Finished", skin);
		finished.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("down");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("up");
				finishedWithCreation();
			}

		});

		table.add(finished).fill(0.5f, 1f);

		table.row();

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

		table.add(menu).fill(0.5f, 1f);
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

	public void finishedWithCreation() {
		if (!name.getText().isEmpty()
				&& !Gdx.files.local("saves/" + name.getText() + ".json")
						.exists()) {
			createCharacter();
			main.setScreen(main.manager.getGame());
		} else {

		}

	}

	public void createCharacter() {
		Player player = new Player(name.getText());

		Json json = new Json();
		System.out.println(json.prettyPrint(player));

		FileHandle playerFile = Gdx.files.local("saves/" + name.getText()
				+ ".json");

		playerFile.writeString(json.prettyPrint(player), false);

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
		txtChar.dispose();
	}

}
