package com.skewpixel.rltut2019.ecs.components;

public class MonsterComponent implements Component {

    public static final String Name = "MonsterComponent";

    @Override
    public String getName() {
        return Name;
    }
}
