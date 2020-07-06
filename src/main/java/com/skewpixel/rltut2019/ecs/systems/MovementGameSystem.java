package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MovementComponent;
import com.skewpixel.rltut2019.ecs.components.PlayerComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.map.Tile;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.services.EventService;
import com.skewpixel.rltut2019.services.EventSubjects;
import com.skewpixel.rltut2019.state.GlobalState;

public class MovementGameSystem implements GameSystem {
    private final World world;
    private final EventService eventService;

    public MovementGameSystem(World world, EventService eventService) {
        this.world = world;
        this.eventService = eventService;
    }

    @Override
    public void tick(long time) {
        for(Entity entity : world.getEntities()) {
            MovementComponent mc = entity.removeComponentByName(MovementComponent.Name, MovementComponent.class);

            if (mc != null) {
                if(requiresProcessing(mc)) {
                    PositionComponent pc = entity.getComponentByName(PositionComponent.Name, PositionComponent.class);

                    if (pc != null) {
                        // TODO: check for no move
                        int dx = mc.newX != null ? mc.newX : pc.x;
                        int dy = mc.newY != null ? mc.newY : pc.y;
                        int dl = mc.newLevel != null ? mc.newLevel : pc.level;

                        if (isValidMove(entity, dx, dy, dl)) {
                            pc.x = dx;
                            pc.y = dy;
                            pc.level = dl;

                            if (entity.hasComponent(PlayerComponent.Name)) {
                                eventService.publishEvent(EventSubjects.EVENT_SUBJECT_PLAYER_MOVED, entity);
                            }
                        }
                    }
                }

                GlobalState.get().componentFactory.returnComponent(mc);
            }
        }
    }

    private boolean requiresProcessing(MovementComponent mc) {
        return (mc.newX != null) || (mc.newY != null) || (mc.newLevel != null);
    }

    private boolean isValidMove(Entity entity, int x, int y, int level) {
        boolean isValid = false;

        Tile t = world.getTileAt(x, y);
        if( t.isWalkable() ) {
            Entity blockingEntity = world.getBlockingEntityAt(x, y, level);

            if(blockingEntity == null) {
                isValid = true;
            }
            else {
                eventService.publishEvent(EventSubjects.EVENT_SUBJECT_ENTITIES_COLLIDED, new EntityCollisionContext(entity, blockingEntity));
            }
        }

        return isValid;
    }
}
