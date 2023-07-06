package main.pieces;

import main.Board;
import main.Main;

import javax.swing.*;
import java.util.Objects;

public class Queen extends Piece {
    private static final ImageIcon whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteQueen.png")));
    private static final ImageIcon blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackQueen.png")));
    public Queen(boolean isWhite) {
        super(isWhite);
    }
    public ImageIcon getIcon() {
        if (isWhite()) return whiteIcon;
        else return blackIcon;
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        //Hier werden die Laufmechanik eines Läufers und Turm gegeben

        if (startX == endX || startY == endY) {
            //Laufmechanik eines Turms
            int dx = Integer.compare(endX, startX);
            int dy = Integer.compare(endY, startY);
            int x = startX + dx;
            int y = startY + dy;
            while (x != endX || y != endY) {
                //kollision
                if (board.getPiece(x, y) != null) {
                    return false;
                }
                x += dx;
                y += dy;
            }
            return true;
        }

        //Laufmechanik eines Läufers
        int rowDiff = Math.abs(endX - startX);
        int colDiff = Math.abs(endY - startY);
        if (rowDiff == colDiff) {
            //kollision
            int dx = Integer.compare(endX, startX);
            int dy = Integer.compare(endY, startY);
            int x = startX + dx;
            int y = startY + dy;
            while (x != endX || y != endY) {
                if (board.getPiece(x, y) != null) {
                    return false;
                }
                x += dx;
                y += dy;
            }
            return true;
        }

        return false;
    }
}
