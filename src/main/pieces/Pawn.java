package main.pieces;

import main.Board;
import main.Main;

import javax.swing.*;
import java.util.Objects;

public class Pawn extends Piece {
    private static final ImageIcon whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whitePawn.png")));
    private static final ImageIcon blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackPawn.png")));
    public Pawn(boolean isWhite) {
        super(isWhite);
    }
    public ImageIcon getIcon() {
        if (isWhite()) return whiteIcon;
        else return blackIcon;
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        int direction = isWhite() ? -1 : 1;

        // Laufmechanik eines Bauer
        if (startY + direction == endY && startX == endX) {
            if (board.getPiece(endX, endY) == null) {
                return true;
            }
        }

        return false;
    }
}
