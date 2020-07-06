package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.creatures.ai.BasicMobAi;
import com.skewpixel.rltut2019.ecs.Entity;
import com.skewpixel.rltut2019.ecs.components.*;
import com.skewpixel.rltut2019.ui.Colors;

public class CreatureGenerator {

    public Entity spawnTroll() {
        return new Entity(new NameComponent("Troll"), new GlyphComponent('T', Colors.DarkBrown),
                            new BlocksMovementComponent(), new MobAIComponent(BasicMobAi.Name), new MonsterComponent(),
                            new HealthComponent(16), new AttackerComponent(4), new DefenderComponent(1));
    }

    public Entity spawnOrc() {
        return new Entity(new NameComponent("Orc"), new GlyphComponent('O', Colors.DarkGreen),
                            new BlocksMovementComponent(), new MobAIComponent(BasicMobAi.Name), new MonsterComponent(),
                            new HealthComponent(10), new AttackerComponent(3), new DefenderComponent(0));
    }
}
