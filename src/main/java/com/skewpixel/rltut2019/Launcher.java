package com.skewpixel.rltut2019;

import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.concurrent.locks.LockSupport;

public class Launcher {

    public static void main(String[] args) {
        // setup the logging config
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/log.cfg.xml");

        Logger logger = LoggerFactory.getLogger(Launcher.class);

        logger.info("RoguelikeDev Does the Complete Roguelike Tutorial");
        logger.info("Creating and starting main game class...");

        try {
            new Game().start();
        } catch (Exception e) {
            logger.error("Error starting game", e);
        }

        logger.info("Done launching game class");


//        long startTime = GameSystem.nanoTime();
//        int iterationCount = 100000;
//        long sleepTime = 500000;
//
//        logger.info("Starting...");
//
//        for(int i = 0; i < iterationCount; i++)
//            LockSupport.parkNanos(sleepTime);
//
//        long endTime = GameSystem.nanoTime();
//
//        long duration = endTime - startTime;
//
//        double iteration = duration / (double)iterationCount;
//
//        logger.info("[{}, {}, {}, {}]", startTime, endTime, duration, iteration);
    }
}
