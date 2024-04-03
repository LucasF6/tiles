package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import game.tiles.tiles.LockTile;
import game.tiles.map.Map;
import game.tiles.tiles.Tile;

import static game.tiles.Textures.Items.KEY;

public class Key extends Item {
    private final String password;

    public Key(String password) {
        super("key", false);
        this.password = password;
    }

    public boolean use(float x, float y) {
        int i = MathUtils.floor(x);
        int j = MathUtils.floor(y);
        Tile tile = Map.getInstance().getTile(i, j);
        if (tile instanceof LockTile) {
            return ((LockTile) tile).acceptKey(this);
        }
        return false;
    }

    public TextureRegion getTexture() {
        return KEY;
    }

    public String getPassword() {
        return password;
    }

}
