package main.pieces;

import main.Board;
import main.Main;

import javax.swing.*;
import java.util.Objects;

public class King extends Piece {
    private static final ImageIcon whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteKing.png")));
    private static final ImageIcon blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackKing.png")));
    public King(boolean isWhite) {
        super(isWhite);
    }
    public ImageIcon getIcon() {
        if (isWhite()) return whiteIcon;
        else return blackIcon;
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        //Laufmechanik vom König
        int rowDiff = Math.abs(endX - startX);
        int colDiff = Math.abs(endY - startY);

        return rowDiff <= 1 && colDiff <= 1;
    }
}
