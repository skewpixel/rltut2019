package com.skewpixel.rltut2019.map;

public class Rect {
    public Point p1;
    public Point p2;

    public Rect(int x, int y, int w, int h) {
        p1 = new Point(x, y);
        p2 = new Point(x + w, y + h);
    }

    public Point getCenter() {
        return new Point((p1.x + p2.x)/2, (p1.y + p2.y)/2);
    }

    public boolean intersects(Rect other) {
        return p1.x <= other.p2.x && p2.x >= other.p1.x &&
                p1.y <= other.p2.y && p2.y >= other.p1.y;
    }
}
