package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.PathComponent;
import com.skewpixel.rltut2019.map.Point;
import com.skewpixel.rltut2019.map.pathfinding.Path;
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
                for(int i = 0; i < pc.paths.size(); i++) {
                    Path path = pc.paths.get(i);
                    Color color = pc.colors.get(i);

                    for (Point pt : path.getPoints()) {
                        terminal.write("*", pt.x, pt.y, color);
                    }
                }
            }
        }
    }
}
