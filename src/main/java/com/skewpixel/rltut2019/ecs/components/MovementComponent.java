package com.skewpixel.rltut2019.ecs.components;

public class MovementComponent implements Component {
    public static final String Name = "MovementComponent";

    public Integer newX;
    public Integer newY;
    public Integer newLevel;

    public MovementComponent() {}

    @Override
    public void reset() {
        newX = null;
        newY = null;
        newLevel = null;
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return String.format("{ mx=%d, my=%d, ml=%d }", newX, newY, newLevel);
    }
}
