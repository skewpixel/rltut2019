package com.skewpixel.rltut2019.ecs.components;

import com.skewpixel.rltut2019.ecs.Entity;

public class TargetEntityComponent implements Component {
    public static final String Name = "TargetEntityComponent";

    // TOOD: change this to an entity id, using the entity reference is generally considered a bad itea (TM)
    public Entity target;

    public TargetEntityComponent() {}

    public TargetEntityComponent(Entity target) {
        this.target = target;
    }

    @Override
    public void reset() {
        target = null;
    }

    @Override
    public String getName() {
        return Name;
    }
}
