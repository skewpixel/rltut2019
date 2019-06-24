package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.ui.Terminal;

import java.util.ArrayList;
import java.util.List;

public class GameRenderer {
    private final Terminal terminal;
    private final List<Renderable> renderables = new ArrayList<>();
    public GameRenderer(Terminal terminal) {
        this.terminal = terminal;
    }

    public void addRenderable(Renderable renderable) {
        this.renderables.add(renderable);
    }
    public void render() {

        terminal.clear();

        for(Renderable renderable : renderables) {
            renderable.render(terminal);
        }
    }
}
