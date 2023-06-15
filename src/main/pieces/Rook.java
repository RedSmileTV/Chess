package main.pieces;

import main.Board;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Implementation of valid move logic for a rook
        // ...
        return true;
    }
}
