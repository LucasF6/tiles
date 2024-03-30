package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import game.tiles.entities.ItemEntity;
import game.tiles.tiles.Map;
import game.tiles.tiles.StoneBackgroundTile;
import game.tiles.tiles.StoneTile;

import static game.tiles.Textures.Items.PICKAXE;

public class Pickaxe extends Item {
    public Pickaxe() {
        super("pickaxe", false);
    }

    public boolean use(float x, float y) {
        int i = MathUtils.floor(x);
        int j = MathUtils.floor(y);
        if (Map.getInstance().getTile(i, j) instanceof StoneTile) {
            Map.getInstance().setTile(new StoneBackgroundTile(i, j));
        }
        return false;
    }

    public ItemEntity asEntity(float x, float y) {
        return new ItemEntity(PICKAXE, this, x, y);
    }

    public TextureRegion getTexture() {
        return PICKAXE;
    }
}
