package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.map.Tile;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.ui.Terminal;

public class MapRenderer implements Renderable {

    private final World world;

    public MapRenderer(World world) {
        this.world = world;
    }

    @Override
    public void render(Terminal terminal) {
        for(int x = 0; x < world.getWidth(); x++) {
            for(int y = 0; y < world.getHeight(); y++) {
                Tile t = world.getTileAt(x, y);

                terminal.write(t.getGlyph(), x, y, t.getColour());
            }
        }
    }
}
