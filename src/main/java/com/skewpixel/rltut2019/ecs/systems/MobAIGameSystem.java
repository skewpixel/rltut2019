package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MobAIComponent;
import com.skewpixel.rltut2019.ecs.components.NameComponent;
import com.skewpixel.rltut2019.services.EventService;
import com.skewpixel.rltut2019.services.EventSubjects;
import com.skewpixel.rltut2019.state.GameState;
import com.skewpixel.rltut2019.state.GlobalState;

import java.util.List;

public class MobAIGameSystem implements GameSystem {

    private final EventService eventService;
    private final List<Entity> entities;

    public MobAIGameSystem(EventService eventService, List<Entity> entities) {
        this.eventService = eventService;
        this.entities = entities;
    }

    @Override
    public void tick(long time) {

        if(GlobalState.get().gameState == GameState.EnemyTurn) {
            // process enemy AI
            for(Entity entity : entities) {
                if(entity.hasComponent(MobAIComponent.Name)) {
                    NameComponent nc = entity.getComponentByName(NameComponent.Name, NameComponent.class);

                    System.out.println("The " + nc.name + " ponders the meaning of its existence.");
                }
            }

            this.eventService.publishEvent(EventSubjects.EVENT_SUBJECT_MOB_AI_COMPLETED, null);
        }
    }
}
