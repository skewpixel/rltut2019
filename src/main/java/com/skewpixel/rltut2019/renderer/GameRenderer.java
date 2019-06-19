package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.ui.RenderBuffer;
import com.skewpixel.rltut2019.ui.SpriteSheetFont;
import com.skewpixel.rltut2019.ui.Textures;

public class GameRenderer {
    private final RenderBuffer renderBuffer;
    private final SpriteSheetFont spriteSheetFont;

    public GameRenderer(RenderBuffer renderBuffer) {
        this.renderBuffer = renderBuffer;
        this.spriteSheetFont = new SpriteSheetFont(9, 16, Textures.font, 0xFF00FF);
    }

    public void render() {
        spriteSheetFont.drawString(renderBuffer, "TEST", 10, 10, 0xFFFF0000);
    }
}
