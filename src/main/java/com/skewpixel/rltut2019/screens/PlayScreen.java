package com.skewpixel.rltut2019.screens;

import com.skewpixel.rltut2019.renderer.*;
import com.skewpixel.rltut2019.ui.Terminal;

public class PlayScreen implements Screen {

    // playscreen has a map view and a stats panel
    private final MapRenderer mapRenderer;
    private final GlyphEntityRenderer entityRenderer;
    private final TextRenderer fpsRenderer;
    private final TextRenderer testTextRenderer;
    private final PathRenderer pathRenderer;


    public PlayScreen(GameRenderer renderManager) {
        this.mapRenderer = renderManager.getRendererByName(MapRenderer.NAME, MapRenderer.class);
        this.entityRenderer = renderManager.getRendererByName(GlyphEntityRenderer.NAME, GlyphEntityRenderer.class);
        this.fpsRenderer = renderManager.getRendererByName("FpsRenderer", TextRenderer.class);
        this.testTextRenderer = renderManager.getRendererByName("TestTextRenderer", TextRenderer.class);
        this.pathRenderer = renderManager.getRendererByName(PathRenderer.NAME, PathRenderer.class);
    }

    @Override
    public void render(Terminal terminal) {
        // render the map
        mapRenderer.render(terminal);
        // then render the creatures
        entityRenderer.render(terminal);
        // render text
        testTextRenderer.render(terminal);
        // finally render fps
        fpsRenderer.render(terminal);

        pathRenderer.render(terminal);
    }

    public void update() {

    }
}
