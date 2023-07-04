package main;

import main.pieces.Piece;

public class Game {
    private Board board;
    private boolean isWhiteTurn;

    public Game() {
        board = new Board();
        isWhiteTurn = true;
    }

    public boolean makeMove(int startX, int startY, int endX, int endY) {
        if (!board.isValidMove(startX, startY, endX, endY)) {
            return false;
        }

        Piece piece = board.getPiece(startX, startY);
        board.setPiece(startX, startY, null);
        board.setPiece(endX, endY, piece);
        isWhiteTurn = !isWhiteTurn;
        return true;
    }

}
