package com.skewpixel.rltut2019;

import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.renderer.GameRenderer;
import com.skewpixel.rltut2019.ui.ConsolePanel;
import com.skewpixel.rltut2019.ui.GameWindow;
import com.skewpixel.rltut2019.ui.RenderBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private static final int ScreenWidth = 80*9;
    private static final int ScreenHeight = 21*16;

    private final GameWindow gameWindow;
    private Thread gameThread;
    private volatile boolean running = false;
    private ConsolePanel consolePanel;

    RenderBuffer renderBuffer;
    private final GameRenderer renderer;

    World world;

    public Game() {
        logger.info("Creating game window");
        gameWindow = new GameWindow("rlTut", ScreenWidth, ScreenHeight);
        consolePanel = new ConsolePanel(ScreenWidth, ScreenHeight);
        renderBuffer = new RenderBuffer(ScreenWidth, ScreenHeight);
        gameWindow.add(this.consolePanel);
        gameWindow.pack();

        world = new World(90, 31);
        this.renderer = new GameRenderer(renderBuffer, world);
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
            consolePanel.render(renderBuffer);
        }
    }
}
