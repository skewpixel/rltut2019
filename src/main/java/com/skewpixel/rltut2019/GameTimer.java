package com.skewpixel.rltut2019;

public class GameTimer {

    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static void sleep(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
