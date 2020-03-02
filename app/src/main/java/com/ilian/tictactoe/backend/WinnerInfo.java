package com.ilian.tictactoe.backend;

import com.ilian.tictactoe.backend.guiconnectors.Figure;
import com.ilian.tictactoe.backend.guiconnectors.Row;

public class WinnerInfo {
    private Row row;
    private Figure figure;
    private Coordinates leftFieldPos;
    private Coordinates rightFieldPos;

    WinnerInfo(Row row, Figure figure, int leftX, int leftY, int rightX, int rightY) {
        this.row = row;
        this.figure = figure;
        this.leftFieldPos = new Coordinates(leftX, leftY);
        this.rightFieldPos = new Coordinates(rightX, rightY);
    }

    public Row getRow() {
        return row;
    }

    public Figure getFigure() {
        return figure;
    }

    public Coordinates getLeftFieldPos() {
        return new Coordinates(leftFieldPos);
    }

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
