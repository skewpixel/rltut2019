package com.skewpixel.rltut2019;

import com.skewpixel.rltut2019.ui.GameWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private final GameWindow gameWindow;
    private Thread gameThread;
    private volatile boolean running = false;


    public Game() {
        logger.info("Creating game window");
        gameWindow = new GameWindow("rlTut", 600, 400);

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

        }
    }
}
