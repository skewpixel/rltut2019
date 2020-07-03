package com.skewpixel.rltut2019.creatures.ai;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.NameComponent;

public class BasicMobAi implements MobAi {
    public static final String Name = "BasicMobAi";

    @Override
    public void takeTurn(Entity entity) {
        NameComponent nc = entity.getComponentByName(NameComponent.Name, NameComponent.class);

        System.out.println("The " + nc.name + " ponders the meaning of its existence.");
    }

    @Override
    public String getName() {
        return Name;
    }
}
