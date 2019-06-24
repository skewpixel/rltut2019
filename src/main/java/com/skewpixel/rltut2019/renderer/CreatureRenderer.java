package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.creatures.Creature;
import com.skewpixel.rltut2019.ui.Terminal;

import java.util.List;

public class CreatureRenderer implements Renderable {

    // TODO: get this from game state when required
    private final List<Creature> creatures;

    public CreatureRenderer(List<Creature> creatures) {
        this.creatures = creatures;
    }

    @Override
    public void render(Terminal terminal) {
        for(Creature creature : creatures) {
            terminal.write(creature.getGlyph(), creature.getX(), creature.getY(), creature.getColor());
        }
    }
}
