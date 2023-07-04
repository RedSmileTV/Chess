package main.pieces;

import main.Board;

public class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        //Laufmechanik vom König
        int rowDiff = Math.abs(endX - startX);
        int colDiff = Math.abs(endY - startY);

        return rowDiff <= 1 && colDiff <= 1;
    }
}
