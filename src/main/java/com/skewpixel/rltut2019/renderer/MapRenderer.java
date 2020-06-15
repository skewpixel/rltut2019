package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.map.FovCache;
import com.skewpixel.rltut2019.map.Tile;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.ui.Terminal;

public class MapRenderer implements Renderer {
    public static final String NAME = "renderer.map_renderer";

    private final World world;
    private final FovCache fovCache;

    public MapRenderer(World world, FovCache fovCache) {
        this.world = world;
        this.fovCache = fovCache;
    }

    @Override
    public void render(Terminal terminal) {
        for(int x = 0; x < world.getWidth(); x++) {
            for(int y = 0; y < world.getHeight(); y++) {
                // if we haven't explored a tile yet, then use an unknown default tile for rendering
                Tile t = fovCache.isExplored(x, y) ? world.getTileAt(x, y) : Tile.Unknown;

                terminal.write(t.getGlyph(), x, y,
                        t.getColour(fovCache.isInFov(x, y) ? Tile.TileColorType.Primary :
                                Tile.TileColorType.Secondary));
            }
        }
    }
}
