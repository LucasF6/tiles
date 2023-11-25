package game.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import game.tiles.tiles.*;

public class Map {
    private final int size = 40;

    private final Tile[][] tiles = new Tile[size][size];
    private float offset = 5;

    public Map() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new GrassTile();
            }
        }
        tiles[5][10] = new StoneTile();
        tiles[5][11] = new StoneBackgroundTile();
        tiles[5][12] = new WoodTile();
    }

    public void onClick(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            System.out.println("OUT OF RANGE");
            return;
        }
        if (tiles[i][j].getValue() != 0) {
            return;
        }
        int number = 1;
        Array<Point> open = new Array<>(); // contains points that need to be examined
        Array<Point> closed = new Array<>(); // contains all the points of open and points that were already examined
        open.add(Point.of(i, j));
        closed.add(Point.of(i, j));
        tiles[i][j].increment();
        Point examining;
        while (true) {
            while (!open.isEmpty()) {
                examining = open.pop();
                checkPoint(examining.left(), open, closed, number);
                checkPoint(examining.right(), open, closed, number);
                checkPoint(examining.up(), open, closed, number);
                checkPoint(examining.down(), open, closed, number);
            }
            if (closed.size < 3) {
                break;
            }
            for (Point point : closed) {
                if (point.x != i || point.y != j) {
                    tiles[point.x][point.y].zeroValue();
                }
            }
            open.clear();
            closed.clear();
            tiles[i][j].increment();
            open.add(Point.of(i, j));
            closed.add(Point.of(i, j));
            number++;
        }
    }

    private void checkPoint(Point p, Array<Point> open, Array<Point> closed, int number) {
        if (p.x >= 0 && tiles[p.x][p.y].getValue() == number && !closed.contains(p, false)) {
            open.add(p);
            closed.add(p);
        }
    }

    public void setWorldSize(float worldSize) {
        offset = worldSize / 2;
    }

    public boolean allowPosition(float x, float y) {
        if (x < 0 || x >= 64 || y < 0 || y >= 64) {
            return false;
        }
        return !tiles[(int) x][(int) y].isSolid();
    }

    // x and y in world coordinates
    public void render(SpriteBatch batch, float playerX, float playerY) {
        int startX = (int) (playerX - offset);
        int startY = (int) (playerY - offset);
        int endX = (int) (playerX + offset);
        int endY = (int) (playerY + offset);
        if (startX < 0) {
            startX = 0;
        }
        if (startY < 0) {
            startY = 0;
        }
        if (endX >= size) {
            endX = size - 1;
        }
        if (endY >= size) {
            endY = size - 1;
        }
        for (int i = 0; i <= endX - startX; i++) {
            for (int j = 0; j <= endY - startY; j++) {
                tiles[i + startX][j + startY].draw(batch, startX + i, startY + j);
            }
        }
    }

}
