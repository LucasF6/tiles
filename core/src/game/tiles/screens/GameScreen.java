package game.tiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.tiles.entities.Alien;
import game.tiles.items.LampItem;
import game.tiles.items.Pickaxe;
import game.tiles.tiles.Map;
import game.tiles.entities.Entity;
import game.tiles.entities.Player;
import game.tiles.TileValueUpdater;
import game.tiles.Tiles;
import game.tiles.tiles.MapViewer;

public class GameScreen extends ScreenAdapter {
	SpriteBatch batch;
	Viewport overlayViewport;
	Player player;
	Map map;
	MapViewer mapViewer;

	public GameScreen(Tiles game) {
		batch = game.batch;
		overlayViewport = new FitViewport(10, 10);
		overlayViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		player = Player.getInstance();
		map = Map.getInstance();
		mapViewer = MapViewer.getInstance();
		Entity.setBatch(batch);
		TileValueUpdater.getInstance().updateAll();

		new Alien(15, 15, new Pickaxe());
		new Alien(12, 15, new Pickaxe());
		new Alien(15, 12, new LampItem());
		new Alien(12, 12, new LampItem());
		new Alien(9, 12, new LampItem());
		new Alien(12, 9, new LampItem());
		new Alien(9, 9, new LampItem());
		new Alien(9, 15, new LampItem());

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
