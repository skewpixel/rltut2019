package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.GlyphComponent;
import com.skewpixel.rltut2019.ecs.components.PositionComponent;
import com.skewpixel.rltut2019.ui.Colors;

import static com.skewpixel.rltut2019.util.RandUtils.randomInt;

public class CreatureGenerator implements EntityGenerator {
    @Override
    public int getMaxEntities() {
        return 3;
    }

    @Override
    public Entity generateEntityAt(int x, int y) {

        Entity entity = null;
        int s = randomInt(0, 100);
        if((s > 20) && (s < 45)){
            entity = new Entity(new GlyphComponent('o', Colors.DarkGreen), new PositionComponent(x, y, 0));
        }
        return entity;
    }
}
