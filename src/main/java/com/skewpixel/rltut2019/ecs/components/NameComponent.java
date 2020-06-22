package com.skewpixel.rltut2019.ecs.components;

public class NameComponent implements Component {
    public static final String Name = "NameComponent";

    public String name;

    public NameComponent(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return Name;
    }
}
