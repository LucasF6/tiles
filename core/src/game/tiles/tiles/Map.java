package game.tiles.tiles;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import game.tiles.TileValueUpdater;
import game.tiles.items.LampItem;

import static game.tiles.Constants.Map.*;
import static game.tiles.Textures.Tiles.STONE_BACKGROUND;

public class Map {
    private static Map instance;

    private final Tile[][] tiles = new Tile[SIZE][SIZE];
    private float offset = 5;

    private Map() {
        /**
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j] = new GrassTile(i, j);
            }
        }
        tiles[5][10] = new StoneTile(5, 10);
        tiles[5][11] = new LampTile(STONE_BACKGROUND, 5, 11);
        tiles[6][11] = new StoneTile(6, 11);
        tiles[5][12] = new WoodTile(5, 12);
        */
        JsonReader reader = new JsonReader();
        JsonValue map = reader.parse(new FileHandle("map.json"));
        for (int i = 0; i < SIZE; i++) {
            int[] row = map.get(i).asIntArray();
            for (int j = 0; j < SIZE; j++) {
                switch (row[j]) {
                    case 0:
                        tiles[i][j] = new GrassTile(i, j);
                        break;
                    case 1:
                        tiles[i][j] = new StoneBackgroundTile(i, j);
                        break;
                    case 2:
                        tiles[i][j] = new WoodTile(i, j);
                        break;
                    case 3:
                        tiles[i][j] = new StoneTile(i, j);
                        break;
                    case 4:
                        tiles[i][j] = new LampTile(STONE_BACKGROUND, i, j);
                        break;
                }
            }
        }
        tiles[46][15] = new LockedTile(5, new LampItem(), 46, 15);
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public void getLeftClicked(int i, int j) {
        if (i < 0 || i >= SIZE || j < 0 || j >= SIZE) {
            System.out.println("OUT OF RANGE");
            return;
        }
        tiles[i][j].getLeftClicked();
    }

    public void getRightClicked(int i, int j) {
        if (i < 0 || i >= SIZE || j < 0 || j >= SIZE) {
            System.out.println("OUT OF RANGE");
            return;
        }
        tiles[i][j].getRightClicked();
    }

    public void setWorldSize(float worldSize) {
        offset = worldSize / 2;
    }

    public boolean isBlocked(float leftX, float rightX, float y) {
        int j = (int) Math.floor(y);
        int li = (int) Math.floor(leftX);
        int ri = (int) Math.floor(rightX);
        return tileBlocked(li, j, leftX - li, rightX - li, y - j)
                || tileBlocked(ri, j, leftX - ri, rightX - ri, y - j);
    }

    private boolean tileBlocked(int i, int j, float leftX, float rightX, float y) {
        if (i < 0 || i > SIZE || j < 0 || j > SIZE) {
            return true;
        }
        return tiles[i][j].isSolid(leftX, rightX, y);
    }

    public boolean isBlocked(float x, float y) {
        if (x < 0 || x > SIZE || y < 0 || y > SIZE) {
            return true;
        }
        int i = (int) Math.floor(x);
        int j = (int) Math.floor(y);
        return tiles[i][j].isSolid(x - i, y - i);
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public void setTile(Tile tile) {
        tiles[tile.x][tile.y] = tile;
        TileValueUpdater.getInstance().update(tile.x, tile.y);
    }

    // x and y in world coordinates
    public void render(SpriteBatch batch, float playerX, float playerY) {
        int startX = (int) Math.floor(playerX - offset);
        int startY = (int) Math.floor(playerY - offset);
        int endX = (int) Math.floor(playerX + offset);
        int endY = (int) Math.floor(playerY + offset);
        if (startX < 0) {
            startX = 0;
        }
        if (startY < 0) {
            startY = 0;
        }
        if (endX >= SIZE) {
            endX = SIZE - 1;
        }
        if (endY >= SIZE) {
            endY = SIZE - 1;
        }
        for (int i = 0; i <= endX - startX; i++) {
            for (int j = 0; j <= endY - startY; j++) {
                tiles[i + startX][j + startY].draw(batch);
            }
        }
    }

}
