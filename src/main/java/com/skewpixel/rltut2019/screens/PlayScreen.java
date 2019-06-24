package com.skewpixel.rltut2019.screens;

import com.skewpixel.rltut2019.creatures.Creature;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.renderer.CreatureRenderer;
import com.skewpixel.rltut2019.renderer.MapRenderer;
import com.skewpixel.rltut2019.ui.Terminal;

import java.awt.event.KeyEvent;
import java.util.List;

public class PlayScreen implements Screen {

    // playscreen has a map view and a stats panel
    private final MapRenderer mapRenderer;
    private final CreatureRenderer creatureRenderer;
    private final Creature player;
    private final World world;

    public PlayScreen(World world, Creature player, List<Creature> creatures) {
        this.world = world;
        this.mapRenderer = new MapRenderer(world);
        this.creatureRenderer = new CreatureRenderer(creatures);
        this.player = player;
    }

    @Override
    public void render(Terminal terminal) {
        // render the map
        mapRenderer.render(terminal);
        // then render the creatures
        creatureRenderer.render(terminal);
    }

    public void onKeyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: {
                int x = player.getX() - 1;
                if(x < 0) x = 0;
                player.setX(x);
            }
            break;

            case KeyEvent.VK_D: {
                int x = player.getX() + 1;
                if(x >= world.getWidth() - 1) x = world.getWidth() - 1;
                player.setX(x);
            }
            break;

            case KeyEvent.VK_W: {
                int y = player.getY() - 1;
                if(y < 0) y = 0;
                player.setY(y);
            }
            break;
            case KeyEvent.VK_S:{
                int y = player.getY() + 1;
                if(y >= world.getHeight() - 1) y = world.getHeight() - 1;
                player.setY(y);
            }
            break;
        }
    }


}
