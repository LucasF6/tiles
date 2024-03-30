package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.tiles.TileValueUpdater;

import static game.tiles.Textures.Tiles.LAMP_OFF;
import static game.tiles.Textures.Tiles.LAMP_ON;

public class LampTile extends Tile {
    private static final float LEFT_X = 25f / 128;
    private static final float RIGHT_X = 103f / 128;
    private static final float BOTTOM_Y = 25f / 128;
    private static final float TOP_Y = 103f / 128;
    private final TextureRegion texture;
    private boolean isOn = false;

    public LampTile(TextureRegion texture, int x, int y) {
        super(x, y);
        this.texture = texture;
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public void getLeftClicked() {
        isOn = !isOn;
        if (isOn) {
            TileValueUpdater.getInstance().lampLight(x, y);
        }
    }

    @Override
    public void addLayers(SpriteBatch batch, float x, float y) {
        batch.draw(isOn ? LAMP_ON : LAMP_OFF, x, y, 1, 1);
    }

    @Override
    public boolean isSolid(float leftX, float rightX, float y) {
        return leftX < RIGHT_X && rightX > LEFT_X && BOTTOM_Y < y && y < TOP_Y;
    }

    @Override
    public boolean isSolid(float x, float y) {
        return LEFT_X < x && x < RIGHT_X && BOTTOM_Y < y && y < TOP_Y;
    }

}
