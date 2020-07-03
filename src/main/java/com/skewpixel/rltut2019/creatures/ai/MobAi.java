package com.skewpixel.rltut2019.creatures.ai;

import com.skewpixel.rltut2019.ecs.Entity;

public interface MobAi {

    void takeTurn(Entity entity);
    String getName();
}
