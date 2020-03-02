package com.ilian.tictactoe.backend;

public class Coordinates {
    private int x;
    private int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
