package com.skewpixel.rltut2019.screens;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.renderer.GlyphEntityRenderer;
import com.skewpixel.rltut2019.renderer.MapRenderer;
import com.skewpixel.rltut2019.services.InputService;
import com.skewpixel.rltut2019.ui.Terminal;

import java.util.List;

public class PlayScreen implements Screen {

    // playscreen has a map view and a stats panel
    private final MapRenderer mapRenderer;
    private final GlyphEntityRenderer glyphEntityRenderer;

    private final World world;
    private final InputService inputService;

    private final Entity player;
    private final PositionComponent playerPosn;

    public PlayScreen(World world, Entity player, List<Entity> entities, InputService inputService) {
        this.world = world;
        this.mapRenderer = new MapRenderer(world);
        this.glyphEntityRenderer = new GlyphEntityRenderer(entities);
        this.player = player;
        this.inputService = inputService;

        this.playerPosn = player.getComponentByName(PositionComponent.Name, PositionComponent.class);
    }

    @Override
    public void render(Terminal terminal) {
        // render the map
        mapRenderer.render(terminal);
        // then render the creatures
        glyphEntityRenderer.render(terminal);
    }

    public void update() {
        if(inputService.isKeyPressed("forward")) {
            playerPosn.y--;
            if(playerPosn.y < 0) playerPosn.y = 0;
        }
        if(inputService.isKeyPressed("backward")) {
            playerPosn.y++;
            if(playerPosn.y >= world.getHeight() - 1) playerPosn.y = world.getHeight() - 1;
        }
        if(inputService.isKeyPressed("left")) {
            playerPosn.x--;
            if(playerPosn.x < 0) playerPosn.x = 0;
        }
        if(inputService.isKeyPressed("right")) {
            playerPosn.x++;
            if(playerPosn.x >= world.getWidth() - 1) playerPosn.x = world.getWidth() - 1;
        }
    }
}
