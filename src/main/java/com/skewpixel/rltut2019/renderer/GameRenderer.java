package com.skewpixel.rltut2019.renderer;

import com.skewpixel.rltut2019.map.Tile;
import com.skewpixel.rltut2019.map.World;
import com.skewpixel.rltut2019.ui.RenderBuffer;
import com.skewpixel.rltut2019.ui.SpriteSheetFont;
import com.skewpixel.rltut2019.ui.Textures;

public class GameRenderer {
    private final RenderBuffer renderBuffer;
    private final SpriteSheetFont spriteSheetFont;
    private final World world;

    public GameRenderer(RenderBuffer renderBuffer, World world) {
        this.renderBuffer = renderBuffer;
        this.world = world;
        this.spriteSheetFont = new SpriteSheetFont(9, 16, Textures.font, 0xFF00FF);
    }

    public void render() {

        for(int x = 0; x < world.getWidth(); x++) {
            for(int y = 0; y < world.getHeight(); y++) {
                Tile t = world.getTileAt(x, y);

                spriteSheetFont.drawChar(renderBuffer, t.getGlyph(), getScreenX(x), getScreenY(y), t.getColour());
            }
        }

        spriteSheetFont.drawChar(renderBuffer, '@', getScreenX(40), getScreenY(10), 0xFFFF0000);
    }

    private int getScreenX(int worldX) {
        return worldX * spriteSheetFont.getSpriteWidth();
    }

    private int getScreenY(int worldY) {
        return worldY * spriteSheetFont.getSpriteHeight();
    }
}
