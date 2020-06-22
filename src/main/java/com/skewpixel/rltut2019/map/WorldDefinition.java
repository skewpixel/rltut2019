package com.skewpixel.rltut2019.map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class WorldDefinition {
    public int worldWidth;
    public int worldHeight;
    public int maxRoomSize;
    public int minRoomSize;
    public int maxRooms;
    public MobDefinition mobs;

    public WorldDefinition(int worldWidth, int worldHeight, int maxRoomSize, int minRoomSize, int maxRooms) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.maxRoomSize = maxRoomSize;
        this.minRoomSize = minRoomSize;
        this.maxRooms = maxRooms;
    }

    public static WorldDefinition fromJson(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        return (WorldDefinition) gson.fromJson(new JsonReader(reader), WorldDefinition.class);
    }
    public EntityGenerator getCreatureGenerator() {
        return new CreatureGenerator();
    }
}
