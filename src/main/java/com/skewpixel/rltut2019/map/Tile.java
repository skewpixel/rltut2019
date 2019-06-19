package com.skewpixel.rltut2019.map;

import com.skewpixel.rltut2019.ui.Colours;

public enum Tile {
    Floor((char)250, Colours.BrightWhite),
    Walls((char)177, Colours.BrightYellow),
    Bounds('x', Colours.BrightBlack);


    Tile(char glyph, int colour) {
        this.glyph = glyph;
        this.colour = colour;
    }

    private char glyph;
    private int colour;

    public char getGlyph() {
        return glyph;
    }

    public int getColour() {
        return colour;
    }
}
