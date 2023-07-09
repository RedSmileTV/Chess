package main;

import main.pieces.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {
    private final JButton[][] boardSquares = new JButton[8][8];
    private final JPanel boardPanel = new JPanel(new GridLayout(8, 8));
    private final JPanel sidePanel = new JPanel();
    private final JButton resetButton = new JButton();
    private JLabel turnLabel = new JLabel();
    private final Board board = new Board();
    private boolean isFirstClick = true;
    private int startX, startY;

    public GUI() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 720);
        setTitle("Chess");

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/icon.png")));
        setIconImage(icon.getImage());

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);

        Container cp = getContentPane();
        cp.setLayout(null);

        int boardWidth = 600;
        int boardHeight = 600;


        boardPanel.setBounds(16, 16, boardWidth, boardHeight);
        boardPanel.setOpaque(true);
        boardPanel.setBackground(Color.WHITE);
        boardPanel.setBorder(new javax.swing.border.LineBorder(new Color(0xC0C0C0), 4));

        sidePanel.setBounds(616, 16, 148, 600);
        sidePanel.setOpaque(true);
        sidePanel.setBackground(new Color(0x232323));
        sidePanel.setBorder(new LineBorder(Color.GRAY, 4));

        resetButton.setBounds(641, 32, 100, 50);
        resetButton.setBackground(Color.BLACK);
        resetButton.setBorder(new LineBorder(Color.WHITE, 4));
        resetButton.setForeground(Color.WHITE);
        resetButton.setText("Reset");
        resetButton.addActionListener(event -> {
            if (event.getSource() == resetButton) {
                isFirstClick = true;
                board.clearBoard();
                board.initializeBoard();
                turnChecker();
                updateBoard();
                clearBoardColors();
            }
        });

        turnLabel.setBounds(641, 94, 100, 50);
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        turnLabel.setVerticalAlignment(SwingConstants.CENTER);
        turnLabel.setOpaque(true);
        turnLabel.setBorder(new LineBorder(Color.GRAY, 4));
        turnChecker();
        cp.add(boardPanel);
        cp.add(turnLabel);
        cp.add(resetButton);
        cp.add(sidePanel);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardSquares[i][j] = new JButton();
                boardSquares[i][j].setBounds(50, 50, 50, 50);
                boardSquares[i][j].setActionCommand(String.valueOf((i + j * 8)));
                boardSquares[i][j].addActionListener(this);
                boardPanel.add(boardSquares[i][j]);

                if ((i + j) % 2 == 0) boardSquares[i][j].setBackground(new Color(245, 245, 230)); // Dunkle Felder
                else boardSquares[i][j].setBackground(new Color(0, 80, 0)); // Helle Felder
            }
        }
        updateBoard();
        setResizable(false);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        int a = Integer.parseInt(event.getActionCommand());
        int x = a / 8;
        int y = a % 8;
        System.out.println("x: " + x + " y: " + y);
        System.out.println(board.getPiece(x, y));

        Piece startPiece, endPiece;

        if (isFirstClick) {
            if (board.getPiece(x, y) == null) return; // Keine Figur ausgewählt
            isFirstClick = false;
            startX = x;
            startY = y;
        }
        else {
            isFirstClick = true;
            startPiece = board.getPiece(startX, startY);

            if (startPiece.isWhite() != board.getTurn()) return; // Falscher Spieler

            endPiece = board.getPiece(x, y);

            if (endPiece == null || startPiece.isWhite() == !endPiece.isWhite()) {
                // Überprüft ob Zug gemacht werden kann
                if (startPiece.isValidMove(board, startX, startY, x, y)) {
                    board.makeMove(startX, startY, x, y);
                    updateBoard();
                    board.setTurn(!board.getTurn());
                    turnChecker();
                    
                }
                else System.out.println("Invalid move!!!");
            }
            else {
                // Figur neu auswählen
                isFirstClick = false;
                startX = x;
                startY = y;
                System.out.println("Reselected");
            }
        }
    }
    public void updateSquareIcon(int x, int y, Board board) {
        x = x - 1;
        y = (y - 8) * -1;
        Piece piece = board.getPiece(x, y);

        if (piece != null) {
            // Zeigt das jeweilige icon für die jeweilige Figur an
            ImageIcon pieceIcon = piece.getIcon();
            boardSquares[y][x].setIcon(resizeIcon(Objects.requireNonNull(pieceIcon)));
        }
        else boardSquares[y][x].setIcon(null);
    }
    private ImageIcon resizeIcon(ImageIcon pieceIcon) {
        Image scaledPieceImage = pieceIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledPieceImage);
    }
    private void updateBoard() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                updateSquareIcon(x + 1, y + 1, this.board);
            }
        }
    }
    private void turnChecker() {
        if (board.getTurn()) {
            turnLabel.setText("White's turn");
            turnLabel.setForeground(Color.BLACK);
            turnLabel.setBackground(Color.WHITE);
        }
        else {
            turnLabel.setText("Black's turn");
            turnLabel.setForeground(Color.WHITE);
            turnLabel.setBackground(Color.BLACK);
        }
    }
    private void clearBoardColors() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((j + i) % 2 == 0) boardSquares[j][i].setBackground(new Color(245, 245, 230));
                else boardSquares[j][i].setBackground(new Color(0, 80, 0));
            }
        }
    }
//    private void checkKingInCheck(Board board) {
//        boolean isWhiteTurn = board.getTurn();
//        int[] kingPos = getKingPos(board, isWhiteTurn);
//        assert kingPos != null;
//        int kingX = kingPos[0];
//        int kingY = kingPos[1];
//
//        King king = (King) board.getPiece(kingX, kingY);
//        if (king.isCheck(board, kingX, kingY)) {
//            king.setCheckIcon();
//            updateSquareIcon(kingX + 1, kingY + 1, board);
//        } else {
//            king.setNormalIcon();
//            updateSquareIcon(kingX + 1, kingY + 1, board);
//        }
//    }
}
