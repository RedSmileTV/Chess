package main.pieces;

import main.Board;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Laufmechanik vom Läufer
        int rowDiff = Math.abs(endX - startX);
        int colDiff = Math.abs(endY - startY);

        return rowDiff == colDiff;
    }
}
