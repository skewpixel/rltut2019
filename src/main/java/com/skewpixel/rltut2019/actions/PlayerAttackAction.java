package com.skewpixel.rltut2019.actions;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MonsterComponent;
import com.skewpixel.rltut2019.ecs.components.NameComponent;
import com.skewpixel.rltut2019.ecs.components.PlayerComponent;
import com.skewpixel.rltut2019.services.EventService;
import com.skewpixel.rltut2019.services.EventSubjects;
import com.skewpixel.rltut2019.state.GameState;
import com.skewpixel.rltut2019.state.GlobalState;

public class PlayerAttackAction implements Action {
    private final EventService eventService;

    public PlayerAttackAction(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public boolean isValidAction(Entity performer, Object performee) {
        boolean isValid = false;

        if(GlobalState.get().gameState == GameState.PlayerTurn) {
            if (performer.hasComponent(PlayerComponent.Name)) {
                // then is the victim a monster?
                isValid = ((performee instanceof Entity) && (((Entity) performee).hasComponent(MonsterComponent.Name)));
            }
        }

        return isValid;
    }

    @Override
    public boolean execute(Entity performer, Object performee) {
        Entity e = (Entity)performee;

        NameComponent nc = e.getComponentByName(NameComponent.Name, NameComponent.class);
        System.out.println(String.format("You kick the %s in the shins, much to its annoyance!", nc.name));

        eventService.publishEvent(EventSubjects.EVENT_SUBJECT_PLAYER_ATTACKED, e);

        return true;
    }
}
