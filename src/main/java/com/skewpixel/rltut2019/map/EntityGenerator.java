package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ecs.Entity;

public interface EntityGenerator {
    int getMaxSpawnAttempts();

    Entity generateEntityAt(int x, int y);
}
