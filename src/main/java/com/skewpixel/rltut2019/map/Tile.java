package com.skewpixel.rltut2019.map;


import java.awt.*;

public enum Tile {
    Floor((char)250, Color.white),
    Walls((char)177, Color.yellow),
    Bounds('x', Color.black);


    Tile(char glyph, Color colour) {
        this.glyph = glyph;
        this.colour = colour;
    }

    private char glyph;
    private Color colour;

    public char getGlyph() {
        return glyph;
    }

    public Color getColour() {
        return colour;
    }
}
