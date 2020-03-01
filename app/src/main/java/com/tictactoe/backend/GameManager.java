package com.tictactoe.backend;

import java.util.ArrayList;

public class GameManager {
    public static final int GRID_MIN_SIZE = 3;
    public static final int GRID_MAX_SIZE = 5;

    private Figure[][] grid;
    private int gridSize;

    GameManager() {
        this(3);
    }

    GameManager(int gridSize) {
        if (gridSize < GRID_MIN_SIZE || gridSize > GRID_MAX_SIZE) {
            gridSize = 3;
        }

        this.gridSize = gridSize;
        grid = new Figure[gridSize][gridSize];
    }

    public boolean markSpace(Figure figure, int i, int j) {
        if (i < 0 || j < 0 || j > gridSize || i > gridSize) {
            throw new IndexOutOfBoundsException("i: " + i + "; j = " + j);
        }

        if (grid[i][j] != null) {
            return false;
        }

        grid[i][j] = figure;

        return true;
    }

    public int getGridSize() {
        return gridSize;
    }

    public Figure getFigure(int i, int j) {
        if (i < 0 || j < 0 || j > gridSize || i > gridSize) {
            throw new IndexOutOfBoundsException("i: " + i + "; j = " + j);
        }

        return grid[i][j];
    }

    public WinnerInfo getWinnerInfo() {
        ArrayList<Figure> sequence = new ArrayList<>();

        // horizontal checks
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                sequence.add(grid[i][j]);
            }

            if (isWinnerSequence(sequence)) {
                return new WinnerInfo(sequence.get(0), 0, i, gridSize - 1, i);
            }

            sequence.clear();
        }

        // vertical checks
        for (int j = 0; j < gridSize; j++) {
            for (int i = 0; i < gridSize; i++) {
                sequence.add(grid[i][j]);
            }

            if (isWinnerSequence(sequence)) {
                return new WinnerInfo(sequence.get(0), j, 0, j, gridSize - 1);
            }

            sequence.clear();
        }

        // downwards diagonal
        for (int i = 0; i < gridSize; i++) {
            sequence.add(grid[i][i]);
        }

        if (isWinnerSequence(sequence)) {
            return new WinnerInfo(sequence.get(0), 0, 0, gridSize - 1, gridSize - 1);
        }

        sequence.clear();

        // upwards diagonal
        for (int i = gridSize - 1; i >= 0; i--) {
            sequence.add(grid[i][gridSize - i - 1]);
        }

        if (isWinnerSequence(sequence)) {
            return new WinnerInfo(sequence.get(0), 0, gridSize - 1, gridSize - 1, 0);
        }

        sequence.clear();

        // winner not found
        return null;
    }

    private boolean isWinnerSequence(ArrayList<Figure> sequence) {
        Figure checkElement = sequence.get(0);

        if (checkElement == null) {
            return false;
        }

        for (Figure element : sequence) {
            if (element == null || element != checkElement) {
                return false;
            }
        }

        return true;
    }
}

