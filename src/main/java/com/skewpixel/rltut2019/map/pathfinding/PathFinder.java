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

            for(Point pt : getPointNeighbors(current, end)) {
                if(!came_from.containsKey(pt)) {// && (world.isEmptyLocation(pt.x, pt.y, 0) || pt.equals(end))){
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

    public List<Point> dijkstraSearch(Point start, Point end) {
        PointWithCost start_with_cost = new PointWithCost(start, 0);

        PriorityQueue<PointWithCost> frontier = new PriorityQueue<>();
        Map<Point, Point> came_from = new HashMap<>();
        Map<Point, Integer> cost_so_far = new HashMap<>();

        frontier.add(start_with_cost);
        came_from.put(start_with_cost.point, null);
        cost_so_far.put(start_with_cost.point, start_with_cost.cost);

        while(!frontier.isEmpty()) {
            PointWithCost currentWithCost = frontier.remove();

            if(currentWithCost.point.equals(end)) {
                break;
            }

            for(Point nextPt : getPointNeighbors(currentWithCost.point, end)) {
                int new_cost = cost_so_far.get(currentWithCost.point) + getCostForPoints(currentWithCost.point, nextPt);//cost to go to next from current

                if(!cost_so_far.containsKey(nextPt) ||
                        (new_cost < cost_so_far.get(nextPt))) {
                    cost_so_far.put(nextPt, new_cost);
                    frontier.add(new PointWithCost(nextPt, new_cost));
                    came_from.put(nextPt, currentWithCost.point);
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

    public List<Point> greadyBestFirstSearch(Point start, Point end) {
        PointWithCost start_with_cost = new PointWithCost(start, 0);

        PriorityQueue<PointWithCost> frontier = new PriorityQueue<>();
        Map<Point, Point> came_from = new HashMap<>();

        frontier.add(start_with_cost);
        came_from.put(start_with_cost.point, null);

        while(!frontier.isEmpty()) {
            PointWithCost currentWithCost = frontier.remove();

            if(currentWithCost.point.equals(end)) {
                break;
            }

            for(Point nextPt : getPointNeighbors(currentWithCost.point, end)) {
                int priority = calculateManhattenPriority(end, nextPt);

                if(!came_from.containsKey(nextPt)) {
                    frontier.add(new PointWithCost(nextPt, priority));
                    came_from.put(nextPt, currentWithCost.point);
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

    public List<Point> aStarSearch(Point start, Point end) {
        PointWithCost start_with_cost = new PointWithCost(start, 0);

        PriorityQueue<PointWithCost> frontier = new PriorityQueue<>();
        Map<Point, Point> came_from = new HashMap<>();
        Map<Point, Integer> cost_so_far = new HashMap<>();

        frontier.add(start_with_cost);
        came_from.put(start_with_cost.point, null);
        cost_so_far.put(start_with_cost.point, start_with_cost.cost);

        while(!frontier.isEmpty()) {
            PointWithCost currentWithCost = frontier.remove();

            if(currentWithCost.point.equals(end)) {
                break;
            }

            for(Point nextPt : getPointNeighbors(currentWithCost.point, end)) {
                int new_cost = cost_so_far.get(currentWithCost.point) + getCostForPoints(currentWithCost.point, nextPt);//cost to go to next from current

                if(!cost_so_far.containsKey(nextPt) ||
                        (new_cost < cost_so_far.get(nextPt))) {
                    int priority = new_cost + calculateManhattenPriority(end, nextPt);
                    cost_so_far.put(nextPt, new_cost);
                    frontier.add(new PointWithCost(nextPt, priority));
                    came_from.put(nextPt, currentWithCost.point);
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

    private int calculateManhattenPriority(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private int getCostForPoints(Point from, Point to) {

        if((from.x == to.x) || (from.y == to.y)) {
            return 0;
        }

        return 1;
    }

    public List<Point> getPointNeighbors(Point pt, Point end) {
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
                    if(world.isEmptyLocation(x, y, 0) || ((end.x == x) && (end.y == y))) {
                        points.add(new Point(x, y));
                    }
                }
            }
        }

        return points;
    }
}