package com.skewpixel.rltut2019.ui;

public class SpriteSheet {

    protected final int spriteWidth;
    protected final int spriteHeight;
    private final RenderBuffer spriteSheetBuf;

    protected final int numCols;
    protected final int numRows;

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public SpriteSheet(int spriteWidth, int spriteHeight, RenderBuffer spriteSheetBuf) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.spriteSheetBuf = spriteSheetBuf;

        this.numCols = spriteSheetBuf.getWidth() / spriteWidth;
        this.numRows = spriteSheetBuf.getHeight() / spriteHeight;
    }

    public void drawToBuffer(RenderBuffer destBuf, int spriteX, int spriteY, int xOffset, int yOffset,
                             int transparentCol, int color) {
        drawToBuffer(destBuf, spriteX, spriteY, xOffset, yOffset, transparentCol, color, -1);
    }

    public void drawToBuffer(RenderBuffer destBuf, int spriteX, int spriteY, int xOffset, int yOffset,
                             int transparentCol, int color, int bgColor) {
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
                transparentCol, color, bgColor );
    }
}
