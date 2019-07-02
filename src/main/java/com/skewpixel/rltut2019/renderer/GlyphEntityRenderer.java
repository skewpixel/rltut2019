package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.creatures.Creature;
import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.GlyphComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.ui.Terminal;

import java.util.List;

public class GlyphEntityRenderer implements Renderable {

    // TODO: get this from game state when required
    private final List<Entity> entities;

    public GlyphEntityRenderer(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public void render(Terminal terminal) {
        for(Entity entity : entities) {

            GlyphComponent gc = entity.getComponentByName(GlyphComponent.Name, GlyphComponent.class);
            PositionComponent pc = entity.getComponentByName(PositionComponent.Name, PositionComponent.class);

            if((gc != null) && (pc != null)) {
                terminal.write(gc.glyph, pc.x, pc.y, gc.foregroundColor);
            }
        }
    }
}
