package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundTile extends Tile {
    private TextureRegion texture;

    public BackgroundTile(TextureRegion texture) {
        this.texture = texture;
    }

    @Override
    public final TextureRegion getTexture() {
        return texture;
    }

}
