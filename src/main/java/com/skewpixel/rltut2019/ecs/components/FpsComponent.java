package com.skewpixel.rltut2019.ecs.components;

public class FpsComponent implements Component {
    public static final String Name = "FpsComponent";

    public int fps;
    public int tps;

    @Override
    public void reset() {
        fps = 0;
        tps = 0;
    }

    @Override
    public String getName() {
        return Name;
    }
}
