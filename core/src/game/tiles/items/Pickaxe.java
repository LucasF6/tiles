package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import game.tiles.map.Map;
import game.tiles.tiles.*;

import static game.tiles.Textures.Items.PICKAXE;

public class Pickaxe extends Item {
    public Pickaxe() {
        super("pickaxe", false);
    }

    public boolean use(float x, float y) {
        int i = MathUtils.floor(x);
        int j = MathUtils.floor(y);
        Tile tile = Map.getInstance().getTile(i, j);
        if (tile instanceof Breakable) {
            ((Breakable) tile).getHit();
        }
        return false;
    }

    public TextureRegion getTexture() {
        return PICKAXE;
    }
}
