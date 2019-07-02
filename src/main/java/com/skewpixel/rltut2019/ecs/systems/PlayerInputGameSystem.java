package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MovementComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.services.InputService;

public class PlayerInputGameSystem implements GameSystem {
    private final InputService inputService;
    private final MovementComponent moveComponent;
    private final PositionComponent playerPosn;

    public PlayerInputGameSystem(InputService inputService, Entity player) {
        this.inputService = inputService;
        this.moveComponent = player.getComponentByName(MovementComponent.Name, MovementComponent.class);
        this.playerPosn = player.getComponentByName(PositionComponent.Name, PositionComponent.class);
    }

    @Override
    public void tick() {
        if(inputService.isKeyPressed("forward")) {
            moveComponent.newY = playerPosn.y-1;
        }
        if(inputService.isKeyPressed("backward")) {
            moveComponent.newY = playerPosn.y+1;
        }
        if(inputService.isKeyPressed("left")) {
            moveComponent.newX = playerPosn.x-1;
        }
        if(inputService.isKeyPressed("right")) {
            moveComponent.newX = playerPosn.x+1;
        }
    }
}
