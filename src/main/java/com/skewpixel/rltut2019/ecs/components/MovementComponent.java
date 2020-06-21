package com.skewpixel.rltut2019.ecs.components;

public class MovementComponent implements Component {
    public static final String Name = "MovementComponent";

    public Integer newX;
    public Integer newY;
    public Integer newLevel;

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return String.format("{ mx=%d, my=%d, ml=%d }", newX, newY, newLevel);
    }
}
