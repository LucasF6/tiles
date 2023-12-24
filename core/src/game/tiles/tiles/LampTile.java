package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static game.tiles.Textures.Tiles.LAMP_OFF;
import static game.tiles.Textures.Tiles.LAMP_ON;

public class LampTile extends Tile {
    private static final float LEFT_X = 25f / 128;
    private static final float RIGHT_X = 103f / 128;
    private static final float BOTTOM_Y = 25f / 128;
    private static final float TOP_Y = 103f / 128;
    private final TextureRegion texture;
    private boolean isOn = false;

    public LampTile(TextureRegion texture) {
        this.texture = texture;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public void getClicked() {
        isOn = !isOn;
    }

    @Override
    public void addLayers(SpriteBatch batch, float x, float y) {
        batch.draw(isOn ? LAMP_ON : LAMP_OFF, x, y, 1, 1);
    }

    @Override
    public boolean isBlocking(float leftX, float rightX, float y) {
        return leftX < RIGHT_X && rightX > LEFT_X && BOTTOM_Y < y && y < TOP_Y;
    }

}