package com.skewpixel.rltut2019.state;

public class GlobalState {
    private static GlobalState instance;

    public static void initialise() {
        if(instance == null) {
            instance = new GlobalState();
        }
    }

    public static GlobalState get() {
        return instance;
    }

    public GameState gameState = GameState.Started;
}
