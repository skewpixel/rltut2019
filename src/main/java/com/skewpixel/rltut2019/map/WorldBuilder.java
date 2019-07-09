package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ecs.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldBuilder {

    private Tile[] tiles;
    private WorldDefinition defn;
    private Random random;

    private WorldBuilder(WorldDefinition defn) {
        this.defn = defn;
        this.random = new Random(System.currentTimeMillis());
        this.tiles = new Tile[defn.worldWidth * defn.worldHeight];

        // fill the world with floor
        for(int i = 0; i < defn.worldWidth * defn.worldHeight; i++) {
            this.tiles[i] = Tile.Wall;
        }
    }

    public static World buildWorld(WorldDefinition defn, List<Entity> entities) {

        WorldBuilder builder = new WorldBuilder(defn);

        builder.build();

        return new World(defn.worldWidth, defn.worldHeight, builder.tiles, entities);
    }

    private void build() {

        buildRooms();
    }

    private int randomInt(int min, int max) {
        if(min >= max) throw new IllegalArgumentException("Min must be less than max");

        return random.nextInt((max - min) + 1) + min;
    }

    private void buildRooms() {
        int numRooms = 0;
        List<Rect> rooms = new ArrayList<>();

        for(int count = 0; count < defn.maxRooms; count++) {
            int width = randomInt(defn.minRoomSize, defn.maxRoomSize);
            int height = randomInt(defn.minRoomSize, defn.maxRoomSize);

            // location
            int x = randomInt(0, defn.worldWidth - width - 1);
            int y = randomInt(0, defn.worldHeight - height - 1);

            Rect room = new Rect(x, y, width, height);

            for(Rect other : rooms) {
                if(room.intersects(other)) {
                    break;
                }
                else {
                    createRoom(room);

                    Point roomCenter = room.getCenter();

                    if(numRooms == 0) {
                        // player x, y = roomCenter;
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

            rooms.add(room);
            numRooms++;
        }
    }

    private void createRoom(Rect room) {
        // go through the tiles in the rectangle and set them to floors
        for(int x = room.p1.x + 1; x < room.p2.x; x++) {
            for(int y = room.p1.y + 1; y < room.p2.y; y++) {
                this.tiles[x + y * defn.worldWidth] = Tile.Floor;
            }
        }
    }

    private void makeVTunnel(int x, int y1, int y2) {
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        for(int y = minY; y <= maxY; y ++) {
            this.tiles[x + y * defn.worldWidth] = Tile.Floor;
        }
    }

    private void makeHTunnel(int x1, int x2, int y) {

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);

        for(int x = minX; x <= maxX; x ++) {
            this.tiles[x + y * defn.worldWidth] = Tile.Floor;
        }
    }
}
