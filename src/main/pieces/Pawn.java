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
        else if (startY + direction * 2 == endY && startX == endX && (startY == 6 || startY == 1)) {
            return board.getPiece(endX, endY) == null;
        }
        // Kollisionsmechanik (diagonal schlagen)
        else if (Math.abs(startX - endX) == 1 && startY + direction == endY) {
            if (board.getPiece(endX, endY) != null) {
                return true;
            }
            // TODO En passant logik
            else if (board.getLastMovedPiece() instanceof Pawn) {
                int lastMovedPawnX = board.getLastMovedPieceX();
                int lastMovedPawnY = board.getLastMovedPieceY();
                System.out.println("TestX: " + lastMovedPawnX);
                System.out.println("TestY: " + lastMovedPawnY);

//                if (Math.abs(startX - endX) == 1 && startY == lastMovedPawnY && endY == lastMovedPawnY + direction) {
//                    board.setPiece(lastMovedPawnX, lastMovedPawnY, null);
//                    return true;
//                }
                if (board.getLastMovedPiece().isWhite() && lastMovedPawnY == 0 || !board.getLastMovedPiece().isWhite() && lastMovedPawnY == 7) {
                    // TODO Promotion logic
                    System.out.println("Promotion");
                }
            }
        }
        return false;
    }
}
