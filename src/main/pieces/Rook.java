package main.pieces;

import main.Board;
import main.Main;

import javax.swing.*;
import java.util.Objects;

public class Rook extends Piece {
    private static final ImageIcon whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteRook.png")));
    private static final ImageIcon blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackRook.png")));
    public Rook(boolean isWhite) {
        super(isWhite);
    }
    public ImageIcon getIcon() {
        if (isWhite()) return whiteIcon;
        else return blackIcon;
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        //Laufmechanik eines Turms
        if (startX == endX || startY == endY) {
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
