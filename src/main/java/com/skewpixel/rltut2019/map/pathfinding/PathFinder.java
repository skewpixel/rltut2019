package com.skewpixel.rltut2019.map.pathfinding;

import com.skewpixel.rltut2019.map.Point;
import com.skewpixel.rltut2019.map.World;

import java.util.*;

// pathfinding routines from https://www.redblobgames.com/pathfinding/a-star/introduction.html

public class PathFinder {
    World world;

    public PathFinder(World world) {
        this.world = world;
    }

    public List<Point> breadthFirstSearch(Point start, Point end, boolean earlyExit) {
        Queue<Point> frontier = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();
        Map<Point, Point> came_from = new HashMap<>();

        frontier.add(start);
        visited.add(start);

        while(!frontier.isEmpty()) {
            Point current = frontier.remove();

            if(earlyExit && (current.equals(end))) {
                break;
            }

            for(Point pt : getPointNeighbors(current)) {
                if(!came_from.containsKey(pt) && (world.isEmptyLocation(pt.x, pt.y, 0) || pt.equals(end))){
                    frontier.add(pt);
                    came_from.put(pt, current);
                }
            }
        }

        List<Point> points = new ArrayList<>();
        Point current = end;
        while(current != start) {
            points.add(current);
            current = came_from.get(current);
        }
        Collections.reverse(points);

        return points;
    }

    public List<Point> getPointNeighbors(Point pt) {
        List<Point> points = new ArrayList<>();

        for(int ox = -1; ox < 2; ox++ ) {
            for(int oy = -1; oy < 2; oy++) {
                if((ox == 0) && (oy == 0)) {
                    continue;
                }

                int x = pt.x + ox;
                int y = pt.y + oy;

                if((x >= 0) && (x < world.getWidth()) &&
                        (y >= 0) && (y < world.getHeight())) {
                    points.add(new Point(x, y));
                }
            }
        }

        return points;
    }
}