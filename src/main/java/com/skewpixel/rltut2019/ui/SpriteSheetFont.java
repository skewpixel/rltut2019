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
            char c = str.charAt(i);
            drawChar(destBuf, c, (i * spriteWidth) + x, y, color, bgColor);
        }
    }

    public void drawChar(RenderBuffer destBuf, char c, int x, int y, int color) {
        drawChar(destBuf, c, x, y, color, -1);
    }

    public void drawChar(RenderBuffer destBuf, char c, int x, int y, int color, int bgColor) {
        int val = (int)c;
        int cy = val / numCols;
        int cx = val % numCols;

        drawToBuffer(destBuf, cx, cy, x, y, transparentColor, color, bgColor);
    }
}
