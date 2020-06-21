package com.skewpixel.rltut2019.screens;

import com.skewpixel.rltut2019.renderer.TextRenderer;
import com.skewpixel.rltut2019.renderer.GameRenderer;
import com.skewpixel.rltut2019.renderer.GlyphEntityRenderer;
import com.skewpixel.rltut2019.renderer.MapRenderer;
import com.skewpixel.rltut2019.ui.Terminal;

public class PlayScreen implements Screen {

    // playscreen has a map view and a stats panel
    private final MapRenderer mapRenderer;
    private final GlyphEntityRenderer entityRenderer;
    private final TextRenderer fpsRenderer;
    private final TextRenderer testTextRenderer;


    public PlayScreen(GameRenderer renderManager) {
        this.mapRenderer = renderManager.getRendererByName(MapRenderer.NAME, MapRenderer.class);
        this.entityRenderer = renderManager.getRendererByName(GlyphEntityRenderer.NAME, GlyphEntityRenderer.class);
        this.fpsRenderer = renderManager.getRendererByName("FpsRenderer", TextRenderer.class);
        this.testTextRenderer = renderManager.getRendererByName("TestTextRenderer", TextRenderer.class);
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
    }

    public void update() {

    }
}
