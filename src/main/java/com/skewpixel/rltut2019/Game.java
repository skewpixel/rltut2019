package com.skewpixel.rltut2019;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.*;
import com.skewpixel.rltut2019.ecs.systems.FieldOfViewSystem;
import com.skewpixel.rltut2019.ecs.systems.MovementGameSystem;
import com.skewpixel.rltut2019.ecs.systems.PlayerInputGameSystem;
import com.skewpixel.rltut2019.map.FovCache;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.map.WorldBuilder;
import com.skewpixel.rltut2019.map.WorldDefinition;
import com.skewpixel.rltut2019.renderer.TextRenderer;
import com.skewpixel.rltut2019.renderer.GameRenderer;
import com.skewpixel.rltut2019.renderer.GlyphEntityRenderer;
import com.skewpixel.rltut2019.renderer.MapRenderer;
import com.skewpixel.rltut2019.screens.PlayScreen;
import com.skewpixel.rltut2019.screens.Screen;
import com.skewpixel.rltut2019.services.EventService;
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
    private static final int ScreenRows = 50;
    private Terminal terminal;

    private static final int WorldWidth = 80;
    private static final int WorldHeight = 45;

    private final World world;
    private final FovCache fovCache;

    private final List<Entity> entities = new ArrayList<>();
    private final Entity player;

    private Thread gameThread;
    private volatile boolean running = false;

    private GameWindow gameWindow;
    private RenderCanvas renderCanvas;

    private final GameRenderer renderer;
    private final InputService inputService;
    private final EventService eventService;

    private final FpsComponent fpsComponent;

    private final List<GameSystem> gameSystems = new ArrayList<>();

    private Screen currentScreen;

    public Game() {
        logger.info("Creating game window");
        terminal = new Terminal(TerminalFont.DefaultFont, ScreenCols, ScreenRows);

        this.fpsComponent = new FpsComponent();

        //
        // Input service
        ///
        inputService = new InputService();
        inputService.addKeyMapping("quit", KeyEvent.VK_ESCAPE);
        inputService.addKeyMapping("fullscreen", KeyEvent.VK_ENTER);
        inputService.addKeyMapping("forward", KeyEvent.VK_W);
        inputService.addKeyMapping("backward", KeyEvent.VK_S);
        inputService.addKeyMapping("left", KeyEvent.VK_A);
        inputService.addKeyMapping("right", KeyEvent.VK_D);
        inputService.addKeyMapping("togglefov", KeyEvent.VK_QUOTE);

        //
        // Game world
        ///
        world = WorldBuilder.buildWorld(new WorldDefinition(WorldWidth, WorldHeight, 10, 6, 15), entities);

        //
        // Events service
        //
        eventService = new EventService();

        //
        // Rendering part 1
        ///
        renderCanvas = new RenderCanvas(terminal.getWidth(), terminal.getHeight());
        renderer = new GameRenderer(terminal);

        fovCache = new FovCache(world.getWidth(), world.getHeight());
        renderer.addRenderer(MapRenderer.NAME, new MapRenderer(world, fovCache));
        renderer.addRenderer(GlyphEntityRenderer.NAME, new GlyphEntityRenderer(entities, fovCache));
        renderer.addRenderer("FpsRenderer", new TextRenderer(19, 0, () -> String.format("FPS: %d, TPS: %d", fpsComponent.fps, fpsComponent.tps)));
        renderer.addRenderer("TestTextRenderer", new TextRenderer(0, 0, "rltut2020 v0.0.1 - "));

        //
        // Game Window
        ///
        createGameWindow(false);

        //
        // Player
        ///
        player = new Entity();

        player.addComponent(new GlyphComponent('@', Color.red));
        player.addComponent(new PositionComponent(world.getSpawnPoint().x, world.getSpawnPoint().y, 0));
        player.addComponent(new MovementComponent());
        player.addComponent(new PlayerComponent());
        FovComponent fovComponent = new FovComponent(5);
        player.addComponent(fovComponent);
        entities.add(player);

        //
        // Game Systems
        ///
        gameSystems.add(new PlayerInputGameSystem(inputService, player));
        gameSystems.add(new MovementGameSystem(world, eventService));
        gameSystems.add(new FieldOfViewSystem(world, fovCache, fovComponent.viewRadius, eventService));

        //
        // Rendering part 2
        ///
        currentScreen = new PlayScreen(renderer);
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

        int frames = 0;
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
        long lastUpdateTime = 0;
        while(running) {
            currentMillis = GameTimer.getTime();

            // update
            int ticksLoop = 0;
            long updateTime = GameTimer.getTime();

            while((updateTime >= nextGameTick) && (ticksLoop < maxFrameSkip)) {
                tick(updateTime);
                lastUpdateTime = updateTime;

                nextGameTick += tickTime;
                ticksLoop++;
                ticks++;
                updateTime = GameTimer.getTime();
            }

            render();
            frames++;

            if(currentMillis - secondsCounter >= 1000) {
                fpsComponent.fps = frames;
                fpsComponent.tps = ticks;
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

    private void tick(long time) {
        if(!gameWindow.hasFocus()) {
            // pause?
        }
        else {
            inputService.tick(time);
            processKeys();
            currentScreen.update();
            for (GameSystem gameSystem : gameSystems) {
                gameSystem.tick(time);
            }
        }
    }

    private void processKeys() {
        if(inputService.isKeyPressed("quit", true)) {
            stop();
        }
        else if(inputService.isKeyPressed("fullscreen", true)) {
            createGameWindow(!gameWindow.isFullscreen());
            gameWindow.setVisible(true);
        }
        else if(inputService.isKeyPressed("togglefov", true)) {
            fovCache.setFovEnabled(!fovCache.isFovEnabled());
        }
    }

    private void render() {
        // get all the renderers to render
        renderer.render();

        // render the terminal into the canvas
        renderCanvas.render(terminal.getRenderBuffer());
    }
}
