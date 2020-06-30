package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.FovComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.map.FovCache;
import com.skewpixel.rltut2019.map.FovCalculator;
import com.skewpixel.rltut2019.map.Point;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.services.EventListener;
import com.skewpixel.rltut2019.services.EventService;
import com.skewpixel.rltut2019.services.EventSubjects;

public class FieldOfViewSystem implements GameSystem, EventListener {
    private final EventService eventService;
    private final FovCalculator fovCalculator;

    public FieldOfViewSystem(World world, FovCache fovCache, int fovRadius, EventService eventService) {
        this.fovCalculator = new FovCalculator(world, fovCache);

        this.eventService = eventService;
        this.eventService.addEventListener(EventSubjects.EVENT_SUBJECT_PLAYER_MOVED, this);

        Point sp = world.getSpawnPoint();
        this.fovCalculator.calculateFov(sp.x, sp.y, 0, fovRadius);
    }


    @Override
    public void tick(long time) {
        // NoOp
    }

    @Override
    public void handleEvent(String subject, Object context) {
        // player moved, recalculate the FOV
        if(subject.equals(EventSubjects.EVENT_SUBJECT_PLAYER_MOVED)) {
            // player entity is sent in the event context
            Entity player = (Entity) context;

            PositionComponent pc = player.getComponentByName(PositionComponent.Name, PositionComponent.class);
            FovComponent fov = player.getComponentByName(FovComponent.Name, FovComponent.class);
            // update the FOV cache with the new FOV
            fovCalculator.calculateFov(pc.x, pc.y, pc.level, fov.viewRadius);
        }
    }
}
