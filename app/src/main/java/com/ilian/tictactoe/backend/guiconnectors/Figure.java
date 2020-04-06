package com.ilian.tictactoe.backend.guiconnectors;

import com.ilian.tictactoe.R;

/**
 * Play figure.
 */
public enum Figure {
    /**
     * Zero figure.
     */
    ZERO (R.drawable.zero, R.color.zeroColor),
    /**
     * Cross figure.
     */
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

    /**
     * Gets drawable id.
     *
     * @return the drawable id
     */
    public int getDrawableID() {
        return drawableID;
    }

    /**
     * Gets color id.
     *
     * @return the color id
     */
    public int getColorID() {
        return colorID;
    }

    /**
     * Gets next.
     *
     * @return the next
     */
    public Figure getNext()
    {
        return vals[(this.ordinal() + 1) % vals.length];
    }
}
