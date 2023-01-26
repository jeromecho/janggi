package model;

import model.events.Event;
import model.events.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a game manager with a list of games
 */
public class GameManager implements Writable {
    private List<Game> gamesList;
    private Game currentGame;
    private EventLog el;

    // EFFECTS: constructs a game manager initialized with an
    //          empty list of games, and the current game
    //          set to nothing
    public GameManager() {
        gamesList = new ArrayList<>();
        currentGame = null;
        el = EventLog.getInstance();
    }

    @Override
    // EFFECTS: returns a JSON object of the given
    //          java object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("games", gamesListToJson());
        return json;
    }

    // EFFECTS: returns a JSON array of the games list
    private JSONArray gamesListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Game game: gamesList) {
            jsonArray.put(game.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: creates a game with given game name, player one name, and player two bame,
    //          and adds a game to the list of games - logging the event
    public void addGame(String gameName, String playerOneName, String playerTwoName) {
        Game game = new Game(gameName, playerOneName, playerTwoName);
        gamesList.add(game);
        el.logEvent(new Event("added game " + gameName));
    }

    // REQUIRES: given index is < length of list of games
    // MODIFIES: this
    // EFFECTS: removes a given game from the list of games, and logs that
    //          event
    public void removeGame(int index) {
        el.logEvent(new Event("Removed game " + gamesList.get(index).getGameName()));
        gamesList.remove(index);
    }

    // EFFECTS: returns the list of games
    public List<Game> getGamesList() {
        return gamesList;
    }

    // REQUIRES: given game index must be < length of games list
    // EFFECTS: set the currentGame
    public void chooseCurrentGame(int gameIndex) {
        currentGame = gamesList.get(gameIndex);
    }

    // EFFECTS: returns the current game
    public Game getCurrentGame() {
        return currentGame;
    }
}

/*
 Citations: structure and code inspiration from:

 */

