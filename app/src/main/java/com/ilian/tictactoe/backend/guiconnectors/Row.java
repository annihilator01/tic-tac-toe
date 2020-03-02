package com.ilian.tictactoe.backend.guiconnectors;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Function;

public enum Row {
    HORIZONTAL (i -> 0, i -> i / 2),
    VERTICAL (i -> i / 2, i -> 0),
    DOWNWARDS_DIAGONAL (i -> 0, i -> 0),
    UPWARDS_DIAGONAL (i -> 0, i -> i);

    private Function<Integer, Integer> offsetX;
    private Function<Integer, Integer> offsetY;

    Row(Function<Integer, Integer> offsetX, Function<Integer, Integer> offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getOffsetX(int width) {
        return offsetX.apply(width);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getOffsetY(int height) {
        return offsetY.apply(height);
    }
}
