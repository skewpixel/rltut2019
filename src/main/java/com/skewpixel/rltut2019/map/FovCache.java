package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.util.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FovCache {
    private static final Logger logger = LoggerFactory.getLogger(FovCache.class);

    private final boolean[] isVisible;
    private final boolean[] isExplored;

    private final int width;
    private final int height;

    private boolean fovEnabled = true;

    public boolean isFovEnabled() {
        return fovEnabled;
    }

    public void setFovEnabled(boolean fovEnabled) {
        this.fovEnabled = fovEnabled;
    }

    public FovCache(int width, int height) {
        this.width = width;
        this.height = height;

        this.isVisible = new boolean[width * height];
        this.isExplored = new boolean[width * height];

        initialise();
    }

    public void clearVisibleCache() {
        ArrayUtils.arrayfill(isVisible, false);
    }

    private void initialise() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                initialiseFovData(x, y);
            }
        }
    }

    private boolean isValidCoord(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < width) && (y < height);
    }

    public void initialiseFovData(int x, int y) {
        if(!isValidCoord(x, y)) {
            logger.error("Unable to initialise FOV for x=[{}], y=[{}] because it is outside of FOV cache map: width=[{}], height=[{}]", x, y, width, height);
            return;
        }

        isVisible[x + y * width] = false;
    }

    public void updateFovData(int x, int y, boolean isVisible) {
        if(!isValidCoord(x, y)) {
            logger.error("Unable to initialise FOV for x=[{}], y=[{}] because it is outside of FOV cache map: width=[{}], height=[{}]", x, y, width, height);
            return;
        }

        this.isVisible[x + y * width] = isVisible;
        this.isExplored[x + y * width] = true;
    }

    public boolean isInFov(int x, int y) {
        if(!isValidCoord(x, y)) {
            logger.error("Unable to determine FOV for x=[{}], y=[{}] because it is outside of FOV cache map: width=[{}], height=[{}]", x, y, width, height);
            return false;
        }

        if(!fovEnabled) return true;

        return isVisible[x + y * width];
    }

    public boolean isExplored(int x, int y) {
        if(!isValidCoord(x, y)) {
            logger.error("Unable to determine IsExplored for x=[{}], y=[{}] because it is outside of IsExplored cache map: width=[{}], height=[{}]", x, y, width, height);
            return false;
        }

        if(!fovEnabled) return true;

        return isExplored[x + y * width];
    }
}
