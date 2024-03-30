package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BackgroundTile extends ValueTile {
    private final TextureRegion texture;

    public BackgroundTile(TextureRegion texture, int x, int y) {
        super(x, y);
        this.texture = texture;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public final boolean isSolid(float leftX, float rightX, float y) {
        return false;
    }

    @Override
    public final boolean isSolid(float x, float y) {
        return false;
    }

}
