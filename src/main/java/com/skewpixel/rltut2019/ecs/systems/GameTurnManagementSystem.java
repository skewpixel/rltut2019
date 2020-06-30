package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.services.EventListener;
import com.skewpixel.rltut2019.services.EventService;
import com.skewpixel.rltut2019.services.EventSubjects;
import com.skewpixel.rltut2019.state.GameState;
import com.skewpixel.rltut2019.state.GlobalState;

public class GameTurnManagementSystem implements GameSystem, EventListener {

    public GameTurnManagementSystem(EventService eventService) {
        // TODO: I don't like this
        eventService.addEventListener(EventSubjects.EVENT_SUBJECT_PLAYER_MOVED, this);
        eventService.addEventListener(EventSubjects.EVENT_SUBJECT_MOB_AI_COMPLETED, this);
        eventService.addEventListener(EventSubjects.EVENT_SUBJECT_PLAYER_ATTACKED, this);
    }

    @Override
    public void tick(long time) {
        // NoOp
    }

    @Override
    public void handleEvent(String subject, Object context) {
        // player moved, change game state
        switch (subject) {
            case EventSubjects.EVENT_SUBJECT_PLAYER_MOVED:
            case EventSubjects.EVENT_SUBJECT_PLAYER_ATTACKED:
                GlobalState.get().gameState = GameState.EnemyTurn;
                break;
            case EventSubjects.EVENT_SUBJECT_MOB_AI_COMPLETED:
                GlobalState.get().gameState = GameState.PlayerTurn;
                break;
        }
    }
}
