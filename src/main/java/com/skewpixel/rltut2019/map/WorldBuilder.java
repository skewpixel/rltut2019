package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ecs.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.skewpixel.rltut2019.util.RandUtils.randomInt;

public class WorldBuilder {

    private Tile[] tiles;
    private WorldDefinition worldDefinition;
    private Point spawnPoint;

    private WorldBuilder(WorldDefinition worldDefinition) {
        this.worldDefinition = worldDefinition;
        this.tiles = new Tile[worldDefinition.worldWidth * worldDefinition.worldHeight];

        // fill the world with floor
        for(int i = 0; i < worldDefinition.worldWidth * worldDefinition.worldHeight; i++) {
            this.tiles[i] = Tile.Wall;
        }
    }

    public Point getSpawnPoint() {
        return spawnPoint;
    }

    public static World buildWorld(WorldDefinition defn, List<Entity> entities) {

        WorldBuilder builder = new WorldBuilder(defn);

        builder.build(entities);

        return new World(defn.worldWidth, defn.worldHeight, builder.tiles, entities, builder.getSpawnPoint());
    }

    private void build(List<Entity> entities) {

        buildRooms(entities);
    }

    private void buildRooms(List<Entity> entities) {
        int numRooms = 0;
        List<Rect> rooms = new ArrayList<>();

        for(int count = 0; count < worldDefinition.maxRooms; count++) {
            int width = randomInt(worldDefinition.minRoomSize, worldDefinition.maxRoomSize);
            int height = randomInt(worldDefinition.minRoomSize, worldDefinition.maxRoomSize);

            // location
            int x = randomInt(0, worldDefinition.worldWidth - width - 1);
            int y = randomInt(0, worldDefinition.worldHeight - height - 1);

            Rect room = new Rect(x, y, width, height);

            for(Rect other : rooms) {
                if(room.intersects(other)) {
                    break;
                }
                else {
                    createRoom(room);

                    Point roomCenter = room.getCenter();

                    if(numRooms == 1) {
                        // player x, y = roomCenter;
                        spawnPoint = roomCenter;
                    }
                    else {
                        // get the center of the previous room
                        Point prevCenter = rooms.get(rooms.size() - 1).getCenter();

                        // random 0 or 1
                        if(randomInt(0, 1) == 1) {
                            // first move horizontally and then vertically
                            makeHTunnel(prevCenter.x, roomCenter.x, prevCenter.y);
                            makeVTunnel(roomCenter.x, prevCenter.y, roomCenter.y);
                        }
                        else {
                            // first move vertically and then horizontally
                            makeVTunnel(prevCenter.x, prevCenter.y, roomCenter.y);
                            makeHTunnel(prevCenter.x, roomCenter.x, prevCenter.y);
                        }
                    }
                }
            }

            spawnEntities(room, entities);

            rooms.add(room);
            numRooms++;
        }
    }

    private void spawnEntities(Rect room, List<Entity> entities) {
        EntityGenerator generator = worldDefinition.getEntityGenerators().get(0);

        int numEntities = randomInt(0, generator.getMaxEntities());

        for(int i = 0; i < numEntities; i++) {
            int x = randomInt(room.p1.x + 1, room.p2.x - 1);
            int y = randomInt(room.p1.y + 1, room.p2.y - 1);

            Entity e = generator.generateEntityAt(x, y);

            if(e != null) {
                entities.add(e);
            }
        }
    }

    private void createRoom(Rect room) {
        // go through the tiles in the rectangle and set them to floors
        for(int x = room.p1.x + 1; x < room.p2.x; x++) {
            for(int y = room.p1.y + 1; y < room.p2.y; y++) {
                this.tiles[x + y * worldDefinition.worldWidth] = Tile.Floor;
            }
        }
    }

    private void makeVTunnel(int x, int y1, int y2) {
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        for(int y = minY; y <= maxY; y ++) {
            this.tiles[x + y * worldDefinition.worldWidth] = Tile.Floor;
        }
    }

    private void makeHTunnel(int x1, int x2, int y) {

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);

        for(int x = minX; x <= maxX; x ++) {
            this.tiles[x + y * worldDefinition.worldWidth] = Tile.Floor;
        }
    }
}
