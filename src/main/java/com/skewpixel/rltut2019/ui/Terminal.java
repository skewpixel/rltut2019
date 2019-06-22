package com.skewpixel.rltut2019.ui;

import java.awt.*;

public class Terminal {

    private final SpriteSheet fontSpriteSheet;
    private final RenderBuffer renderBuffer;
    private Color defaultForegroundColor = Color.white;
    private Color defaultBackgroundColor = Color.black;

    private final int cols;
    private final int rows;

    public Terminal(SpriteSheet fontSpriteSheet, int cols, int rows) {
        this.fontSpriteSheet = fontSpriteSheet;
        this.cols = cols;
        this.rows = rows;
        this.renderBuffer = new RenderBuffer(cols * this.fontSpriteSheet.getSpriteWidth(),
                                             rows * this.fontSpriteSheet.getSpriteHeight());

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
        clear(defaultBackgroundColor);
    }

    public void clear(Color color) {
        renderBuffer.fill(color);
    }

    public Color getDefaultForegroundColor() {
        return defaultForegroundColor;
    }

    public void setDefaultForegroundColor(Color defaultForegroundColor) {
        this.defaultForegroundColor = defaultForegroundColor;
    }

    public Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
    }

    public void setDefaultBackgroundColor(Color defaultBackgroundColor) {
        this.defaultBackgroundColor = defaultBackgroundColor;
    }


    public void write(String str, int col, int row, Color foregroundColor) {
        write(str, col, row, foregroundColor, defaultBackgroundColor);
    }

    public void write(String str, int col, int row, Color foregroundColor, Color backgroundColor) {
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            write( c, col + i, row, foregroundColor, backgroundColor);
        }
    }

    public void write(String str, int col, int row) {
        write(str, col, row, defaultForegroundColor);
    }

    public void write(char c, int col, int row, Color foregroundColor, Color backgroundColor) {

        int val = (int)c;
        int cy = val / fontSpriteSheet.numCols;
        int cx = val % fontSpriteSheet.numCols;

        fontSpriteSheet.drawToBuffer(renderBuffer, cx, cy,
                                        col * fontSpriteSheet.getSpriteWidth(), row * fontSpriteSheet.getSpriteHeight(),
                                        foregroundColor, backgroundColor);
    }

    public void write(char c, int col, int row, Color foregroundColor) {
        write(c, col, row, foregroundColor, defaultBackgroundColor);
    }

    public void write(char c, int col, int row) {
        write(c, col, row, defaultForegroundColor);
    }

    public RenderBuffer getRenderBuffer() {
        return this.renderBuffer;
    }
}
