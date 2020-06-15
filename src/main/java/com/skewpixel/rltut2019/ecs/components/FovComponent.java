package com.skewpixel.rltut2019.ecs.components;

public class FovComponent implements Component {

    public static final String Name = "FovComponent";

    public int viewRadius;

    public FovComponent(int viewRadius) {
        this.viewRadius = viewRadius;
    }


    @Override
    public String getName() {
        return Name;
    }
}
