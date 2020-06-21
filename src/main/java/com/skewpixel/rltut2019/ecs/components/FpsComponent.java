package com.skewpixel.rltut2019.ecs.components;

public class FpsComponent implements Component {
    public static final String Name = "FpsComponent";

    public int fps;
    public int tps;

    @Override
    public String getName() {
        return Name;
    }
}
