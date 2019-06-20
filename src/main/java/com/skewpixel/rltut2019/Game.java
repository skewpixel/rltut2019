package com.skewpixel.rltut2019;

import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.renderer.GameRenderer;
import com.skewpixel.rltut2019.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private static final int ScreenCols = 80;
    private static final int ScreenRows = 21;
    private Terminal terminal;

    private static final int WorldWidth = 90;
    private static final int WorldHeight = 31;
    private final World world;

    private Thread gameThread;
    private volatile boolean running = false;

    private final GameWindow gameWindow;
    private RenderCanvas renderCanvas;

    private final GameRenderer renderer;

    public Game() {
        logger.info("Creating game window");
        terminal = new Terminal(new SpriteSheet(9, 16, Textures.font, Colors.Fuchsia), ScreenCols, ScreenRows);
        gameWindow = new GameWindow("rlTut", terminal.getWidth(), terminal.getHeight());
        renderCanvas = new RenderCanvas(terminal.getWidth(), terminal.getHeight());

        gameWindow.add(this.renderCanvas);
        gameWindow.pack();

        world = new World(WorldWidth, WorldHeight);
        renderer = new GameRenderer(terminal, world);
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
    }
}
