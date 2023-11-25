package game.tiles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.tiles.controllers.Controller;
import game.tiles.controllers.DefaultController;

public class Tiles extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;
	Viewport viewport;
	Controller controller;
	Player player;
	Map map;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		viewport = new FitViewport(10, 10, camera);
		camera.position.set(0, 0, 0);
		controller = new DefaultController();
		player = new Player();
		map = new Map();

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean scrolled(float x, float y) {
				float projectedSize = viewport.getWorldWidth() + 0.5f * y;
				if (3.0 <= projectedSize && projectedSize <= 20) {
					viewport.setWorldSize(viewport.getWorldWidth() + 0.5f * y, viewport.getWorldHeight() + 0.5f * y);
					viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
					map.setWorldSize(viewport.getWorldWidth());
				}
				return true;
			}
		});
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		updateCamera();

		player.update(controller, map);
		if (Gdx.input.justTouched()) {
			Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			viewport.unproject(vec);
			map.onClick((int) Math.floor(vec.x), (int) Math.floor(vec.y));
		}

		batch.begin();
		map.render(batch, player.getX(), player.getY());
		batch.draw(player.getTextureRegion(), player.getX() - 0.75f / 2, player.getY(), 0.75f, 1f);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		Textures.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}

	public void updateCamera() {
		camera.position.set(player.getX(), player.getY(), 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}

}
