package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MovementComponent;
import com.skewpixel.rltut2019.ecs.components.NameComponent;
import com.skewpixel.rltut2019.ecs.components.PlayerComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.map.Tile;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.services.EventService;

public class MovementGameSystem implements GameSystem {
    private final World world;
    private final EventService eventService;

    public static final String EVENT_SUBJECT_PLAYER_MOVED = "movement.player.moved";

    public MovementGameSystem(World world, EventService eventService) {
        this.world = world;
        this.eventService = eventService;
    }

    @Override
    public void tick(long time) {
        for(Entity entity : world.getEntities()) {
            MovementComponent mc = entity.getComponentByName(MovementComponent.Name, MovementComponent.class);
            PositionComponent pc = entity.getComponentByName(PositionComponent.Name, PositionComponent.class);

            if((mc != null) && (pc != null) && (requiresProcessing(mc))) {
                // TODO: check for no move
                int dx = mc.newX != null ? mc.newX : pc.x;
                int dy = mc.newY != null ? mc.newY : pc.y;
                int dl = mc.newLevel != null ? mc.newLevel : pc.level;

                if(isValidMove(dx, dy, dl)) {
                    pc.x = dx;
                    pc.y = dy;
                    pc.level = dl;

                    if(entity.hasComponent(PlayerComponent.Name)) {
                        eventService.publishEvent(EVENT_SUBJECT_PLAYER_MOVED, entity);
                    }
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
        boolean isValid = false;

        Tile t = world.getTileAt(x, y);
        if( t.isWalkable() ) {
            Entity e = world.getBlockingEntityAt(x, y, level);

            if(e == null) {
                isValid = true;
            }
            else {
                NameComponent nc = e.getComponentByName(NameComponent.Name, NameComponent.class);
                System.out.println(String.format("You kick the %s in the shins, much to its annoyance!", nc.name));
            }
        }

        return isValid;
    }
}
