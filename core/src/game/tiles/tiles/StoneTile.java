package game.tiles.tiles;

import game.tiles.map.Map;

import static game.tiles.Textures.Tiles.STONE;

public class StoneTile extends BlockTile implements Breakable {
    private int health = 3;

    public StoneTile(int x, int y) {
        super(STONE, x, y);
    }

    public void getHit() {
        health--;
        if (health == 0) {
            Map.getInstance().setTile(new StoneBackgroundTile(x, y));
        }
    }

}
