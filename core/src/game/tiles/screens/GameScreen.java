package game.tiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.tiles.map.Map;
import game.tiles.entities.Entity;
import game.tiles.entities.Player;
import game.tiles.map.TileValueUpdater;
import game.tiles.map.MapViewer;

public class GameScreen extends ScreenAdapter {
	SpriteBatch batch;
	Viewport overlayViewport;
	Player player;
	Map map;
	MapViewer mapViewer;

	public GameScreen() {
		batch = Screens.GAME.batch;
		overlayViewport = new FitViewport(10, 10);
		overlayViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
		Entity.updateAll(delta);
		player.update(delta);

		mapViewer.updateCamera(player.getX(), player.getY());

		batch.setProjectionMatrix(mapViewer.getProjectionMatrix());
		batch.begin();
		map.render(batch, mapViewer.getX(), mapViewer.getY());
		Entity.drawAll();
		player.draw(batch);
		batch.setProjectionMatrix(overlayViewport.getCamera().combined);
		player.drawInventory(batch);
		player.drawHealth(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		mapViewer.setSize(width, height);
	}

}
