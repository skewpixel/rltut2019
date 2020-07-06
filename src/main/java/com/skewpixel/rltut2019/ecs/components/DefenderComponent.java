package com.skewpixel.rltut2019.ecs.components;

public class DefenderComponent implements Component {
    public static final String Name = "DefenderComponent";

    public int defence;

    public DefenderComponent() {}

    public DefenderComponent(int defence) {
        this.defence = defence;
    }

    @Override
    public void reset() {
        defence = 0;
    }

    @Override
    public String getName() {
        return Name;
    }
}