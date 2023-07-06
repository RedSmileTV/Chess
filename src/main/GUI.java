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
    private final Board board = new Board();

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
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (event.getSource() == resetButton) {
                    board.initializeBoard();
                    updateBoard();
                }
            }
        });
        cp.add(boardPanel);
        cp.add(resetButton);
        cp.add(sidePanel);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardSquares[i][j] = new JButton();
                boardSquares[i][j].setBounds(50, 50, 50, 50);
                boardSquares[i][j].setActionCommand("" + (i + j * 8));
                boardSquares[i][j].addActionListener(this);
                boardPanel.add(boardSquares[i][j]);

                if ((i + j) % 2 == 0) boardSquares[i][j].setBackground(new Color(245, 245, 230)); // Dunkle Felder
                else boardSquares[i][j].setBackground(new Color(0, 80, 0)); // Helle Felder
            }
        }
        board.initializeBoard();
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
        System.out.println(this.board.getPiece(x, y));


    }

    public void updateSquareIcon(int x, int y, Board board) {
        x = x - 1;
        y = (y - 8) * -1;
        Piece piece = board.getPiece(x, y);

        if (piece != null) {
            // Zeigt das jeweilige icon für die jeweilige Figur an

            ImageIcon pieceIcon = null;
            if (piece instanceof Pawn) {
                if (piece.isWhite()) pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whitePawn.png")));
                else pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackPawn.png")));
            }
            else if (piece instanceof Rook) {
                if (piece.isWhite()) pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteRook.png")));
                else pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackRook.png")));
            }
            else if (piece instanceof Knight) {
                if (piece.isWhite()) pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteKnight.png")));
                else pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackKnight.png")));
            }
            else if (piece instanceof Bishop) {
                if (piece.isWhite()) pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteBishop.png")));
                else pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackBishop.png")));
            }
            else if (piece instanceof Queen) {
                if (piece.isWhite()) pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteQueen.png")));
                else pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackQueen.png")));
            }
            else if (piece instanceof King) {
                if (piece.isWhite()) pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whiteKing.png")));
                else pieceIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackKing.png")));
            }
            boardSquares[y][x].setIcon(resizeIcon(Objects.requireNonNull(pieceIcon)));
        }
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
}
