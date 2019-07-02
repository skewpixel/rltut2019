package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MovementComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.map.World;

public class MovementGameSystem implements GameSystem {

    private final World world;

    public MovementGameSystem(World world) {
        this.world = world;
    }

    @Override
    public void tick() {
        for(Entity entity : world.getEntities()) {
            MovementComponent mc = entity.getComponentByName(MovementComponent.Name, MovementComponent.class);
            PositionComponent pc = entity.getComponentByName(PositionComponent.Name, PositionComponent.class);

            if((mc != null) && (pc != null) && (requiresProcessing(mc))) {
                int dx = mc.newX != null ? mc.newX : pc.x;
                int dy = mc.newY != null ? mc.newY : pc.y;
                int dl = mc.newLevel != null ? mc.newLevel : pc.level;

                if(isValidMove(dx, dy, dl)) {
                    pc.x = dx;
                    pc.y = dy;
                    pc.level = dl;
                }

                mc.newX = null;
                mc.newY = null;
                mc.newLevel = null;
            }
        }
    }

    private boolean requiresProcessing(MovementComponent mc) {
        return (mc.newX != null) || (mc.newY != null) || (mc.newLevel != null);
    }

    private boolean isValidMove(int x, int y, int level) {
        return world.isEmptyLocation(x, y, level);
    }
}
