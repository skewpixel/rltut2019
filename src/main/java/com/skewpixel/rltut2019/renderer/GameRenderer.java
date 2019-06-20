package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.map.Tile;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.ui.Terminal;

import java.awt.*;

public class GameRenderer {
    private final Terminal terminal;
    private final World world;

    public GameRenderer(Terminal terminal, World world) {
        this.terminal = terminal;
        this.world = world;
    }

    public void render() {

        for(int x = 0; x < world.getWidth(); x++) {
            for(int y = 0; y < world.getHeight(); y++) {
                Tile t = world.getTileAt(x, y);

                terminal.write(t.getGlyph(), x, y, t.getColour());
            }
        }

        terminal.write('@', terminal.getCols()/2, terminal.getRows()/2, Color.red);
    }
}
