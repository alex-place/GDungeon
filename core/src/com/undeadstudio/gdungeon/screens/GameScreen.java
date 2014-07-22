package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.undeadstudio.gdungeon.Assets;
import com.undeadstudio.gdungeon.Main;
import com.undeadstudio.gdungeon.screens.game.EntityManager;
import com.undeadstudio.gdungeon.screens.game.GameInput;
import com.undeadstudio.gdungeon.screens.game.Player;

public class GameScreen implements Screen {

	Main main;
	GameInput input;
	public EntityManager manager;

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private AtlasRegion playerTexture;

	private Player player;

	public GameScreen(Main main) {
		this.main = main;
	}

	@Override
	public void show() {
		input = new GameInput(this);
		Gdx.input.setInputProcessor(input);
		manager = new EntityManager(this);
		playerTexture = Assets.instance.rouge.rouge_0_0;
		map = new TmxMapLoader().load("maps/tiled/test.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / 16f);
		camera = new OrthographicCamera(10, 10);
		camera.setToOrtho(false, 10, 10);
		camera.update();

		// create the player we want to move around the world
		player = new Player("Alex");
		player.position.set(20, 20);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.position.x = player.position.x;
		camera.position.y = player.position.y;

		camera.update();

		// set the tile map renderer view based on what the
		// camera sees and render the map
		renderer.setView(camera);
		renderer.render();

		Batch batch = renderer.getSpriteBatch();
		batch.begin();
		batch.draw(playerTexture, player.position.x, player.position.y, 1, 1);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		show();
	}

	public Player getPlayer() {
		return player;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
