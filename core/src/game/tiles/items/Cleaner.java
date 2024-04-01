package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import game.tiles.TileValueUpdater;

import static game.tiles.Textures.Items.CLEANER;

public class Cleaner extends Item {
    public Cleaner() {
        super("cleaner", false);
    }

    public boolean use(float x, float y) {
        int i = MathUtils.floor(x);
        int j = MathUtils.floor(y);
        if (TileValueUpdater.getInstance().getTileValue(i, j) != -1) {
            TileValueUpdater.getInstance().getValueTile(i, j).zeroValue();
        }
        return false;
    }

    public TextureRegion getTexture() {
        return CLEANER;
    }

}
