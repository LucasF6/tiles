package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class BlockTile extends Tile {
    private final TextureRegion texture;

    public BlockTile(TextureRegion texture) {
        this.texture = texture;
    }

    @Override
    public final TextureRegion getTexture() {
        return texture;
    }

    @Override
    public final boolean isBlocking(float leftX, float rightX, float y) {
        return leftX < 1 && rightX > 0 && 0 < y && y < 1;
    }

}
