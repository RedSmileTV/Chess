package main;

import main.pieces.*;

public class Board {
    private final Piece[][] grid;
    private boolean isWhiteTurn;

    public Board() {
        grid = new Piece[8][8];
        initializeBoard();
    }
    public boolean getTurn() {
        return isWhiteTurn;
    }
    public void setTurn(boolean turn) {
        isWhiteTurn = turn;
    }

    public Piece getPiece(int x, int y) {
        return grid[x][y];
    }

    public void setPiece(int x, int y, Piece piece) {
        grid[x][y] = piece;
    }

    public boolean isValidMove(int startX, int startY, int endX, int endY) {
        Piece piece = getPiece(startX, startY);
        return piece != null && piece.isValidMove(this, startX, startY, endX, endY);
        // Additional validation logic, e.g., checking for obstructions, captures, etc.
        // ...
    }
    public boolean makeMove(int startX, int startY, int endX, int endY) {
        if (!isValidMove(startX, startY, endX, endY)) {
            return false;
        }

        Piece piece = getPiece(startX, startY);
        setPiece(startX, startY, null);
        setPiece(endX, endY, piece);

        // Additional logic for special moves, captures, promotions, etc.

        return true;
    }
    public void initializeBoard() {
        // Weiß beginnt
        isWhiteTurn = true;
        // Platziert die Figuren in deren Startposition

        // Platziert Bauern
        for (int i = 0; i < 8; i++) {
            grid[i][6] = new Pawn(true);
            grid[i][1] = new Pawn(false);
        }

        // Platziert Türme, Springer, Läufer, König und Königin
        grid[0][7] = new Rook(true);
        grid[7][7] = new Rook(true);
        grid[0][0] = new Rook(false);
        grid[7][0] = new Rook(false);

        grid[1][7] = new Knight(true);
        grid[6][7] = new Knight(true);
        grid[1][0] = new Knight(false);
        grid[6][0] = new Knight(false);

        grid[2][7] = new Bishop(true);
        grid[5][7] = new Bishop(true);
        grid[2][0] = new Bishop(false);
        grid[5][0] = new Bishop(false);

        grid[3][7] = new Queen(true);
        grid[3][0] = new Queen(false);

        grid[4][7] = new King(true);
        grid[4][0] = new King(false);
    }
    public void clearBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = null;
            }
        }
    }
}
