package main;

import main.pieces.Piece;

public class Game {
    private Board board;
    private boolean isWhiteTurn;

    public Game() {
        board = new Board();
        isWhiteTurn = true;
    }

    public void makeMove(int startX, int startY, int endX, int endY) {
        if (board.isValidMove(startX, startY, endX, endY)) {
            Piece piece = board.getPiece(startX, startY);
            board.setPiece(endX, endY, piece);
            board.setPiece(startX, startY, null);
            isWhiteTurn = !isWhiteTurn;
            // Print the board after each move
            printBoard();
        } else {
            // Invalid move
            System.out.println("Invalid move!");
        }
    }

    public void printBoard() {
        // Print the current state of the board
        // ...
    }
}
