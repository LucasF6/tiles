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
    public final boolean isBlocking(Rectangle r, int x, int y) {

        return x < r.x + r.width && x + 1 > r.x && y < r.y + r.height && y + 1 > r.y;
    }

}
