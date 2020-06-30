package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.actions.Action;
import com.skewpixel.rltut2019.services.EventListener;
import com.skewpixel.rltut2019.services.EventService;
import com.skewpixel.rltut2019.services.EventSubjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// map collision events to actions that can be performed
public class CollisionActionSystem implements GameSystem, EventListener {

    private final List<Action> actions = new ArrayList<>();

    public CollisionActionSystem(EventService eventService) {
        eventService.addEventListener(EventSubjects.EVENT_SUBJECT_ENTITIES_COLLIDED, this);
    }

    public void AddAction(Action action) {
        actions.add(action);
    }

    @Override
    public void tick(long time) {
        // NoOp
    }

    @Override
    public void handleEvent(String subject, Object context) {
        if(subject == EventSubjects.EVENT_SUBJECT_ENTITIES_COLLIDED) {
            // transform the collision event into an action
            if(context instanceof EntityCollisionContext) {
                EntityCollisionContext ecc = (EntityCollisionContext)context;

                Optional<Action> action = actions.stream().filter(a -> a.isValidAction(ecc.collider, ecc.collidee)).findFirst();

                if(action.isPresent()) {
                    action.get().execute(ecc.collider, ecc.collidee);
                }
            }
        }
    }
}
