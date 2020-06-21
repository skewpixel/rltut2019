package com.skewpixel.rltut2019.util;

import java.util.Random;

public class RandUtils {
    private static Random random = new Random(System.currentTimeMillis());

    public static int randomInt(int min, int max) {
        if(min >= max) throw new IllegalArgumentException("Min must be less than max");

        return random.nextInt((max - min) + 1) + min;
    }
}
