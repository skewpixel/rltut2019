package com.skewpixel.rltut2019.map;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private final List<Point> points = new ArrayList<>();

    public List<Point> getPoints() { return points; }

    private Line() {}

    public static Line createLine(int x0, int y0, int x1, int y1) {

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int signX = (x0 < x1) ? 1 : -1;
        int signY = (y0 < y1) ? 1 : -1;

        int err = dx - dy;

        Line line = new Line();
        while(true) {
            line.points.add(new Point(x0, y0));

            if((x0 == x1) && (y0 == y1)) break;

            int e2 = err * 2;

            if(e2 > -dx) {
                err -= dy;
                x0 += signX;
            }

            if(e2 < dx) {
                err += dx;
                y0 += signY;
            }
        }

        return line;
    }
}
