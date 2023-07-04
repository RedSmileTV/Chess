package main.pieces;

import main.Board;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        int direction = isWhite() ? 1 : -1;

        // Hier wird geprüft ob der Bauer angreifen kann
        if (startY + direction == endY && startX == endX) {
            if (board.getPiece(endX, endY) == null) {
                return true;
            }
        }

        return false;
    }
}
