package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    public final boolean isSolid() {
        return true;
    }

}
