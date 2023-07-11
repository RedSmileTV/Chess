package main.pieces;

import main.Board;
import main.Main;

import javax.swing.*;
import java.util.Objects;

public class King extends Piece {
    private static ImageIcon whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteKing.png")));
    private static ImageIcon blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackKing.png")));
    public King(boolean isWhite) {
        super(isWhite);
    }
    public ImageIcon getIcon() {
        if (isWhite()) return whiteIcon;
        else return blackIcon;
    }
    public void setCheckIcon() {
        if (isWhite()) whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteKingCheck.png")));
        else blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackKingCheck.png")));
    }
    public void setNormalIcon() {
        if (isWhite()) whiteIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteKing.png")));
        else blackIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackKing.png")));
    }


    // TODO Check logic
    @Override
    public boolean isValidMove(Board board, int startX, int startY, int endX, int endY) {
        int rowDiff = Math.abs(endX - startX);
        int colDiff = Math.abs(endY - startY);

        return rowDiff == colDiff;
    }
    private boolean isCheck(Board board, int startX, int startY, int endX, int endY) {
        if (!isValidMove(board, startX, startY, endX, endY)) {
            setCheckIcon();
            return true;
        }
        else {
            setNormalIcon();
            return false;
        }
    }
}
