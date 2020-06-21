package com.skewpixel.rltut2019.map;

import java.util.ArrayList;
import java.util.List;

public class WorldDefinition {
    public int worldWidth;
    public int worldHeight;
    public int maxRoomSize;
    public int minRoomSize;
    public int maxRooms;

    public WorldDefinition(int worldWidth, int worldHeight, int maxRoomSize, int minRoomSize, int maxRooms) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.maxRoomSize = maxRoomSize;
        this.minRoomSize = minRoomSize;
        this.maxRooms = maxRooms;
    }

    List<EntityGenerator> getEntityGenerators() {
        List<EntityGenerator> generators = new ArrayList<>();
        generators.add(new CreatureGenerator());

        return generators;
    }
}
