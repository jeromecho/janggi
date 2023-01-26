package model;

import model.pieces.*;
import org.json.JSONObject;
import persistence.Writable;

/*
 * Represents a janggi game with two players, a gameboard
   with several pieces (Chariot, Knight, Elephant, Guard, King, Cannon, Soldier),
   that keeps track of which player's turn it is and allows a player to surrender
 */
public class Game implements Writable {
    private final Gameboard gameboard;
    private final String gameName;
    private String winnerName;
    private Player currentPlayer;
    private final Player playerOne;
    private final Player playerTwo;
    private boolean isGameOver;

    // EFFECTS: constructs a new game with two players, a game board,
    //          with current player equal to player one and the game not being over
    public Game(String name, String playerOneName, String playerTwoName) {
        gameName = name;
        playerOne = new Player(playerOneName, Piece.PLAYER_1);
        playerTwo = new Player(playerTwoName, Piece.PLAYER_2);
        gameboard = new Gameboard();
        isGameOver = false;
        currentPlayer = playerOne;
    }

    // EFFECTS: returns a JSON object of the game
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", gameName);
        jsonObject.put("playerOneName", playerOne.getName());
        jsonObject.put("playerTwoName", playerTwo.getName());
        return jsonObject;
    }

    // MODIFIES: this
    // EFFECTS: changes the current player to the other player
    public void changeTurns() {
        if (currentPlayer.getPlayingOrder() == Piece.PLAYER_1) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }

    // REQUIRES: at most only one of the players can be forfeited at any given time
    // EFFECTS: sets the current game being over to true if either of the players have
    //          forfeited and sets the winner name to be the name of the winner
    public void updateGameStatus() {
        if (playerOne.getStatus()) {
            isGameOver = true;
            winnerName = playerTwo.getName();
        } else if (playerTwo.getStatus()) {
            isGameOver = true;
            winnerName = playerOne.getName();
        }
    }

    // EFFECTS: returns true if the game is over, false otherwise
    public boolean getGameStatus() {
        return isGameOver;
    }

    // REQUIRES: the game must have ended before this function can be called
    // EFFECTS: returns the name of the winner
    public String getWinnerName() {
        return winnerName;
    }

    // EFFECTS: gets game name
    public String getGameName() {
        return gameName;
    }

    // EFFECTS: returns the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    // EFFECTS: returns the gameboard
    public Gameboard getGameboard() {
        return gameboard;
    }

    // EFFECTS: returns the first player
    public Player getPlayerOne() {
        return playerOne;
    }

    // EFFECTS: returns the second player
    public Player getPlayerTwo() {
        return playerTwo;
    }
}
