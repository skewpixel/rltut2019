package com.skewpixel.rltut2019.ecs.components;

public class BlocksMovementComponent implements Component {
    public static final String Name = "BlocksMovementComponent";

    public BlocksMovementComponent() {}


    @Override
    public void reset() {
    }

    @Override
    public String getName() {
        return Name;
    }
}
