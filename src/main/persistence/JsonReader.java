package persistence;

import model.Game;
import model.GameManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
 Represents the reader for reading the janggi game manager from file
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs a JSON reader that reads from
    //          a given source
    public JsonReader(String src) {
        this.source = src;
    }

    // EFFECTS: constructs a game manager based on data
    //          stored as JSON
    public GameManager loadGameManager() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return reconstructGameManager(jsonObject);
    }

    // EFFECTS: reconstruct game manager based on given
    //          JSON object
    private GameManager reconstructGameManager(JSONObject jsonObject) {
        GameManager gameManager = new GameManager();
        List<Game> gamesList = reconstructGames(jsonObject.getJSONArray("games"));
        for (Game game: gamesList) {
            gameManager.addGame(game.getGameName(), game.getPlayerOne().getName(),
                    game.getPlayerTwo().getName());
        }

        return gameManager;
    }

    // EFFECTS: reconstructs a list of games from the given JSON array
    private List<Game> reconstructGames(JSONArray jsonArray) {
        String name;
        String playerOneName;
        String playerTwoName;
        List<Game> games = new ArrayList<>();

        for (Object gameObject: jsonArray) {
            JSONObject game = (JSONObject) gameObject;
            name = game.getString("name");
            playerOneName = game.getString("playerOneName");
            playerTwoName = game.getString("playerTwoName");
            games.add(new Game(name, playerOneName, playerTwoName));
        }

        return games;
    }

    // EFFECTS: reads source file as a string then returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public String getSource() {
        return source;
    }
}

/*
 structure inspiration for persistence package: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter
 */
