package ui.gui.views;

import model.Position;
import model.pieces.Piece;
import ui.gui.JanggiGUI;
import ui.gui.components.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
 * class representing the in-game view of the GUI
 */
public class GameView extends JPanel {
    private JanggiGUI gui;
    private Board left;
    private JPanel right;

    // EFFECTS: constructs the game view
    public GameView(JanggiGUI gui) {
        this.gui = gui;
        this.setPreferredSize(new Dimension(480, 480));
        this.left = getLeft();
        this.right = getRight();
        this.add(this.left);
        this.add(this.right);
    }

    // MODIFIES: this
    // EFFECTS: updates the game view
    public void updateGameView() {
        this.remove(this.left);
        this.remove(this.right);
        this.left = getLeft();
        this.right = getRight();
        this.add(this.left);
        this.add(this.right);
    }

    // EFFECTS: constructs and returns the left part of the game view
    private Board getLeft() {
        Board left = new Board(this.gui);
        return left;
    }

    // EFFECTS: constructs and returns the right part of the game view
    private JPanel getRight() {
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.add(new JLabel("<html><h1><b>Playing Game " + gui.getCurrentGameName() + "</b></h1></html>"));
        right.add(new JLabel("<html><p><b>Current player is " + gui.getCurrentPlayerName() + "</b></p></html>"));
        JButton forfeitButton = new JButton(new AbstractAction("Forfeit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.forfeitGame();
            }
        });
        forfeitButton.setText("<html><b>FORFEIT</b></html>");
        right.add(forfeitButton);
        return right;
    }
}
