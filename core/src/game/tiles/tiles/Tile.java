package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static game.tiles.Textures.Tiles.NUMBERS;

public abstract class Tile {
    protected int value;

    public final int getValue() {
        return value;
    }

    public final void zeroValue() {
        value = 0;
    }

    public final void increment() {
        value++;
    }

    public final void draw(SpriteBatch batch, float x, float y) {
        batch.draw(getTexture(), x, y, 1, 1);
        if (0 < value && value <= 9) {
            batch.draw(NUMBERS[value - 1], x, y, 1, 1);
        }
    }

    // All properties default to those of background tiles

    public boolean isSolid() {
        return false;
    }

    public void onClick() {}

    public abstract TextureRegion getTexture();

}
