package com.skewpixel.rltut2019.ecs.components;

public class PositionComponent implements Component {

    public static final String Name = "PositionComponent";

    public int x;
    public int y;
    public int level;

    public PositionComponent(int x, int y) {
        this(x, y, 0);
    }

    public PositionComponent(int x, int y, int level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return String.format("{ x=%d, y=%d, l=%d }", x, y, level);
    }
}
