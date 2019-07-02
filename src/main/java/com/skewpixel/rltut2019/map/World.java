package com.skewpixel.rltut2019.map;

public class World {
    private final int width;
    private final int height;
    private final Tile[] tiles;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width * height];

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
}
