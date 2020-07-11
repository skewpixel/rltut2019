package com.skewpixel.rltut2019.ecs.components;

import com.skewpixel.rltut2019.map.pathfinding.Path;

public class PathComponent implements Component {
    public static final String Name = "PathComponent";

    public Path path;

    public PathComponent() {}

    public PathComponent(Path path) {
        this.path = path;
    }

    @Override
    public void reset() {
        path = null;
    }

    @Override
    public String getName() {
        return Name;
    }
}
