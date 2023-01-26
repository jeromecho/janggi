package ui.gui.views;

import model.Game;
import ui.gui.JanggiGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/*
 * class representing the menu view of the GUI
 */
public class MenuView extends JPanel {
    private JanggiGUI gui;
    private JPanel mainDisplay;
    private JScrollPane gamesSection;
    private ListSelectionModel listSelectionModel;
    private int currentGameIndex;
    private int alternator;
    private JScrollPane scrollingPane;
    private JList games;


    // EFFECTS: constructs the menu view
    public MenuView(JanggiGUI gui) {
        this.gui = gui;
        this.games = new JList();
        this.setLayout(new FlowLayout());
        this.add(getButtons());
        this.alternator = 1;
        mainDisplay = getMainDisplay();
        this.add(mainDisplay);
    }

    // EFFECTS: constructs and returns the menu buttons
    @SuppressWarnings("methodlength")
    private JPanel getButtons() {
        JPanel buttonsContainer = new JPanel();
        buttonsContainer.setLayout(new BoxLayout(buttonsContainer, BoxLayout.Y_AXIS));
        JButton loadButton = new JButton(new AbstractAction("load") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.readFromFile();
            }
        });
        loadButton.setText("<html><b>LOAD GAMES</b></html>");
        JButton saveButton = new JButton(new AbstractAction("save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.writeToFile();
            }
        });
        saveButton.setText("<html><b>SAVE GAMES</b></html>");
        JButton deleteButton = new JButton(new AbstractAction("delete") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.removeCurrentGame();
            }
        });
        deleteButton.setText("<html><b>DELETE GAME</b></html>");
        JButton addButton = new JButton(new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.showAddGame();
            }
        });
        addButton.setText("<html><b>ADD GAME</b></html>");
        JButton playButton = new JButton(new AbstractAction("play") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.playCurrentGame();
            }
        });
        playButton.setText("<html><b>PLAY GAME</b></html>");
        JButton quitButton = new JButton(new AbstractAction("quit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.quitApplication();
            }
        });
        quitButton.setText("<html><b>QUIT</b></html>");

        buttonsContainer.add(loadButton);
        buttonsContainer.add(saveButton);
        buttonsContainer.add(deleteButton);
        buttonsContainer.add(addButton);
        buttonsContainer.add(playButton);
        buttonsContainer.add(quitButton);

        return buttonsContainer;
    }

    // MODIFIES: this
    // EFFECTS: constructs and returns the main display - containing the games list, title, and sarcastic
    //          caption
    private JPanel getMainDisplay() {
        JPanel mainDisplay = new JPanel();
        mainDisplay.setLayout(new BoxLayout(mainDisplay, BoxLayout.Y_AXIS));
        mainDisplay.add(getTitleSection());
        this.gamesSection = getGameSection();
        mainDisplay.add(this.gamesSection);
        return mainDisplay;
    }

    // MODIFIES: this
    // EFFECTS: removes and updates the games section of the main display
    public void updateGames() {
        updateListSelectionModel();
        this.scrollingPane.repaint();
        this.scrollingPane.revalidate();
    }

    // EFFECTS: constructs and returns the title section of main display
    private JPanel getTitleSection() {
        JPanel titleSection = new JPanel();
        titleSection.setLayout(new BoxLayout(titleSection, BoxLayout.Y_AXIS));
        ImageIcon icon = scaleImageIcon(new ImageIcon("./src/main/img/title_icon.png"), 50, 50);
        JLabel title = new JLabel("CASUAL JANGGI", icon, SwingConstants.TRAILING);
        title.setAlignmentX(CENTER_ALIGNMENT);
        JLabel caption = new JLabel("Not for single people.");
        caption.setAlignmentX(CENTER_ALIGNMENT);
        titleSection.add(title);
        titleSection.add(caption);
        return titleSection;
    }

    // EFFECTS: constructs and returns the games section of main display
    private JScrollPane getGameSection() {
        updateListSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (alternator % 2 != 0) {
                    if (gui.getGames().size() == 0) {
                        currentGameIndex = 0;
                    } else if (currentGameIndex != e.getFirstIndex()) {
                        currentGameIndex = e.getFirstIndex();
                        updateCurrentGame(e.getFirstIndex());
                    } else {
                        currentGameIndex = e.getLastIndex();
                        updateCurrentGame(e.getLastIndex());
                    }
                }
                alternator++;
            }
        });
        games.setLayout(new BoxLayout(games, BoxLayout.Y_AXIS));
        this.scrollingPane = new JScrollPane(games);
        scrollingPane.setPreferredSize(new Dimension(400, 200));
        return scrollingPane;
    }

    // MODIFIES: this
    // EFFECTS: updates the list selection model to correspond with updated data
    private void updateListSelectionModel() {
        List<String> gameNamesList = extractGameNames(gui.getGames());
        String[] gameNamesData = new String[gameNamesList.size()];
        gameNamesData = gameNamesList.toArray(gameNamesData);
        this.games.setListData(gameNamesData);
        this.listSelectionModel = this.games.getSelectionModel();
    }

    // MODIFIES: JanggiGui
    // EFFECTS: updates the current game selected to selected game
    private void updateCurrentGame(int index) {
        gui.setCurrentGame(index);
    }

    // EFFECTS: extracts all the game names of the given list of games
    private List<String> extractGameNames(List<Game> gamesList) {
        List<String> gameNames = new ArrayList<>();
        for (Game game: gamesList) {
            gameNames.add(game.getGameName());
        }
        return gameNames;
    }

    // EFFECTS: returns a scaled image of the given ImageIcon
    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }
}

/*
  - src for learning about arrays: https://www.w3schools.com/java/java_arrays.asp
 */
