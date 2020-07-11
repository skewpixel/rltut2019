package com.skewpixel.rltut2019.map.pathfinding;

import com.skewpixel.rltut2019.map.Point;
import com.skewpixel.rltut2019.map.World;

import java.util.List;

public class Path {
    private List<Point> points;

    private Path(List<Point> points) { this.points = points; }

    public List<Point> getPoints() {
        return points;
    }

    public static Path findPath(Point start, Point end, PathFindingAlgorithm searchType, World world) {
        PathFinder pf = new PathFinder(world);
        switch (searchType) {
            case BreadthFirst:
            case BreadthFirstEarlyExit:
                return new Path(pf.breadthFirstSearch(start, end, searchType == PathFindingAlgorithm.BreadthFirstEarlyExit));
        }

        throw new RuntimeException("Invalid PathFindingAlgorithm provided. Unable to compute path");
    }
}
