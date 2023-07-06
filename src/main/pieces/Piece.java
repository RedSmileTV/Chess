package main.pieces;

import main.Board;

import javax.swing.*;

public abstract class Piece {
    private final boolean isWhite;
    private static final ImageIcon icon = new ImageIcon();

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }
    public ImageIcon getIcon() {
        return icon;
    }

    public boolean isWhite() {
        return isWhite;
    }


    public abstract boolean isValidMove(Board board, int startX, int startY, int endX, int endY);
}