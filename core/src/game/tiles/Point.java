package game.tiles;

public class Point {
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
