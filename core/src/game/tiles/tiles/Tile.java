package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static game.tiles.Textures.Tiles.GRASS;

public class Tile {
    protected int x;
    protected int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public final void draw(SpriteBatch batch) {
        batch.draw(getTexture(), x, y, 1, 1);
        addLayers(batch, x, y);
    }

    public void getLeftClicked() {}

    public void getRightClicked() {}

    public void addLayers(SpriteBatch batch, float x, float y) {}

    public TextureRegion getTexture() {
        return GRASS;
    }

    // consider the tile as being in the unit square
    public boolean isSolid(float leftX, float rightX, float y) {
        return false;
    }

    public boolean isSolid(float x, float y) {
        return false;
    }
}
