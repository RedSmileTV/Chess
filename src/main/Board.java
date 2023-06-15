package main;

import main.pieces.Piece;

public class Board {
    private Piece[][] grid;

    public Board() {
        grid = new Piece[8][8];
        // Initialize the board with pieces
        // ...
    }

    public Piece getPiece(int x, int y) {
        return grid[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        grid[x][y] = piece;
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        Piece piece = getPiece(startX, startY);
        if (piece == null || !piece.isValidMove(this, startX, startY, endX, endY)) {
            return false;
        }
        // Additional validation logic, e.g., checking for obstructions, captures, etc.
        // ...
        return true;
    }
}
