package com.skewpixel.rltut2019.ecs.components;

public class AttackerComponent implements Component {
    public static final String Name = "AttackerComponent";

    public int power;

    public AttackerComponent() {}

    public AttackerComponent(int power) {
        this.power = power;
    }

    @Override
    public void reset() {
        power = 0;
    }

    @Override
    public String getName() {
        return Name;
    }
}