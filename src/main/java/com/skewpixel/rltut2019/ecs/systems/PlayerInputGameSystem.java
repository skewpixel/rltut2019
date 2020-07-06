package com.skewpixel.rltut2019.ecs.systems;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.MovementComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.services.InputService;
import com.skewpixel.rltut2019.state.GameState;
import com.skewpixel.rltut2019.state.GlobalState;

public class PlayerInputGameSystem implements GameSystem {
    private final InputService inputService;
   // private final MovementComponent moveComponent;
    private final PositionComponent playerPosn;
    private final Entity player;

    public PlayerInputGameSystem(InputService inputService, Entity player) {
        this.inputService = inputService;
        this.player = player;
        //this.moveComponent = player.getComponentByName(MovementComponent.Name, MovementComponent.class);
        this.playerPosn = player.getComponentByName(PositionComponent.Name, PositionComponent.class);
    }

    @Override
    public void tick(long time) {
        if(GlobalState.get().gameState == GameState.PlayerTurn) {
            if (inputService.isKeyPressed("forward", true)) {
                player.addComponent(movePlayer(null, playerPosn.y - 1, null));
            }
            else if (inputService.isKeyPressed("backward", true)) {
                player.addComponent(movePlayer(null, playerPosn.y + 1, null));
            }
            else if (inputService.isKeyPressed("left", true)) {
                player.addComponent(movePlayer(playerPosn.x - 1, null, null));
            }
            else if (inputService.isKeyPressed("right", true)) {
                player.addComponent(movePlayer(playerPosn.x + 1, null, null));
            }
            else if (inputService.isKeyPressed("fwdleft", true)) {
                player.addComponent(movePlayer(playerPosn.x - 1, playerPosn.y - 1, null));
            }
            else if (inputService.isKeyPressed("fwdright", true)) {
                player.addComponent(movePlayer(playerPosn.x + 1, playerPosn.y - 1, null));
            }
            else if (inputService.isKeyPressed("backleft", true)) {
                player.addComponent(movePlayer(playerPosn.x - 1, playerPosn.y + 1, null));
            }
            else if (inputService.isKeyPressed("backright", true)) {
                player.addComponent(movePlayer(playerPosn.x + 1, playerPosn.y + 1, null));
            }
        }
    }

    private MovementComponent movePlayer(Integer x, Integer y, Integer level) {
        MovementComponent mc = GlobalState.get().componentFactory.getComponentByName(MovementComponent.Name, MovementComponent.class);
        mc.newX = x;
        mc.newY = y;
        mc.newLevel = level;
        return mc;
    }
}
