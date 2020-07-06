package com.skewpixel.rltut2019.ecs.components;

public class FovComponent implements Component {

    public static final String Name = "FovComponent";

    public int viewRadius;

    public FovComponent() {}

    public FovComponent(int viewRadius) {
        this.viewRadius = viewRadius;
    }


    @Override
    public void reset() {
        viewRadius = 0;
    }

    @Override
    public String getName() {
        return Name;
    }
}
