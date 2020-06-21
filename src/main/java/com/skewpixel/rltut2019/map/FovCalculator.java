package com.skewpixel.rltut2019.map;

public class FovCalculator {
    private final World world;
    private final FovCache cache;

    public FovCalculator(World world, FovCache cache) {
        this.world = world;
        this.cache = cache;

    }

    public void calculateFov(int playerX, int playerY, int playerZ, int maxRadius) {
        // first clear our visible cache
        cache.clearVisibleCache();

        // then recalculate it
        for(int x = -maxRadius; x < maxRadius; x++) {
            for(int y = -maxRadius; y < maxRadius; y++) {

                if(x*x + y*y > maxRadius * maxRadius) continue;

                if((playerX + x < 0) || (playerX + x >= world.getWidth()) ||
                        (playerY + y < 0) || (playerY + y >= world.getHeight())) continue;

                Line line = Line.createLine(playerX, playerY, playerX + x, playerY + y);

                for(Point pt : line.getPoints()) {
                    Tile tile = world.getTileAt(pt.x, pt.y);
                    cache.updateFovData(pt.x, pt.y, true);

                    if(tile.getBlocksSight()) break;
                }
            }
        }

    }
}
