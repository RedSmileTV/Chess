package main.pieces;

import main.Board;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
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
