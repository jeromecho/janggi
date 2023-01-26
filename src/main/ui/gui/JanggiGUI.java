package ui.gui;

import model.*;
import model.events.Event;
import model.events.EventLog;
import model.pieces.Piece;
import org.json.JSONPropertyName;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.gui.components.Placeable;
import ui.gui.views.AddGameView;
import ui.gui.views.GameView;
import ui.gui.views.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 Class representing janggi GUI
 */
public class JanggiGUI {
    private static final String MENU_VIEW = "menu";
    private static final String GAME_VIEW = "game";
    private static final String ADD_GAME_VIEW = "add game";
    private static int VIEW_DIM_X = 480;
    private static int VIEW_DIM_Y = 480;
    private static final String DATA_PATH = "./data/gamemanager.json";

    private static CardLayout cl;
    private static JPanel container;
    private static JFrame mFrame;
    private GameManager gameManager;
    private MenuView menuView;
    private AddGameView addGameView;
    private GameView gameView;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Piece currentPiece;
    private EventLog el = EventLog.getInstance();

    // EFFECTS: constructs containing window of the janggi GUI
    public JanggiGUI() {
        this.gameManager = new GameManager();
        this.currentPiece = null;
        this.jsonWriter = new JsonWriter(DATA_PATH);
        this.jsonReader = new JsonReader(DATA_PATH);
        setUpMainFrame();
        this.cl = new CardLayout();
        this.container = new JPanel(cl);
        this.container.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.container.setAlignmentY(Component.CENTER_ALIGNMENT);
        addPanelsToCardLayout();
        showMenu();
        this.mFrame.add(container);
        this.mFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
    }

    // MODIFIES: this
    // EFFECTS: constructs and adds event listeners to the main frame
    public void setUpMainFrame() {
        this.mFrame = new JFrame("Casual Janggi");
        this.mFrame.setFocusable(true);
        this.mFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'a') {
                    showAddGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    // EFFECTS: writes application state to json file
    public void writeToFile() {
        try {
            this.jsonWriter.writeGameManager(this.gameManager);
            JOptionPane.showMessageDialog(mFrame,
                    "Wrote games to file.", "Success!", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(mFrame,
                    "Error writing games to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: reads application state from json file
    public void readFromFile() {
        try {
            this.gameManager = this.jsonReader.loadGameManager();
            menuView.updateGames();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(mFrame,
                    "Error loading games from file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: shows the menu view on the GUI
    public void showMenu() {
        cl.show(container, MENU_VIEW);
    }

    // EFFECTS: shows the game view on the GUI
    public void showGame() {
        cl.show(container, GAME_VIEW);
    }

    // EFFECTS: shows the add game view on the GUI
    public void showAddGame() {
        cl.show(container, ADD_GAME_VIEW);
    }

    // MODIFIES: this
    // EFFECTS: starts game by making GUI window visible
    public void startGame() {
        mFrame.setVisible(true);
    }

    // EFFECTS: gets games of game manager
    public ArrayList<Game> getGames() {
        ArrayList<Game> games = (ArrayList<Game>) this.gameManager.getGamesList();
        return games;
    }

    // REQUIRES: given index must be < size of games list
    // MODIFIES: gameManager
    // EFFECTS: sets the current game of the game manager to given index
    public void setCurrentGame(int index) {
        this.gameManager.chooseCurrentGame(index);
    }

    // EFFECTS: adds panels to card layout, defining all the views
    //          of the program
    private void addPanelsToCardLayout() {
        this.menuView = new MenuView(this);
        this.gameView = new GameView(this);
        this.addGameView = new AddGameView(this);

        this.gameView.setLayout(new GridBagLayout());
        this.menuView.setLayout(new GridBagLayout());
        this.addGameView.setLayout(new GridBagLayout());

        this.menuView.setPreferredSize(new Dimension(VIEW_DIM_X, VIEW_DIM_Y));
        this.gameView.setPreferredSize(new Dimension(VIEW_DIM_X, VIEW_DIM_Y));
        this.addGameView.setPreferredSize(new Dimension(VIEW_DIM_X, VIEW_DIM_Y));

        this.container.add(menuView, MENU_VIEW);
        this.container.add(gameView, GAME_VIEW);
        this.container.add(addGameView, ADD_GAME_VIEW);
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the game manager
    public void addGame(String gameName, String playerOneName, String playerTwoName) {
        gameManager.addGame(gameName, playerOneName, playerTwoName);
        this.updateGamesSection();
    }

    // MODIFIES: menuView
    // EFFECTS: updates the games list of the application
    public void updateGamesSection() {
        menuView.updateGames();
    }

    // REQUIRES: must be at least one game in the games list and a current game must
    //           be selected
    // MODIFIES: this
    // EFFECTS: removes current game in the game list
    public void removeCurrentGame() {
        int index = gameManager.getGamesList().indexOf(gameManager.getCurrentGame());
        gameManager.removeGame(index);
        menuView.updateGames();
    }

    // MODIFIES: this
    // EFFECTS: plays the current game
    public void playCurrentGame() {
        if (isCurrentGameSelected()) {
            gameView.updateGameView();
            showGame();
        } else {
            JOptionPane.showMessageDialog(mFrame, "Please select a game to play",
                    "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    // EFFECTS: gets the current game's name, else returns empty string if
    //          no current game
    public String getCurrentGameName() {
        if (isCurrentGameSelected()) {
            return gameManager.getCurrentGame().getGameName();
        } else {
            return "";
        }
    }

    // EFFECTS: gets the current player's name, else returns empty string if no current player
    public String getCurrentPlayerName() {
        if (isCurrentGameSelected()) {
            return gameManager.getCurrentGame().getCurrentPlayer().getName();
        } else {
            return "";
        }
    }

    // EFFECTS: gets gameboard of current game, else returns a generic gameboard
    public Gameboard getGameboard() {
        if (isCurrentGameSelected()) {
            return gameManager.getCurrentGame().getGameboard();
        } else {
            return new Gameboard();
        }
    }

    // EFFECTS: return true if current game selected, else false
    private boolean isCurrentGameSelected() {
        return (gameManager.getCurrentGame() != null);
    }

    // MODIFIES: this
    // EFFECTS: registers selected piece as current piece if selected piece is
    //          an ally piece, else if a selected piece is registered, then moves
    //          piece to selected piece, else do nothing
    public void runPlaySequence(Placeable placeable) {
        Player currentPlayer = gameManager.getCurrentGame().getCurrentPlayer();
        Gameboard currentGameboard = gameManager.getCurrentGame().getGameboard();
        Game currentGame = gameManager.getCurrentGame();
        if (placeable.getOwner() == currentPlayer.getPlayingOrder()) {
            this.currentPiece = currentGameboard.getBoard().get(placeable.getPosY()).get(placeable.getPosX());
        } else if (this.currentPiece != null && placeable.getOwner() != currentPlayer.getPlayingOrder()) {
            List<Position> possiblePositions = this.currentPiece.generatePossiblePositions(currentGameboard);
            Position potentialPos = new Position(placeable.getPosY(), placeable.getPosX());
            if (possiblePositions.contains(potentialPos)) {
                currentPlayer.movePiece(this.currentPiece,
                        new Position(placeable.getPosY(), placeable.getPosX()), currentGameboard);
                currentGame.changeTurns();
                this.currentPiece = null;
                updateGameStatus();
                gameView.updateGameView();
                gameView.repaint();
                gameView.revalidate();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if the player has lost due to losing their king
    public void updateGameStatus() {
        Player currentPlayer = gameManager.getCurrentGame().getCurrentPlayer();
        Gameboard currentGameboard = gameManager.getCurrentGame().getGameboard();
        Game currentGame = gameManager.getCurrentGame();
        currentPlayer.checkForKing(currentGameboard);
        currentGame.updateGameStatus();
        if (currentGame.getGameStatus()) {
            endGame(currentGame.getWinnerName());
        }
    }

    // MODIFIES: this
    // EFFECTS: forfeits the game
    public void forfeitGame() {
        Player currentPlayer = gameManager.getCurrentGame().getCurrentPlayer();
        Game currentGame = gameManager.getCurrentGame();
        currentPlayer.forfeit();
        currentGame.updateGameStatus();
        endGame(currentGame.getWinnerName());
    }

    // MODIFIES: this
    // EFFECTS: ends the game, displaying message and taking user back to main
    //          menu
    private void endGame(String winnerName) {
        JOptionPane.showMessageDialog(container, "The winner is "
                + winnerName + "!");
        removeCurrentGame();
        showMenu();
    }

    // EFFECTS: prints the events, then exits the application
    public void quitApplication() {
        for (Event next: el) {
            System.out.println(next.toString());
        }
        System.exit(0);
    }
}
