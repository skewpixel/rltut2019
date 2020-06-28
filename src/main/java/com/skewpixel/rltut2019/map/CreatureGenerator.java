package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.BlocksMovementComponent;
import com.skewpixel.rltut2019.ecs.components.GlyphComponent;
import com.skewpixel.rltut2019.ecs.components.NameComponent;
import com.skewpixel.rltut2019.ui.Colors;

public class CreatureGenerator {

    public Entity spawnTroll() {
        return new Entity(new NameComponent("Troll"), new GlyphComponent('T', Colors.DarkBrown), new BlocksMovementComponent());
    }

    public Entity spawnOrc() {
        return new Entity(new NameComponent("Orc"), new GlyphComponent('O', Colors.DarkGreen),
                            new BlocksMovementComponent());
    }
}
