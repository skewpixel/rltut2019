package com.skewpixel.rltut2019.creatures.ai;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.*;
import com.skewpixel.rltut2019.map.pathfinding.Path;
import com.skewpixel.rltut2019.map.pathfinding.PathFinder;
import com.skewpixel.rltut2019.map.Point;
import com.skewpixel.rltut2019.map.pathfinding.PathFindingAlgorithm;
import com.skewpixel.rltut2019.state.GlobalState;

import java.awt.*;

public class BasicMobAi implements MobAi {
    public static final String Name = "BasicMobAi";

    private final Entity player;

    public BasicMobAi(Entity player) {
        this.player = player;
    }

    @Override
    public void takeTurn(Entity entity) {
        PositionComponent pc = entity.getComponentByName(PositionComponent.Name, PositionComponent.class);

        if(pc != null) {

            TargetEntityComponent tc = entity.getComponentByName(TargetEntityComponent.Name, TargetEntityComponent.class);

            if(tc == null) {
                tc = GlobalState.get().componentFactory.getComponentByName(TargetEntityComponent.Name, TargetEntityComponent.class);
                tc.target = player;
                entity.addComponent(tc);
            }
           // if((tc != null) && (tc.target != null)) {
                PositionComponent targetPos = tc.target.getComponentByName(PositionComponent.Name, PositionComponent.class);

                if(targetPos != null) {
                    if (GlobalState.get().fovCache.isInFov(pc.x, pc.y)) {
                        if (distanceTo(pc.x, pc.y, targetPos.x, targetPos.y) >= 2.0) {
                            entity.addComponent(moveTowards(pc.x, pc.y, targetPos.x, targetPos.y));
                        }
                        else {
                            HealthComponent hc = tc.target.getComponentByName(HealthComponent.Name, HealthComponent.class);

                            if(hc != null) {
                                if(hc.health > 0) {
                                    NameComponent nc = entity.getComponentByName(NameComponent.Name, NameComponent.class);

                                    if(nc != null) {
                                        System.out.println(String.format("The %s insults you! Your ego is damaged!", nc.name));
                                    }
                                }
                            }
                        }
                    }
                }
           // }
        }
    }

    private MovementComponent moveTowards(int currentX, int currentY, int targetX, int targetY) {
        int dx = targetX - currentX;
        int dy = targetY - currentY;

        double dist = Math.sqrt(dx^2 + dy^2);

        dx = (int)(Math.round(dx/ dist));
        dy = (int)(Math.round(dy / dist));


        MovementComponent mc = GlobalState.get().componentFactory.getComponentByName(MovementComponent.Name, MovementComponent.class);
        mc.newX = currentX + dx;
        mc.newY = currentY + dy;

        return mc;
    }

    private double distanceTo(int x, int y, int targetX, int targetY) {
        int dx = targetX - x;
        int dy = targetY - y;

        return Math.sqrt(dx^2 + dy^2);
    }

    @Override
    public String getName() {
        return Name;
    }
}
