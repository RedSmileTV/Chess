package main.pieces;

import main.Board;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }
    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        //Laufmechanik vom Springer
        int rowDiff = Math.abs(endX - startX);
        int colDiff = Math.abs(endY - startY);

        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}
