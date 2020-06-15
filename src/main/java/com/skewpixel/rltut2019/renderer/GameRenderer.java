package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.ui.Terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRenderer {
    private final Terminal terminal;
    private final List<Renderable> renderables = new ArrayList<>();
    private final Map<String, Renderer> renderers = new HashMap<>();

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

    public void addRenderer(String name, Renderer renderer) {
        renderers.put(name, renderer);
    }

    public <T extends Renderer> T getRendererByName(String name, Class<T> clazz) {
        if (!renderers.containsKey(name)) {
            throw new RuntimeException("Unable to find renderer with name: " + name);
        }

        return clazz.cast(renderers.get(name));
    }
}
