package com.ilian.tictactoe.backend.guiconnectors;

import com.ilian.tictactoe.R;

/**
 * Play figure.
 */
public enum Figure {
    ZERO (R.drawable.zero, R.color.zeroColor),
    CROSS (R.drawable.cross, R.color.crossColor);

    private int drawableID;
    private int colorID;
    private static Figure[] vals = values();

    /**
     * Constructor with drawable image id and color id for figure.
     * @param drawableID - id of figure drawable image
     * @param colorID - id of figure color
     */
    Figure(int drawableID, int colorID) {
        this.drawableID = drawableID;
        this.colorID = colorID;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public int getColorID() {
        return colorID;
    }

    public Figure getNext()
    {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
