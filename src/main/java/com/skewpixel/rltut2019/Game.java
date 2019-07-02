package com.skewpixel.rltut2019;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.GlyphComponent;
import com.skewpixel.rltut2019.ecs.components.MovementComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.ecs.systems.MovementGameSystem;
import com.skewpixel.rltut2019.ecs.systems.PlayerInputGameSystem;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.renderer.GameRenderer;
import com.skewpixel.rltut2019.screens.PlayScreen;
import com.skewpixel.rltut2019.screens.Screen;
import com.skewpixel.rltut2019.services.InputService;
import com.skewpixel.rltut2019.ui.*;
import com.skewpixel.rltut2019.ecs.systems.GameSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private static final int ScreenCols = 80;
    private static final int ScreenRows = 21;
    private Terminal terminal;

    private static final int WorldWidth = 90;
    private static final int WorldHeight = 31;
    private final World world;
    private final List<Entity> entities = new ArrayList<>();
    private final Entity player;

    private Thread gameThread;
    private volatile boolean running = false;

    private GameWindow gameWindow;
    private RenderCanvas renderCanvas;

    private final GameRenderer renderer;
    private final InputService inputService;

    private final List<GameSystem> gameSystems = new ArrayList<>();

    private Screen currentScreen;

    public Game() {
        logger.info("Creating game window");
        terminal = new Terminal(TerminalFont.DefaultFont, ScreenCols, ScreenRows);

        renderCanvas = new RenderCanvas(terminal.getWidth(), terminal.getHeight());
        inputService = new InputService();
        inputService.addKeyMapping("quit", KeyEvent.VK_ESCAPE);
        inputService.addKeyMapping("fullscreen", KeyEvent.VK_ENTER);
        inputService.addKeyMapping("forward", KeyEvent.VK_W);
        inputService.addKeyMapping("backward", KeyEvent.VK_S);
        inputService.addKeyMapping("left", KeyEvent.VK_A);
        inputService.addKeyMapping("right", KeyEvent.VK_D);

        createGameWindow(false);

        world = new World(WorldWidth, WorldHeight, entities);

        renderer = new GameRenderer(terminal);

        player = new Entity();

        player.addComponent(new GlyphComponent('@', Color.red));
        player.addComponent(new PositionComponent(terminal.getCols()/2, terminal.getRows()/2, 0));
        player.addComponent(new MovementComponent());
        entities.add(player);

        currentScreen = new PlayScreen(world, entities);
        renderer.addRenderable(currentScreen);

        gameSystems.add(new PlayerInputGameSystem(inputService, player));
        gameSystems.add(new MovementGameSystem(world));
    }

    private void createGameWindow(boolean fullscreen) {
        if(gameWindow != null) {
            gameWindow.setVisible(false);
            gameWindow.dispose();
            gameWindow = null;
        }

        gameWindow = new GameWindow("rlTut", terminal.getWidth(), terminal.getHeight());
        gameWindow.setFullscreen(fullscreen);

        gameWindow.addKeyListener(inputService);
        gameWindow.addFocusListener(inputService);
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

            gameWindow.close();
            gameWindow.dispose();

            gameThread.join();
        } catch (InterruptedException e) {
            logger.error("Error trying to stop game thread",e);
        }
    }

    @Override
    public void run() {
        logger.info("Starting game loop");

        int frames  = 0;
        int ticks = 0;

        long currentMillis = GameTimer.getTime();
        long secondsCounter = currentMillis;

        int fps = 60;
        float frameTime = 1000/(float)fps;
        int maxFrameSkip = 5;

        int tps = 20;
        int tickTime = 1000/tps;


        double nextFrame = currentMillis;
        long nextGameTick = currentMillis;

        while(running) {
            currentMillis = GameTimer.getTime();

            // update
            int ticksLoop = 0;
            long updateTime = GameTimer.getTime();

            while((updateTime >= nextGameTick) && (ticksLoop < maxFrameSkip)) {
                tick();

                nextGameTick += tickTime;
                ticksLoop++;
                ticks++;
                updateTime = GameTimer.getTime();
            }

            render();
            frames++;

            if(currentMillis - secondsCounter >= 1000) {
                System.out.println(frames + " fps, " + ticks + " tps");
                frames = 0;
                ticks = 0;
                secondsCounter = currentMillis;
            }

            nextFrame += frameTime;

            // sleep
            long frameTimer = GameTimer.getTime();
            double sleepTime = (nextFrame - frameTimer) / 2.0; // sleep a little less?

            while(sleepTime < 0) {
                // overran, skip a frame
                nextFrame += frameTime;
                sleepTime = nextFrame - GameTimer.getTime();
            }

            GameTimer.sleep((long)sleepTime);
        }

        logger.info("Game loop stopped");
        gameWindow.close();
    }

    private void tick() {
        processKeys();
        currentScreen.update();
        for(GameSystem gameSystem : gameSystems) {
            gameSystem.tick();
        }
    }

    private void processKeys() {
        if(inputService.isKeyPressed("quit")) {
            stop();
        }
        else if(inputService.isKeyPressed("fullscreen")) {
            createGameWindow(!gameWindow.isFullscreen());
            gameWindow.setVisible(true);
        }
    }

    private void render() {
        renderer.render();
        renderCanvas.render(terminal.getRenderBuffer());
    }
}
