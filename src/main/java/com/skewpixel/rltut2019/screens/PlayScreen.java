package com.skewpixel.rltut2019.screens;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.renderer.GlyphEntityRenderer;
import com.skewpixel.rltut2019.renderer.MapRenderer;
import com.skewpixel.rltut2019.ui.Terminal;

import java.util.List;

public class PlayScreen implements Screen {

    // playscreen has a map view and a stats panel
    private final MapRenderer mapRenderer;
    private final GlyphEntityRenderer glyphEntityRenderer;


    public PlayScreen(World world, List<Entity> entities) {
        this.mapRenderer = new MapRenderer(world);
        this.glyphEntityRenderer = new GlyphEntityRenderer(entities);
    }

    @Override
    public void render(Terminal terminal) {
        // render the map
        mapRenderer.render(terminal);
        // then render the creatures
        glyphEntityRenderer.render(terminal);
    }

    public void update() {

    }
}
