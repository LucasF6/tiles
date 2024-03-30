package game.tiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.tiles.items.LampItem;
import game.tiles.tiles.Map;
import game.tiles.entities.Entity;
import game.tiles.entities.Player;
import game.tiles.TileValueUpdater;
import game.tiles.Tiles;
import game.tiles.tiles.MapViewer;

import static game.tiles.Constants.Map.SIZE;

public class GameScreen extends ScreenAdapter {
	SpriteBatch batch;
	Viewport inventoryViewport;
	Player player;
	Map map;
	MapViewer mapViewer;

	public GameScreen(Tiles game) {
		batch = game.batch;
		inventoryViewport = new FitViewport(10, 10);
		inventoryViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		player = Player.getInstance();
		map = Map.getInstance();
		mapViewer = MapViewer.getInstance();
		Entity.setBatch(batch);
		TileValueUpdater.getInstance().updateAll();

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override
			public boolean scrolled(float x, float y) {
				player.switchSelectedItem(MathUtils.floor(y));
				return true;
			}
		});
	}

	@Override
	public void render (float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		mapViewer.updateCamera(player.getX(), player.getY());

		Entity.updateAll(Gdx.graphics.getDeltaTime());

		batch.setProjectionMatrix(mapViewer.getProjectionMatrix());
		batch.begin();
		map.render(batch, mapViewer.getX(), mapViewer.getY());
		Entity.drawAll();
		batch.setProjectionMatrix(inventoryViewport.getCamera().combined);
		player.drawInventory(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		mapViewer.setSize(width, height);
	}

}
