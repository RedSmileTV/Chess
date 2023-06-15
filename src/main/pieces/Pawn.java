package main.pieces;

import main.Board;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        int direction = isWhite() ? 1 : -1;

        if (startY + direction == endY && startX == endX) {
            System.out.println("Pawn moves forward");
        }
        return true;
    }
}
