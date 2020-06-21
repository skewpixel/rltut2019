package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.GlyphComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.map.FovCache;
import com.skewpixel.rltut2019.ui.Terminal;

import java.util.List;

public class GlyphEntityRenderer implements Renderer {
    public static final String NAME = "renderer.glyph_entity_renderer";

    // TODO: get this from game state when required
    private final List<Entity> entities;
    private final FovCache fovCache;

    public GlyphEntityRenderer(List<Entity> entities, FovCache fovCache) {
        this.entities = entities;
        this.fovCache = fovCache;
    }

    @Override
    public void render(Terminal terminal) {
        for(Entity entity : entities) {

            GlyphComponent gc = entity.getComponentByName(GlyphComponent.Name, GlyphComponent.class);
            PositionComponent pc = entity.getComponentByName(PositionComponent.Name, PositionComponent.class);

            if((gc != null) && (pc != null)) {
                if(fovCache.isInFov(pc.x, pc.y)) {
                    terminal.write(gc.glyph, pc.x, pc.y, gc.foregroundColor);
                }
            }
        }
    }
}
