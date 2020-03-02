package com.ilian.tictactoe.backend.guiconnectors;

import com.ilian.tictactoe.R;

public enum Figure {
    ZERO (R.drawable.zero, R.color.zeroColor),
    CROSS (R.drawable.cross, R.color.crossColor);

    private int drawableID;
    private int colorID;
    private static Figure[] vals = values();

    Figure(int drawableID, int color) {
        this.drawableID = drawableID;
        this.colorID = color;
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
