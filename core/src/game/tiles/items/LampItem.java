package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import game.tiles.entities.ItemEntity;
import game.tiles.tiles.BackgroundTile;
import game.tiles.tiles.LampTile;
import game.tiles.tiles.Map;
import game.tiles.tiles.Tile;

import static game.tiles.Textures.Items.LAMP;

public class LampItem extends Item {

    public LampItem() {
        super("lamp", true);
    }

    @Override
    public boolean use(float x, float y) {
        int i = MathUtils.floor(x);
        int j = MathUtils.floor(y);
        Tile currentTile = Map.getInstance().getTile(i, j);
        if (currentTile instanceof BackgroundTile) {
            Map.getInstance().setTile(new LampTile(currentTile.getTexture(), i, j));
            count--;
        }
        return count == 0;
    }

    @Override
    public ItemEntity asEntity(float x, float y) {
        return new ItemEntity(LAMP, this, x, y);
    }

    @Override
    public TextureRegion getTexture() {
        return LAMP;
    }

}
