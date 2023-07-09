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
        // Laufmechanik eines Bauer (1 Feld)
        if (startY + direction == endY && startX == endX) {
            return board.getPiece(endX, endY) == null;
        }
        // Laufmechanik (2 Felder)
        else if (startY + direction * 2 == endY && startX == endX && startY == 6 || startY == 1 && startX == endX) {
            board.setLastMovedPiece(board.getPiece(endX, endY));
            return board.getPiece(endX, endY) == null;
        }
        // Kollisionsmechanik (diagonal schlagen)
        else if (Math.abs(startX - endX) == 1 && startY + direction == endY) {
            return board.getPiece(endX, endY) != null;
        }
        // TODO En passant
        else if (board.getLastMovedPiece() instanceof Pawn) {
            Pawn lastMovedPawn = (Pawn) board.getLastMovedPiece();
            int lastMovedPawnX = board.getLastMovedPieceX();
            int lastMovedPawnY = board.getLastMovedPieceY();

        }
        return false;
    }
}
