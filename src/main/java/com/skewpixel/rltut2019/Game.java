package com.skewpixel.rltut2019;

import com.skewpixel.rltut2019.creatures.Creature;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.renderer.GameRenderer;
import com.skewpixel.rltut2019.screens.PlayScreen;
import com.skewpixel.rltut2019.screens.Screen;
import com.skewpixel.rltut2019.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable, KeyListener {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private static final int ScreenCols = 80;
    private static final int ScreenRows = 21;
    private Terminal terminal;

    private static final int WorldWidth = 90;
    private static final int WorldHeight = 31;
    private final World world;
    private final List<Creature> creatures = new ArrayList<>();
    private final Creature player;

    private Thread gameThread;
    private volatile boolean running = false;

    private GameWindow gameWindow;
    private RenderCanvas renderCanvas;

    private final GameRenderer renderer;

    private Screen currentScreen;

    public Game() {
        logger.info("Creating game window");
        terminal = new Terminal(TerminalFont.DefaultFont, ScreenCols, ScreenRows);

        renderCanvas = new RenderCanvas(terminal.getWidth(), terminal.getHeight());
        createGameWindow(false);

        world = new World(WorldWidth, WorldHeight);

        renderer = new GameRenderer(terminal);

        player = new Creature('@', Color.red, world);
        player.setX(terminal.getCols()/2);
        player.setY(terminal.getRows()/2);
        creatures.add(player);

        currentScreen = new PlayScreen(world, player, creatures);
        renderer.addRenderable(currentScreen);
    }

    private void createGameWindow(boolean fullscreen) {
        if(gameWindow != null) {
            gameWindow.setVisible(false);
            gameWindow.dispose();
            gameWindow = null;
        }

        gameWindow = new GameWindow("rlTut", terminal.getWidth(), terminal.getHeight());
        gameWindow.setFullscreen(fullscreen);

        gameWindow.addKeyListener(this);
        gameWindow.add(this.renderCanvas);
        gameWindow.pack();
    }

    public synchronized void start() {
        if(running) return;
        running = true;

        logger.info("Setting game window visible");
        gameWindow.setVisible(true);

        logger.info("Starting game thread");
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {
        if(!running) return;

        try {
            logger.info("Attempting to stop game thread");
            running = false;

            gameThread.join();
        } catch (InterruptedException e) {
            logger.error("Error trying to stop game thread",e);
        }
    }

    @Override
    public void run() {
        while(running) {
            renderer.render();
            renderCanvas.render(terminal.getRenderBuffer());
        }

        logger.info("Game thread stopped");
        gameWindow.close();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            createGameWindow(!gameWindow.isFullscreen());
            gameWindow.setVisible(true);
        }
        else {
            currentScreen.onKeyPressed(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
