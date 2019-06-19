package com.skewpixel.rltut2019.ui;

public class SpriteSheetFont extends SpriteSheet {

    private final int transparentColor;

    public SpriteSheetFont(int spriteWidth, int spriteHeight, RenderBuffer spriteSheetBuf, int transparentColor) {
        super(spriteWidth, spriteHeight, spriteSheetBuf);
        this.transparentColor = transparentColor;
    }

    public void drawString(RenderBuffer destBuf, String str, int x, int y, int color) {
        drawString(destBuf, str, x, y, color, -1);
    }

    public void drawString(RenderBuffer destBuf, String str, int x, int y, int color, int bgColor) {

        for(int i = 0; i < str.length(); i++) {
            int c = (int)str.charAt(i);
            int cy = c / numCols;
            int cx = c % numCols;

            drawToBuffer(destBuf, cx, cy, (i * spriteWidth) + x, y, transparentColor, color, bgColor);
        }
    }
}
