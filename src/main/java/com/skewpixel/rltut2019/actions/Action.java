package com.skewpixel.rltut2019.actions;

import com.skewpixel.rltut2019.ecs.Entity;

public interface Action {

    boolean isValidAction(Entity performer, Object performee);
    boolean execute(Entity performer, Object performee);
}
