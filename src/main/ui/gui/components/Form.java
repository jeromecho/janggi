package ui.gui.components;

import ui.gui.JanggiGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Class representing form for adding games to a list of games
 */
public class Form extends JPanel {

    private JTextField gameNameField;
    private JTextField playerOneNameField;
    private JTextField playerTwoNameField;
    private String gameName;
    private String playerOneName;
    private String playerTwoName;
    private JanggiGUI gui;

    // EFFECTS: constructs a form component with an associated GUI
    public Form(JanggiGUI gui) {
        this.gui = gui;
        this.gameName = "";
        this.playerOneName = "";
        this.playerTwoName = "";
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(getGameNameSection());
        this.add(getPlayerOneNameSection());
        this.add(getPlayerTwoNameSection());
        JButton submitButton = new JButton(new AbstractAction("submit game") {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGame();
            }
        });
        submitButton.setText("<html><h3><b>ADD GAME</b></h3></html>");
        this.add(submitButton);
    }

    // EFFECTS: creates and returns game name section of form
    private JPanel getGameNameSection() {
        JPanel gameNameSection = new JPanel();
        gameNameSection.setAlignmentX(LEFT_ALIGNMENT);
        gameNameSection.setLayout(new BoxLayout(gameNameSection, BoxLayout.Y_AXIS));
        JLabel gameName = new JLabel("<html><h4>Game Name</h4></html>");
        gameNameField = new JTextField(40);
        gameNameField.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                updateGameName();
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        gameNameSection.add(gameName);
        gameNameSection.add(gameNameField);
        gameName.setAlignmentX(LEFT_ALIGNMENT);
        gameNameField.setAlignmentX(LEFT_ALIGNMENT);
        return gameNameSection;
    }

    // EFFECTS: creates and returns player one name section of form
    private JPanel getPlayerOneNameSection() {
        JPanel playerOneNameSection = new JPanel();
        playerOneNameSection.setLayout(new BoxLayout(playerOneNameSection, BoxLayout.Y_AXIS));
        playerOneNameSection.setAlignmentX(LEFT_ALIGNMENT);
        JLabel playerOneName = new JLabel("<html><h4>Player One Name</h4></html>");
        playerOneNameField = new JTextField(40);
        playerOneNameField.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                updatePlayerOneName();
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        playerOneName.setAlignmentX(LEFT_ALIGNMENT);
        playerOneNameField.setAlignmentX(LEFT_ALIGNMENT);
        playerOneNameSection.add(playerOneName);
        playerOneNameSection.add(playerOneNameField);
        return playerOneNameSection;
    }

    // EFFECTS: creates and returns player two name section of form
    private JPanel getPlayerTwoNameSection() {
        JPanel playerTwoNameSection = new JPanel();
        playerTwoNameSection.setLayout(new BoxLayout(playerTwoNameSection, BoxLayout.Y_AXIS));
        playerTwoNameSection.setAlignmentX(LEFT_ALIGNMENT);
        JLabel playerTwoName = new JLabel("<html><h4>Player Two Name</h4></html>");
        playerTwoNameField = new JTextField(40);
        playerTwoNameField.addKeyListener(new KeyListener() {
            @Override
            public void keyReleased(KeyEvent e) {
                updatePlayerTwoName();
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        playerTwoName.setAlignmentX(LEFT_ALIGNMENT);
        playerTwoNameField.setAlignmentX(LEFT_ALIGNMENT);
        playerTwoNameSection.add(playerTwoName);
        playerTwoNameSection.add(playerTwoNameField);
        return playerTwoNameSection;
    }

    // MODIFIES: this
    // EFFECTS: updates the game name to be the current value inputted into text field
    private void updateGameName() {
        this.gameName = this.gameNameField.getText();
    }

    // MODIFIES: this
    // EFFECTS: updates the player one name to be the current value inputted into text field
    private void updatePlayerOneName() {
        this.playerOneName = this.playerOneNameField.getText();
    }

    // MODIFIES: this
    // EFFECTS: updates the player two name to be the current value inputted into text field
    private void updatePlayerTwoName() {
        this.playerTwoName = this.playerTwoNameField.getText();
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the list of games, resets all field values to empty strings,
    //          then closes the add game view, returning to the menu view.
    private void addGame() {
        gui.addGame(this.gameName, this.playerOneName, this.playerTwoName);
        gameNameField.setText("");
        playerOneNameField.setText("");
        playerTwoNameField.setText("");
        gui.showMenu();
    }
}
