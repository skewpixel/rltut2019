package com.skewpixel.rltut2019.map;


import com.skewpixel.rltut2019.ui.Colors;

import java.awt.*;

public enum Tile {
    Floor((char)250, Color.white, Colors.HalfWhite, true),
    Wall((char)177, Color.yellow, Colors.HalfYellow, false),
    Bounds('x', Color.black, Color.black, false),
    Unknown(' ', Color.black, Color.black, true);


    Tile(char glyph, Color primaryColor, Color secondaryColor, boolean walkable) {
        this(glyph, primaryColor, secondaryColor,  walkable, !walkable);
    }

    Tile(char glyph, Color primaryColor, Color secondaryColor, boolean walkable, boolean blocksSight) {
        this.glyph = glyph;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.walkable = walkable;
        this.blocksSight = blocksSight;
    }

    private char glyph;
    private Color primaryColor;
    private Color secondaryColor;
    private boolean walkable;
    private boolean blocksSight;

    public char getGlyph() {
        return glyph;
    }

    public Color getColour(TileColorType tileColorType) {
        return tileColorType == TileColorType.Primary ? primaryColor : secondaryColor;
    }

    public boolean isWalkable() { return walkable; }

    public boolean getBlocksSight() { return blocksSight; }

    public enum TileColorType { Primary, Secondary }
}
