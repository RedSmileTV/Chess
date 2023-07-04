package main.pieces;

import main.Board;

import javax.swing.*;

public abstract class Piece {
    private boolean isWhite;
    private ImageIcon icon;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract boolean isValidMove(Board board, int startX, int startY, int endX, int endY);
}