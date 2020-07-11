package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;

import java.util.ArrayList;
import java.util.List;

import static com.skewpixel.rltut2019.util.RandUtils.randomInt;

public class WorldBuilder {

    private Tile[] tiles;
    private WorldDefinition worldDefinition;
    private CreatureGenerator mobGenerator;
    private Point spawnPoint;
    private int maxMobSpawnCount = 1;
    private int mobSpawnCount = 0;

    private WorldBuilder(WorldDefinition worldDefinition) {
        this.worldDefinition = worldDefinition;
        this.mobGenerator = new CreatureGenerator();

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
        List<Rect> rooms = new ArrayList<>();

        for(int count = 0; count < worldDefinition.maxRooms; count++) {
            int width = randomInt(worldDefinition.minRoomSize, worldDefinition.maxRoomSize);
            int height = randomInt(worldDefinition.minRoomSize, worldDefinition.maxRoomSize);

            // location
            int x = randomInt(0, worldDefinition.worldWidth - width - 1);
            int y = randomInt(0, worldDefinition.worldHeight - height - 1);

            Rect room = new Rect(x, y, width, height);

            if(rooms.isEmpty()) {
                // first room is spawn room
                // player x, y = roomCenter;
                Point roomCenter = buildRoom(room, entities, rooms, false);
                rooms.add(room);
                spawnPoint = roomCenter;
            }
            else {
                boolean intersects = false;
                for (Rect other : rooms) {
                    if (room.intersects(other)) {
                        intersects = true;
                        break;
                    }
                }

                if(!intersects){
                    buildRoom(room, entities, rooms, true);
                    rooms.add(room);
                }
            }
        }
    }

    private Point buildRoom(Rect room, List<Entity> entities, List<Rect> rooms, boolean spawnMobs) {
        createRoom(room);

        Point roomCenter = room.getCenter();

        if(spawnMobs) {
            spawnCreatures(room, entities);
        }

        // get the center of the previous room
        if(!rooms.isEmpty()) {
            Point prevCenter = rooms.get(rooms.size() - 1).getCenter();

            // random 0 or 1
            if (randomInt(0, 1) == 1) {
                // first move horizontally and then vertically
                makeHTunnel(prevCenter.x, roomCenter.x, prevCenter.y);
                makeVTunnel(roomCenter.x, prevCenter.y, roomCenter.y);
            } else {
                // first move vertically and then horizontally
                makeVTunnel(prevCenter.x, prevCenter.y, roomCenter.y);
                makeHTunnel(prevCenter.x, roomCenter.x, prevCenter.y);
            }
        }
        return roomCenter;
    }

    private void spawnCreatures(Rect room, List<Entity> entities) {
        if(mobSpawnCount < maxMobSpawnCount) {
            mobSpawnCount++;

            int x = randomInt(room.p1.x + 1, room.p2.x - 1);
            int y = randomInt(room.p1.y + 1, room.p2.y - 1);
            Entity orc = mobGenerator.spawnOrc();
            orc.addComponent(new PositionComponent(x, y, 0));
            entities.add(orc);
        }
//        int numSpawns = randomInt(0, worldDefinition.maxRoomSpawns);
//
//        int orcSpawnCount = 0;
//        int trollSpawnCount = 0;
//        for(int i = 0; i < numSpawns; i++) {
//            int x = randomInt(room.p1.x + 1, room.p2.x - 1);
//            int y = randomInt(room.p1.y + 1, room.p2.y - 1);
//
//            switch (randomInt(0, 2)) {
//                case 0:
//                    if(orcSpawnCount < worldDefinition.maxOrcs) {
//                        Entity orc = mobGenerator.spawnOrc();
//                        orc.addComponent(new PositionComponent(x, y, 0));
//                        entities.add(orc);
//                        orcSpawnCount++;
//                    }
//                    break;
//                case 1:
//                    if(trollSpawnCount < worldDefinition.maxTrolls) {
//                        Entity troll = mobGenerator.spawnTroll();
//                        troll.addComponent(new PositionComponent(x, y, 0));
//                        entities.add(troll);
//                        trollSpawnCount++;
//                    }
//                    break;
//                case 2:
//                    break;
//            }
//        }
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
