package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;

import java.util.List;

public class World {
    private final int width;
    private final int height;
    private final Tile[] tiles;
    private final List<Entity> entities;

    public World(int width, int height, List<Entity> entities) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width * height];
        this.entities = entities;

        // fill the world with floor
        for(int i = 0; i < width*height; i++) {
            this.tiles[i] = Tile.Floor;
        }

        for(int y = 5; y < 10; y++) {
            tiles[10 + y * width] = Tile.Wall;
            tiles[20 + y * width] = Tile.Wall;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile getTileAt(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.Bounds;
        }
        else {
            return tiles[x + y * width];
        }
    }

    public Entity getEntityAt(int x, int y, int level) {
        for(Entity entity : entities) {
            PositionComponent pc = entity.getComponentByName(PositionComponent.Name, PositionComponent.class);

            if((pc != null) && (pc.x == x) && (pc.y == y) && (pc.level == level)){
                return entity;
            }
        }
        return null;
    }

    public boolean isValidLocation(int x, int y, int level) {
        return ((x >= 0) && (x < width) && (y >=0) && (y < height));
    }

    public boolean isEmptyLocation(int x, int y, int level) {
        if(!isValidLocation(x, y, level)) return false;

        Tile tile = getTileAt(x, y);
        Entity entity = getEntityAt(x, y, level);

        if(entity != null) return false;

        return tile.isWalkable();
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
