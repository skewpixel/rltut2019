package com.skewpixel.rltut2019;

import com.skewpixel.rltut2019.ui.ConsolePanel;
import com.skewpixel.rltut2019.ui.GameWindow;
import com.skewpixel.rltut2019.ui.RenderBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private final GameWindow gameWindow;
    private Thread gameThread;
    private volatile boolean running = false;
    private ConsolePanel consolePanel;

    RenderBuffer renderBuffer;

    public Game() {
        logger.info("Creating game window");
        gameWindow = new GameWindow("rlTut", 600, 400);
        consolePanel = new ConsolePanel(600, 400);
        renderBuffer = new RenderBuffer(600, 400);
        gameWindow.add(this.consolePanel);
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
            consolePanel.render(renderBuffer);
        }
    }
}
