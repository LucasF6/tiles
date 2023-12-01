package game.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import game.tiles.tiles.*;

import static game.tiles.Constants.Map.*;
import static game.tiles.Textures.Tiles.STONE_BACKGROUND;

public class Map {
    private final Tile[][] tiles = new Tile[SIZE][SIZE];
    private float offset = 5;
    private final Array<Point> open = new Array<>(); // contains points that need to be examined
    private final Array<Point> closed = new Array<>(); // contains open and points that were already examined

    public Map() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j] = new GrassTile();
            }
        }
        tiles[5][10] = new StoneTile();
        tiles[5][11] = new LampTile(STONE_BACKGROUND);
        tiles[6][11] = new StoneTile();
        tiles[5][12] = new WoodTile();
    }

    public void getClicked(int i, int j) {
        if (i < 0 || i >= SIZE || j < 0 || j >= SIZE) {
            System.out.println("OUT OF RANGE");
            return;
        }
        tiles[i][j].getClicked();
        if (tiles[i][j].getValue() != 0) {
            return;
        }
        int number = 1;
        open.clear();
        closed.clear();
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
        return tiles[i][j].isBlocking(leftX, rightX, y);
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
                tiles[i + startX][j + startY].draw(batch, startX + i, startY + j);
            }
        }
    }

    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Point)) {
                return false;
            }
            Point p = (Point) obj;
            return x == p.x && y == p.y;
        }

        public static Point of(int x, int y) {
            return new Point(x, y);
        }

        public Point left() {
            return Point.of(x - 1, y);
        }

        public Point right() {
            return Point.of(x + 1, y);
        }

        public Point up() {
            return Point.of(x, y + 1);
        }

        public Point down() {
            return Point.of(x, y - 1);
        }

    }

}
