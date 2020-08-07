package com.skewpixel.rltut2019.map.pathfinding;

import com.skewpixel.rltut2019.map.Point;

import java.util.Objects;

public class PointWithCost implements Comparable<PointWithCost>  {
    public Point point;
    public int cost;

    public PointWithCost(Point pt) {
        this(pt, 0);
    }

    public PointWithCost(Point pt, int cost) {
        this.point = pt;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointWithCost that = (PointWithCost) o;
        return point.equals(that.point);
    }

    @Override
    public int hashCode() {
        return point.hashCode();
    }

    @Override
    public int compareTo(PointWithCost o) {
        return Integer.compare(cost, o.cost);
    }
}
