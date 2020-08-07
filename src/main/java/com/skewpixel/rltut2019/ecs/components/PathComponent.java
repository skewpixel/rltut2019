package com.skewpixel.rltut2019.ecs.components;

import com.skewpixel.rltut2019.map.pathfinding.Path;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PathComponent implements Component {
    public static final String Name = "PathComponent";

    public List<Path> paths = new ArrayList<>();
    public List<Color> colors = new ArrayList<>();

    public void addPath(Path path, Color color) {
        paths.add(path);
        colors.add(color);
    }

    public PathComponent() { }

    @Override
    public void reset() {
        paths.clear();
        colors.clear();
    }

    @Override
    public String getName() {
        return Name;
    }
}
