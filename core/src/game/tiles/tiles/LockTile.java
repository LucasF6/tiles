package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.tiles.items.Key;

import static game.tiles.Textures.Tiles.LOCK;

public class LockTile extends BlockTile {
    private String password;
    private Tile hiddenTile;

    public LockTile(String password, Tile hiddenTile, int x, int y) {
        super(LOCK, x, y);
        this.password = password;
        this.hiddenTile = hiddenTile;
    }

    public LockTile(String password, int x, int y) {
        this(password, new StoneBackgroundTile(x, y), x, y);
    }

    public boolean acceptKey(Key key) {
        if (key.getPassword().equals(password)) {
            Map.getInstance().setTile(hiddenTile);
            return true;
        }
        return false;
    }

}
