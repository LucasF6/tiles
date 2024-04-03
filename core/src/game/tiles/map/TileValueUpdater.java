package game.tiles.map;

import com.badlogic.gdx.utils.Array;
import game.tiles.tiles.Tile;
import game.tiles.tiles.ValueTile;

import static game.tiles.Constants.Map.SIZE;

public class TileValueUpdater {
    private static TileValueUpdater instance;

    private final ValueTile[][] tiles = new ValueTile[SIZE][SIZE];

    private final Array<Point> open = new Array<>();
    private final Array<Point> closed = new Array<>();

    private final Map map = Map.getInstance();

    private TileValueUpdater() {}

    public static TileValueUpdater getInstance() {
        if (instance == null) {
            instance = new TileValueUpdater();
        }
        return instance;
    }

    public void update(int x, int y) {
        Tile tile = map.getTile(x, y);
        if (tile instanceof ValueTile) {
            tiles[x][y] = (ValueTile) tile;
        } else {
            tiles[x][y] = null;
        }
    }

    public void updateAll() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                update(i, j);
            }
        }
    }

    public void increment(int i, int j) {
        if (tiles[i][j] != null && tiles[i][j].getValue() != 0) {
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
                checkPoint(examining.left(), number);
                checkPoint(examining.right(), number);
                checkPoint(examining.up(), number);
                checkPoint(examining.down(), number);
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

    private void checkPoint(Point p, int number) {
        if (isPointOfValue(p, number) && !closed.contains(p, false)) {
            open.add(p);
            closed.add(p);
        }
    }

    private boolean isPointOfValue(Point p, int number) {
        return p.x >= 0 && p.y >= 0 && tiles[p.x][p.y] != null && tiles[p.x][p.y].getValue() == number;
    }

    public void lampLight(int x, int y) {
        Point lampPoint = Point.of(x, y);
        Point[] adjPoints = lampPoint.adjacentPoints();
        for (Point adjPoint : adjPoints) {
            if (!isPointOfValue(adjPoint, 4)) {
                return;
            }
        }
        for (Point adjPoint : adjPoints) {
            tiles[adjPoint.x][adjPoint.y].increment();
        }
    }

    public int getTileValue(int x, int y) {
        if (x >= 0 && y >= 0 && x < SIZE && y < SIZE && tiles[x][y] != null) {
            return tiles[x][y].getValue();
        }
        return -1;
    }

    public ValueTile getValueTile(int x, int y) {
        if (x >= 0 && y >= 0 && x < SIZE && y < SIZE) {
            return tiles[x][y];
        }
        return null;
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
            return of(x - 1, y);
        }

        public Point right() {
            return of(x + 1, y);
        }

        public Point up() {
            return of(x, y + 1);
        }

        public Point down() {
            return of(x, y - 1);
        }

        public Point[] adjacentPoints() {
            return new Point[] {left(), right(), up(), down()};
        }

    }
}
