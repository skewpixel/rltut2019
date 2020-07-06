package com.skewpixel.rltut2019.state;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.ComponentFactory;
import com.skewpixel.rltut2019.map.FovCache;
import com.skewpixel.rltut2019.map.World;

public class GlobalState {
    private static GlobalState instance;

    public GlobalState(World world, FovCache fovCache, ComponentFactory componentFactory) {
        this.world = world;
        this.fovCache = fovCache;
        this.componentFactory = componentFactory;
    }

    public static void initialise(World world, FovCache fovCache, ComponentFactory componentFactory) {
        if(instance == null) {
            instance = new GlobalState(world, fovCache, componentFactory);
        }
    }

    public static GlobalState get() {
        return instance;
    }

    public GameState gameState = GameState.Started;
    public ComponentFactory componentFactory;
    public World world;
    public FovCache fovCache;
}
