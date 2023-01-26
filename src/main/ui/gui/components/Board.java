package ui.gui.components;

import model.pieces.Piece;
import ui.gui.JanggiGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * Class representing the board
 */
public class Board extends JPanel {
    public static final int CELL_X = 40;
    public static final int CELL_Y = 40;
    private static final int LEFT_X = 800;
    private static final int LEFT_Y = 900;
    private static final String CHARIOT_1 = "./src/main/img/Chinese-Rook-Black.png";
    private static final String KNIGHT_1 = "./src/main/img/Chinese-Horse-Black.png";
    private static final String ELEPHANT_1 = "./src/main/img/Chinese-Elephant-Black.png";
    private static final String GUARD_1 = "./src/main/img/Chinese-Advisor-Black.png";
    private static final String KING_1 = "./src/main/img/Chinese-King-Black.png";
    private static final String CANNON_1 = "./src/main/img/Chinese-Cannon-Black.png";
    private static final String SOLDIER_1 = "./src/main/img/Chinese-Pawn-Black.png";
    private static final String CHARIOT_2 = "./src/main/img/Chinese-Rook-Red.png";
    private static final String KNIGHT_2 = "./src/main/img/Chinese-Horse-Red.png";
    private static final String ELEPHANT_2 = "./src/main/img/Chinese-Elephant-Red.png";
    private static final String GUARD_2 = "./src/main/img/Chinese-Advisor-Red.png";
    private static final String KING_2 = "./src/main/img/Chinese-King-Red.png";
    private static final String CANNON_2 = "./src/main/img/Chinese-Cannon-Red.png";
    private static final String SOLDIER_2 = "./src/main/img/Chinese-Pawn-Red.png";

    private JanggiGUI gui;

    // EFFECTS: constructs a janggi board with an associated GUI
    public Board(JanggiGUI gui) {
        this.gui = gui;
        JPanel board = getBoard();
        board.setBounds(0, 0, LEFT_X,
                LEFT_Y);
        add(board);
    }

    // EFFECTS: constructs and returns the board
    @SuppressWarnings("methodlength")
    private JPanel getBoard() {
        JPanel board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        for (int j = 0; j < gui.getGameboard().getBoard().size(); j++) {
            JPanel row = new JPanel();
            row.setLayout(new FlowLayout());
            for (int i = 0; i < gui.getGameboard().getBoard().get(j).size(); i++) {
                Piece piece = gui.getGameboard().getBoard().get(j).get(i);
                if (piece.getOwner() == Piece.NO_OWNER) {
                    Space space = new Space(j, i);
                    space.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            gui.runPlaySequence(space);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                        }
                    });
                    row.add(space);
                } else if (piece.getOwner() == Piece.PLAYER_1) {
                    PlayablePiece playablePiece = getPieceForPlayerOne(piece, j, i);
                    playablePiece.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            gui.runPlaySequence(playablePiece);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                        }
                    });
                    row.add(playablePiece);
                } else {
                    PlayablePiece playablePiece = getPieceForPlayerTwo(piece, j, i);
                    playablePiece.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            gui.runPlaySequence(playablePiece);
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                        }
                    });
                    row.add(playablePiece);
                }
            }
            board.add(row);
        }
        return board;
    }

    // EFFECTS: given a piece from model package, a y-position, and an x-position,
    //          returns appropriate gui component for player one
    @SuppressWarnings("methodlength")
    private PlayablePiece getPieceForPlayerOne(Piece piece, int posY, int posX) {
        ImageIcon icon;
        switch (piece.getName()) {
            case "chariot":
                icon = new ImageIcon(CHARIOT_1);
                break;
            case "knight":
                icon = new ImageIcon(KNIGHT_1);
                break;
            case "elephant":
                icon = new ImageIcon(ELEPHANT_1);
                break;
            case "guard":
                icon = new ImageIcon(GUARD_1);
                break;
            case "king":
                icon = new ImageIcon(KING_1);
                break;
            case "cannon":
                icon = new ImageIcon(CANNON_1);
                break;
            default:
                icon = new ImageIcon(SOLDIER_1);
        }
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(CELL_X - 4, CELL_Y - 4, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        return new PlayablePiece(icon, posY, posX, piece.getOwner());
    }

    // EFFECTS: given a piece from model package, a y-position, and an x-position,
    //          returns appropriate gui component for player two
    @SuppressWarnings("methodlength")
    private PlayablePiece getPieceForPlayerTwo(Piece piece, int posY, int posX) {
        ImageIcon icon;
        switch (piece.getName()) {
            case "chariot":
                icon = new ImageIcon(CHARIOT_2);
                break;
            case "knight":
                icon = new ImageIcon(KNIGHT_2);
                break;
            case "elephant":
                icon = new ImageIcon(ELEPHANT_2);
                break;
            case "guard":
                icon = new ImageIcon(GUARD_2);
                break;
            case "king":
                icon = new ImageIcon(KING_2);
                break;
            case "cannon":
                icon = new ImageIcon(CANNON_2);
                break;
            default:
                icon = new ImageIcon(SOLDIER_2);
        }
        Image image = icon.getImage();
        Image newImg = image.getScaledInstance(CELL_X - 4, CELL_Y - 4, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        return new PlayablePiece(icon, posY, posX, piece.getOwner());
    }
}
