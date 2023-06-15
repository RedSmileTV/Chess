package main.pieces;

import main.Board;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }
    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Implementation of valid move logic for a knight
        // ...
        return true;
    }
}
