package com.skewpixel.rltut2019.map;


import java.awt.*;

public enum Tile {
    Floor((char)250, Color.white, true),
    Wall((char)177, Color.yellow, false),
    Bounds('x', Color.black, false);


    Tile(char glyph, Color colour, boolean walkable) {
        this(glyph, colour, walkable, !walkable);
    }

    Tile(char glyph, Color colour, boolean walkable, boolean blocksSight) {
        this.glyph = glyph;
        this.colour = colour;
        this.walkable = walkable;
        this.blocksSight = blocksSight;
    }

    private char glyph;
    private Color colour;
    private boolean walkable;
    private boolean blocksSight;

    public char getGlyph() {
        return glyph;
    }

    public Color getColour() {
        return colour;
    }

    public boolean isWalkable() { return walkable; }

    public boolean getBlocksSight() { return blocksSight; }
}
