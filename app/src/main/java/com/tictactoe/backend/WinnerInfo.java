package com.tictactoe.backend;

public class WinnerInfo {
    private Figure figure;
    private Coordinates leftFieldPos;
    private Coordinates rightFieldPos;

    WinnerInfo(Figure figure, int leftX, int leftY, int rightX, int rightY) {
        this.figure = figure;
        this.leftFieldPos = new Coordinates(leftX, leftY);
        this.rightFieldPos = new Coordinates(rightX, rightY);
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
        return winnerInfo.figure.equals(figure) &&
                winnerInfo.leftFieldPos.equals(leftFieldPos) &&
                winnerInfo.rightFieldPos.equals(rightFieldPos);
    }
}
