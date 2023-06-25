package main;

import main.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GUI extends JFrame implements ActionListener {
    private final JButton[][] boardSquares = new JButton[8][8];
    private JPanel boardPanel = new JPanel(new GridLayout(8, 8));
    private JPanel sidePanel = new JPanel();
    private Board board;

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
        cp.add(boardPanel);

        sidePanel.setBounds(616, 16, 148, 600);
        sidePanel.setOpaque(true);
        sidePanel.setBackground(new Color(0x232323));
        sidePanel.setBorder(new javax.swing.border.LineBorder(Color.GRAY, 4));
        cp.add(sidePanel);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardSquares[i][j] = new JButton();
                boardSquares[i][j].setBounds(50, 50, 50, 50);
                boardSquares[i][j].setActionCommand("" + (i + j * 8));
                boardSquares[i][j].addActionListener(this);
                boardPanel.add(boardSquares[i][j]);

                if ((i + j) % 2 == 0) {
                    boardSquares[i][j].setBackground(new Color(245, 245, 230));
                    //Font color black
                }
                else boardSquares[i][j].setBackground(new Color(0, 80, 0));
                //Font color white
            }
        }

        updateSquareIcon(1, 1);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        int a = Integer.parseInt(event.getActionCommand());
        int x = a / 8 + 1;
        int y = a % 8;

        y = (y - 8) * -1;

        System.out.println("x: " + x + " y: " + y);
    }

    private void updateSquareIcon(int x, int y) {
        x = Main.updateX(x);
        y = Main.updateY(y);
        System.out.println(x);
        System.out.println(y);
//        board = new Board();
//        Piece piece = board.getPiece(x, y);
        Piece piece = new Pawn(true);

        if (piece != null) {
            // Set the button icon based on the piece
            // For example, you can use ImageIcon to represent the different pieces
            // You can modify this code to set the appropriate icons based on your implementation
            if (piece instanceof Pawn) {
                ImageIcon pawnIcon, scaledPawnIcon;
                Image scaledPawnImage;
                if (piece.isWhite()) {
                    pawnIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/whitePawn.png")));
                    scaledPawnImage = pawnIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
                    scaledPawnIcon = new ImageIcon(scaledPawnImage);
                    boardSquares[y][x].setIcon(scaledPawnIcon);
                }

                else pawnIcon = new ImageIcon(Objects.requireNonNull(Main.class.getResource("/resources/blackPawn.png")));
            } else if (piece instanceof Rook) {
                // Set the rook icon
                // ...
            } else if (piece instanceof Knight) {
                
            } else if (piece instanceof Bishop) {
                
            } else if (piece instanceof Queen) {
                
            } else if (piece instanceof King) {
                
            }
            // Handle other piece types
            // ...
        } else {
            // Set the button icon to null (no piece)
            boardSquares[x][y].setIcon(null);
        }
    }
    
}
