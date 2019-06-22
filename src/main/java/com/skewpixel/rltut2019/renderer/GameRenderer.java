package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.map.Tile;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.ui.Terminal;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameRenderer {
    private final Terminal terminal;
    private final World world;

    private int player_x;
    private int player_y;

    public GameRenderer(Terminal terminal, World world) {
        this.terminal = terminal;
        this.world = world;

        player_x = terminal.getCols()/2;
        player_y = terminal.getRows()/2;
    }

    public void render() {

        terminal.clear();

        for(int x = 0; x < world.getWidth(); x++) {
            for(int y = 0; y < world.getHeight(); y++) {
                Tile t = world.getTileAt(x, y);

                terminal.write(t.getGlyph(), x, y, t.getColour());
            }
        }

        terminal.write('@', player_x, player_y, Color.red);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: {
                player_x --;
                if(player_x < 0) player_x = 0;
            }
            break;

            case KeyEvent.VK_D: {
                player_x ++;
                if(player_x >= terminal.getCols()) player_x = terminal.getCols() - 1;
            }
            break;

            case KeyEvent.VK_W: {
                player_y --;
                if(player_y < 0) player_y = 0;
            }
            break;
            case KeyEvent.VK_S:{
                player_y ++;
                if(player_y >= terminal.getRows()) player_y = terminal.getRows() - 1;
            }
            break;
        }
    }
}
