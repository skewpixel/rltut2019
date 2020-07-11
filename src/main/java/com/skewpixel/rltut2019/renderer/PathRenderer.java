package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.PathComponent;
import com.skewpixel.rltut2019.map.Point;
import com.skewpixel.rltut2019.ui.Terminal;

import java.awt.*;
import java.util.List;

public class PathRenderer implements Renderer {
    public static final String NAME = "renderer.path_renderer";

    private final List<Entity> entities;

    public PathRenderer(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public void render(Terminal terminal) {
        for(Entity entity : entities) {

            PathComponent pc = entity.getComponentByName(PathComponent.Name, PathComponent.class);

            if(pc != null) {
                for(Point pt : pc.path.getPoints()) {
                    terminal.write("*", pt.x, pt.y, Color.magenta);
                }
            }
        }
    }
}
