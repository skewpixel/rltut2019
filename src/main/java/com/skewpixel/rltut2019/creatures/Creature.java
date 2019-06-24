package com.skewpixel.rltut2019.creatures;

import com.skewpixel.rltut2019.map.World;

import java.awt.*;

public class Creature {
    private int x;
    private int y;

    private char glyph;
    private Color color;

    private final World world;

    public Creature(char glyph, Color color, World world) {
        this.glyph = glyph;
        this.color = color;
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getGlyph() {
        return glyph;
    }

    public void setGlyph(char glyph) {
        this.glyph = glyph;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
