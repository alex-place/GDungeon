package com.undeadstudio.gdungeon.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.undeadstudio.gdungeon.Assets;
import com.undeadstudio.gdungeon.Main;
import com.undeadstudio.gdungeon.ashley.components.InputComponent;
import com.undeadstudio.gdungeon.ashley.components.MovementComponent;
import com.undeadstudio.gdungeon.ashley.components.PositionComponent;
import com.undeadstudio.gdungeon.ashley.components.VisualComponent;
import com.undeadstudio.gdungeon.ashley.input.EntityController;
import com.undeadstudio.gdungeon.ashley.systems.InputSystem;
import com.undeadstudio.gdungeon.ashley.systems.MovementSystem;
import com.undeadstudio.gdungeon.ashley.systems.RenderSystem;
import com.undeadstudio.gdungeon.ashley.utils.EntityFactory;
import com.undeadstudio.gdungeon.gen.Generator;
import com.undeadstudio.gdungeon.gen.GeneratorConfig;

public class AshleyGameScreen implements Screen {

	Main main;
	PooledEngine engine;
	OrthographicCamera camera;
	InputMultiplexer input;

	MovementSystem movement;
	RenderSystem render;
	InputSystem inputSys;
	EntityController controller;

	Generator generator;

	boolean init = false;

	public AshleyGameScreen(Main main) {
		this.main = main;

	}

	@Override
	public void render(float delta) {
		Gdx.input.setInputProcessor(input);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		engine.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		if (init == false) {
			camera = new OrthographicCamera(50, 50);
			camera.position.set(25, 25, 0);
			camera.update();

			input = new InputMultiplexer();

			movement = new MovementSystem();
			render = new RenderSystem(camera);
			inputSys = new InputSystem(input);

			Texture coinTexture = new Texture("coin.png");
			TextureRegion coinRegion = new TextureRegion(coinTexture);

			engine = new PooledEngine();
			engine.addSystem(render);
			engine.addSystem(movement);
			engine.addSystem(inputSys);

			generator = new Generator(engine);
			Gdx.app.log("GAME", generator.newDungeon());

			Entity rouge = engine.createEntity();
			rouge.add(new PositionComponent(25, 25));
			rouge.add(new MovementComponent(0, 0));
			rouge.add(new VisualComponent(Assets.instance.rouge.rouge_0_0));
			rouge.add(new InputComponent(new EntityController(camera, rouge)));

			engine.addEntity(rouge);

			for (int i = 0; i < 100; i++) {
				Entity coin = engine.createEntity();
				coin.add(new PositionComponent(MathUtils.random(50), MathUtils
						.random(50)));
				coin.add(new MovementComponent(0f, 0f));
				coin.add(new VisualComponent(coinRegion));
				// coin.add(new InputComponent(new EntityController(camera,
				// coin)));
				engine.addEntity(coin);
			}

			Gdx.input.setInputProcessor(input);
			init = true;
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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
