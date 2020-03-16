package com.ilian.tictactoe.backend;

import com.ilian.tictactoe.backend.guiconnectors.Figure;
import com.ilian.tictactoe.backend.guiconnectors.Row;

import java.util.ArrayList;

/**
 * Logic manager of the game.
 */
public class GameManager {
    public static final int GRID_MIN_SIZE = 3;
    public static final int GRID_MAX_SIZE = 100;

    private int gridSize;
    private Figure turn;
    private Figure[][] grid;

    /**
    * Empty constructor.
    * */
    public GameManager() {
        this(3);
    }

    /**
     * Constructor with gridSize parameter.
     * @param gridSize - size of play grid
     * */
    public GameManager(int gridSize) {
        if (gridSize < GRID_MIN_SIZE || gridSize > GRID_MAX_SIZE) {
            gridSize = GRID_MIN_SIZE;
        }

        this.gridSize = gridSize;
        this.turn = Figure.CROSS;
        this.grid = new Figure[gridSize][gridSize];
    }

    /**
     * Put specified figure at the specified position.
     * @param figure - figure
     * @param i - number of line
     * @param j - number of column
     * @return boolean - if space was marked successfully returns true else false
     */
    public boolean markSpace(Figure figure, int i, int j) {
        if (i < 0 || j < 0 || j > gridSize || i > gridSize) {
            throw new IndexOutOfBoundsException("i: " + i + "; j = " + j);
        }

        if (grid[i][j] != null) {
            return false;
        }

        grid[i][j] = figure;
        turn = turn.getNext();

        return true;
    }

    /**
     * Get the grid size.
     * @return gridSize - size of play grid
     */
    public int getGridSize() {
        return gridSize;
    }

    /**
     * Get figure that is on the turn now.
     * @return turn - figure on the turn
     */
    public Figure getTurn() {
        return turn;
    }

    /**
     * Get figure at the specified position on the play grid.
     * @param i - number of line
     * @param j - number of column
     * @return Figure - figure at the specified position
     */
    public Figure getFigure(int i, int j) {
        if (i < 0 || j < 0 || j > gridSize || i > gridSize) {
            throw new IndexOutOfBoundsException("i: " + i + "; j = " + j);
        }

        return grid[i][j];
    }

    public boolean isDraw() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][j] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Get winner information if winner exist.
     * @return WinnerInfo - winner information or null if winner doesn't exist yet
     */
    public WinnerInfo getWinnerInfo() {
        ArrayList<Figure> sequence = new ArrayList<>();
        boolean isDraw = true;

        // horizontal checks
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                sequence.add(grid[i][j]);
            }

            if (isWinnerSequence(sequence)) {
                return new WinnerInfo(Row.HORIZONTAL, sequence.get(0), 0, i, gridSize - 1, i);
            }

            sequence.clear();
        }

        // vertical checks
        for (int j = 0; j < gridSize; j++) {
            for (int i = 0; i < gridSize; i++) {
                sequence.add(grid[i][j]);
            }

            if (isWinnerSequence(sequence)) {
                return new WinnerInfo(Row.VERTICAL, sequence.get(0), j, 0, j, gridSize - 1);
            }

            sequence.clear();
        }

        // downwards diagonal
        for (int i = 0; i < gridSize; i++) {
            sequence.add(grid[i][i]);
        }

        if (isWinnerSequence(sequence)) {
            return new WinnerInfo(Row.DOWNWARDS_DIAGONAL, sequence.get(0), 0, 0, gridSize - 1, gridSize - 1);
        }

        sequence.clear();

        // upwards diagonal
        for (int i = gridSize - 1; i >= 0; i--) {
            sequence.add(grid[i][gridSize - i - 1]);
        }

        if (isWinnerSequence(sequence)) {
            return new WinnerInfo(Row.UPWARDS_DIAGONAL, sequence.get(0), 0, gridSize - 1, gridSize - 1, 0);
        }

        sequence.clear();

        // winner not found
        return null;
    }

    /**
     * Check if sequence is winner sequence
     * @param sequence - sequence consisted of figures
     * @return boolean - true if sequence is winner sequence else false
     */
    private boolean isWinnerSequence(ArrayList<Figure> sequence) {
        Figure checkElement = sequence.get(0);

        for (Figure element : sequence) {
            if (element == null || element != checkElement) {
                return false;
            }
        }

        return true;
    }
}

