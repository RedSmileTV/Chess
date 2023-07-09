package main.pieces;

import main.Board;
import main.Main;

import javax.swing.*;
import java.util.Objects;

public class Bishop extends Piece {
    private static final ImageIcon whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteBishop.png")));
    private static final ImageIcon blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackBishop.png")));
    public Bishop(boolean isWhite) {
        super(isWhite);
    }
    public ImageIcon getIcon() {
        if (isWhite()) return whiteIcon;
        else return blackIcon;
    }
    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        // Laufmechanik vom Läufer
        int rowDiff = Math.abs(endX - startX);
        int colDiff = Math.abs(endY - startY);

        if (rowDiff != colDiff) {
            // The move is not diagonal, so it's not a valid bishop move
            return false;
        }
        // Determine the direction of the move
        int rowDirection = (endX - startX) / rowDiff;
        int colDirection = (endY - startY) / colDiff;
        // Iterate through the squares between the starting and ending positions
        int currentX = startX + rowDirection;
        int currentY = startY + colDirection;
        while (currentX != endX || currentY != endY) {
            if (board.getPiece(currentX, currentY) != null) {
                // Wenn eine Figur im Weg ist
                return false;
            }
            currentX += rowDirection;
            currentY += colDirection;
        }
        // The move is valid
        return true;
    }
}
