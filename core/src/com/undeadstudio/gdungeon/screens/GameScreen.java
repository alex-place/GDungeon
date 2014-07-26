package com.undeadstudio.gdungeon.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.undeadstudio.gdungeon.Assets;
import com.undeadstudio.gdungeon.Main;
import com.undeadstudio.gdungeon.screens.game.CameraHelper;
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
	private CameraHelper helper;
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
		MapProperties prop = map.getProperties();

		renderer = new OrthogonalTiledMapRenderer(map, 1 / 32f);
		camera = new OrthographicCamera(10, 10);
		camera.update();

		helper = new CameraHelper();
		helper.setZoom(2f);
		helper.setPosition(-10, -10);
		helper.applyTo(camera);

		// create the player we want to move around the world
		player = new Player("Alex");
		player.position.set(0, 1);

		helper.setTarget(player);
		
					TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(1);
					layer.getCell(0, 0);

	}

	@Override
	public void render(float delta) {
		handleDebugInput(delta);

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		helper.update(delta);
		helper.applyTo(camera);

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

	private void moveCamera(float x, float y) {
		x += helper.getPosition().x;
		y += helper.getPosition().y;
		helper.setPosition(x, y);
	}

	private void handleDebugInput(float delta) {
		if (Gdx.app.getType() != ApplicationType.Desktop)
			return;

		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}

		// Camera Controls (move)
		float camMoveSpeed = 1 * delta;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camMoveSpeed *= camMoveSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			moveCamera(-camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			moveCamera(camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.UP))
			moveCamera(0, camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			moveCamera(0, -camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE))
			helper.setPosition(0, 0);

		// Camera Controls (zoom)
		float camZoomSpeed = 1 * delta;
		float camZoomSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT))
			camZoomSpeed *= camZoomSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.COMMA))
			helper.addZoom(camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.PERIOD))
			helper.addZoom(-camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.SLASH))
			helper.setZoom(1);

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

	public CameraHelper getHelper() {
		return helper;

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
