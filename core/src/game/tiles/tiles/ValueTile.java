package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.tiles.map.TileValueUpdater;

import static game.tiles.Textures.Tiles.NUMBERS;

public class ValueTile extends Tile {
    protected int value;

    public ValueTile(int x, int y) {
        super(x, y);
    }

    public final int getValue() {
        return value;
    }

    public final void zeroValue() {
        value = 0;
    }

    public final void increment() {
        value++;
    }

    public void getLeftClicked() {
        TileValueUpdater.getInstance().increment(x, y);
    }

    public final void addLayers(SpriteBatch batch, float x, float y) {
        addFurtherLayers();
        if (0 < value && value <= 9) {
            batch.draw(NUMBERS[value - 1], x, y, 1, 1);
        }
    }

    public void addFurtherLayers() {}
}
