package com.skewpixel.rltut2019.map;

public class WorldDefinition {
    public int worldWidth;
    public int worldHeight;
    public int maxRoomSize;
    public int minRoomSize;
    public int maxRooms;
    public int maxRoomSpawns;
    public int maxOrcs;
    public int maxTrolls;

    public WorldDefinition(int worldWidth, int worldHeight,
                           int maxRoomSize, int minRoomSize, int maxRooms,
                           int maxRoomSpawns, int maxOrcs, int maxTrolls) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.maxRoomSize = maxRoomSize;
        this.minRoomSize = minRoomSize;
        this.maxRooms = maxRooms;
        this.maxRoomSpawns = maxRoomSpawns;
        this.maxOrcs = maxOrcs;
        this.maxTrolls = maxTrolls;
    }

}
