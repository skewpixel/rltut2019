package com.skewpixel.rltut2019.ui;

import java.awt.*;

public class Terminal {

    private final SpriteSheet fontSpriteSheet;
    private final RenderBuffer renderBuffer;
    private Color foregroundColor = Color.white;
    private Color backgroundColor = Color.black;

    private final int cols;
    private final int rows;

    public Terminal(SpriteSheet fontSpriteSheet, int cols, int rows) {
        this.fontSpriteSheet = fontSpriteSheet;
        this.cols = cols;
        this.rows = rows;
        this.renderBuffer = new RenderBuffer(cols * this.fontSpriteSheet.getSpriteWidth(), rows * this.fontSpriteSheet.spriteHeight);

    }

    public int getWidth() {
        return cols * fontSpriteSheet.getSpriteWidth();
    }

    public int getHeight() {
        return rows * fontSpriteSheet.getSpriteHeight();
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public void clear() {
        clear(backgroundColor);
    }

    public void clear(Color color) {
        renderBuffer.fill(color);
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }


    public void write(String str, int col, int row, Color foregroundColor) {
        write(str, col, row, foregroundColor, null);
    }

    public void write(String str, int col, int row, Color foregroundColor, Color backgroundColor) {

        if(foregroundColor != null) setForegroundColor(foregroundColor);
        if(backgroundColor != null) setBackgroundColor(backgroundColor);

        write(str, col, row);
    }

    public void write(String str, int col, int row) {
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            write( c, col + i, row);
        }
    }

    public void write(char c, int col, int row) {

        int val = (int)c;
        int cy = val / fontSpriteSheet.numCols;
        int cx = val % fontSpriteSheet.numCols;

        fontSpriteSheet.drawToBuffer(renderBuffer, cx, cy,
                                        col * fontSpriteSheet.getSpriteWidth(), row * fontSpriteSheet.getSpriteHeight(),
                                        foregroundColor, backgroundColor);
    }

    public void write(char c, int col, int row, Color foregroundColor) {

        write(c, col, row, foregroundColor, null);
    }

    public void write(char c, int col, int row, Color foregroundColor, Color backgroundColor) {

        if(foregroundColor != null) setForegroundColor(foregroundColor);
        if(backgroundColor != null) setBackgroundColor(backgroundColor);

        write( c, col, row);
    }

    public RenderBuffer getRenderBuffer() {
        return this.renderBuffer;
    }
}
