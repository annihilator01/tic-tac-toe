package com.ilian.tictactoe.backend;

/**
 * Coordinates of figure on the play grid.
 */
public class Coordinates {
    private int x;
    private int y;

    /**
     * Constructor with two offsets.
     * @param x - offset from most-left field
     * @param y - offset from most-top field
     */
    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Copy constructor.
     * @param coordinates - figure coordinates
     */
    Coordinates(Coordinates coordinates) {
        this.x = coordinates.x;
        this.y = coordinates.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }

        Coordinates coordinates = (Coordinates) o;
        return coordinates.x == x && coordinates.y == y;
    }
}
