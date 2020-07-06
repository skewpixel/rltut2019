package com.skewpixel.rltut2019.ecs.components;

public class PlayerComponent implements Component {

    public static final String Name = "PlayerComponent";

    public PlayerComponent() {}

    @Override
    public void reset() {
    }

    @Override
    public String getName() {
        return Name;
    }
}
