package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;

public class EntityCollisionContext {
    public Entity collider;
    public Entity collidee;

    public EntityCollisionContext(Entity collider, Entity collidee) {
        this.collider = collider;
        this.collidee = collidee;
    }
}
