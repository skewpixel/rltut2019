package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.creatures.ai.MobAi;
import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MobAIComponent;
import com.skewpixel.rltut2019.ecs.components.NameComponent;
import com.skewpixel.rltut2019.services.EventService;
import com.skewpixel.rltut2019.services.EventSubjects;
import com.skewpixel.rltut2019.state.GameState;
import com.skewpixel.rltut2019.state.GlobalState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobAIGameSystem implements GameSystem {

    private final EventService eventService;
    private final List<Entity> entities;
    private final Map<String, MobAi> aiMap = new HashMap<>();

    public MobAIGameSystem(EventService eventService, List<Entity> entities) {
        this.eventService = eventService;
        this.entities = entities;
    }

    @Override
    public void tick(long time) {

        if(GlobalState.get().gameState == GameState.EnemyTurn) {
            // process enemy AI
            for(Entity entity : entities) {
                MobAIComponent aiComp = entity.getComponentByName(MobAIComponent.Name, MobAIComponent.class);
                if(aiComp != null) {
                    MobAi ai = getMobAi(aiComp.aiName);

                    if(ai != null) {
                        ai.takeTurn(entity);
                    }
                }
            }

            this.eventService.publishEvent(EventSubjects.EVENT_SUBJECT_MOB_AI_COMPLETED, null);
        }
    }

    public void addAi(MobAi mobAi) {
        aiMap.put(mobAi.getName(), mobAi);
    }
    private MobAi getMobAi(String aiName) {
        MobAi ai = null;

        if(aiMap.containsKey(aiName)) {
            ai = aiMap.get(aiName);
        }

        return ai;
    }
}
