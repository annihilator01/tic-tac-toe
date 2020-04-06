package com.ilian.tictactoe.backend.guiconnectors;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Function;

/**
 * Type of winning sequence.
 */
public enum Row {
    /**
     * Horizontal row.
     */
    HORIZONTAL (i -> 0, i -> i / 2),
    /**
     * Vertical row.
     */
    VERTICAL (i -> i / 2, i -> 0),
    /**
     * Downwards diagonal row.
     */
    DOWNWARDS_DIAGONAL (i -> 0, i -> 0),
    /**
     * Upwards diagonal row.
     */
    UPWARDS_DIAGONAL (i -> 0, i -> i);

    private Function<Integer, Integer> offsetX;
    private Function<Integer, Integer> offsetY;

    /**
     * Constructor with offsets parameters.
     * @param offsetX - start point for drawing win-line
     * @param offsetY - end point for drawing win-line
     */
    Row(Function<Integer, Integer> offsetX, Function<Integer, Integer> offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * Gets offset x.
     *
     * @param width the width
     * @return the offset x
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getOffsetX(int width) {
        return offsetX.apply(width);
    }

    /**
     * Gets offset y.
     *
     * @param height the height
     * @return the offset y
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getOffsetY(int height) {
        return offsetY.apply(height);
    }
}
