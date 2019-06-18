package com.skewpixel.rltut2019;

import ch.qos.logback.classic.util.ContextInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Launcher {

    public static void main(String[] args) {
        System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, "config/log.cfg.xml");

        Logger logger = LoggerFactory.getLogger(Launcher.class);

        System.out.println("RoguelikeDev Does the Complete Roguelike Tutorial");

        logger.info("RoguelikeDev Does the Complete Roguelike Tutorial");

    }
}
