package com.ilian.tictactoe.backend;

import com.ilian.tictactoe.backend.guiconnectors.Figure;
import com.ilian.tictactoe.backend.guiconnectors.Row;

/**
 * Winner information.
 */
public class WinnerInfo {
    private Row row;
    private Figure figure;
    private Coordinates leftFieldPos;
    private Coordinates rightFieldPos;


    /**
     * Constructor with row, figure and position of most-left and most-right figure in the winning sequence
     *
     * @param row    - type of figure combination
     * @param figure - winning figure
     * @param leftX  - x coordinate of most-left figure in the winning sequence
     * @param leftY  - y coordinate of most-left figure in the winning sequence
     * @param rightX - x coordinate of most-right figure in the winning sequence
     * @param rightY - y coordinate of most-right figure in the winning sequence
     */
    WinnerInfo(Row row, Figure figure, int leftX, int leftY, int rightX, int rightY) {
        this.row = row;
        this.figure = figure;
        this.leftFieldPos = new Coordinates(leftX, leftY);
        this.rightFieldPos = new Coordinates(rightX, rightY);
    }

    /**
     * Gets row.
     *
     * @return the row
     */
    public Row getRow() {
        return row;
    }

    /**
     * Gets figure.
     *
     * @return the figure
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     * Gets left field pos.
     *
     * @return the left field pos
     */
    public Coordinates getLeftFieldPos() {
        return new Coordinates(leftFieldPos);
    }

    /**
     * Gets right field pos.
     *
     * @return the right field pos
     */
    public Coordinates getRightFieldPos() {
        return new Coordinates(rightFieldPos);
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }

        WinnerInfo winnerInfo = (WinnerInfo) o;
        return winnerInfo.row.equals(row) &&
                winnerInfo.figure.equals(figure) &&
                winnerInfo.leftFieldPos.equals(leftFieldPos) &&
                winnerInfo.rightFieldPos.equals(rightFieldPos);
    }
}
