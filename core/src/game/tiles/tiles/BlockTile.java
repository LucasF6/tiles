package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class BlockTile extends Tile {
    private final TextureRegion texture;

    public BlockTile(TextureRegion texture, int x, int y) {
        super(x, y);
        this.texture = texture;
    }

    @Override
    public final TextureRegion getTexture() {
        return texture;
    }

    @Override
    public final boolean isSolid(float leftX, float rightX, float y) {
        return leftX < 1 && rightX > 0 && 0 < y && y < 1;
    }

    @Override
    public final boolean isSolid(float x, float y) {
        return true;
    }

}
