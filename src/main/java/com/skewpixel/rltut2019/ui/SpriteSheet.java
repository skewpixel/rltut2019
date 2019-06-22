package com.skewpixel.rltut2019.ui;

import java.awt.*;

public class SpriteSheet {

    private final int spriteWidth;
    private final int spriteHeight;
    private final Color transparentColor;
    private final RenderBuffer spriteSheetBuf;

    protected final int numCols;
    protected final int numRows;

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public SpriteSheet(int spriteWidth, int spriteHeight, RenderBuffer spriteSheetBuf, Color transparentColor) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.spriteSheetBuf = spriteSheetBuf;
        this.transparentColor = transparentColor;

        this.numCols = spriteSheetBuf.getWidth() / spriteWidth;
        this.numRows = spriteSheetBuf.getHeight() / spriteHeight;
    }

    public void drawToBuffer(RenderBuffer destBuf, int spriteX, int spriteY, int xOffset, int yOffset, Color color) {
        drawToBuffer(destBuf, spriteX, spriteY, xOffset, yOffset, color, null);
    }

    public void drawToBuffer(RenderBuffer destBuf, int spriteX, int spriteY, int xOffset, int yOffset, Color color, Color bgColor) {
        int startX = spriteX * spriteWidth;
        int startY = spriteY * spriteHeight;

        if(startX < 0) return;
        if(startY < 0) return;
        if(startX > spriteSheetBuf.getWidth()) return;
        if(startY > spriteSheetBuf.getHeight()) return;

        if(spriteX < 0) return;
        if(spriteY < 0) return;
        if(spriteX > numCols - 1) return;
        if(spriteY > numRows - 1) return;

        if(xOffset + spriteWidth > destBuf.getWidth()) return;
        if(yOffset + spriteHeight > destBuf.getHeight()) return;

        destBuf.draw(spriteSheetBuf, startX, startY, spriteWidth, spriteHeight, xOffset, yOffset,
                transparentColor, color, bgColor );
    }
}
