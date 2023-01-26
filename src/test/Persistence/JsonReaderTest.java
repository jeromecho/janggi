package Persistence;

import model.GameManager;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    private static final String PATH = "./data/json_read_test.json";
    private static final String EMPTY_PATH = "./data/json_read_empty_test.json";
    private static final String INVALID_PATH = "./data/invalid_path.json";
    private static final String NO_GAMES_PATH = "./data/json_read_no_games_test.json";
    private static final String MANY_GAMES_PATH = "./data/json_read_many_games_test.json";

    JsonReader jsonReader;

    @Test
    public void testConstructor() {
        jsonReader = new JsonReader(PATH);
        assertEquals(PATH ,jsonReader.getSource());
    }

    @Test
    public void testConstructorDifferentPath() {
        jsonReader = new JsonReader(INVALID_PATH);
        assertEquals(INVALID_PATH ,jsonReader.getSource());
    }

    @Test
    public void testLoadGameManagerInvalidPath() {
        jsonReader = new JsonReader(INVALID_PATH);
        try {
            GameManager gameManager = jsonReader.loadGameManager();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testLoadGameManagerNoDataInFile() {
        jsonReader = new JsonReader(EMPTY_PATH);
        try {
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(0, gameManager.getGamesList().size());
            fail("Expected a JSONException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        } catch (JSONException e) {
            // pass
        }
    }

    @Test
    public void testLoadGameManagerNoGames() {
        jsonReader = new JsonReader(NO_GAMES_PATH);
        try {
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(0, gameManager.getGamesList().size());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testLoadGameManagerAGameInFile() {
        jsonReader = new JsonReader(PATH);
        try {
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(1, gameManager.getGamesList().size());
            assertEquals("game1", gameManager.getGamesList().get(0).getGameName());
            assertEquals("bill", gameManager.getGamesList().get(0).getPlayerOne().getName());
            assertEquals("ash", gameManager.getGamesList().get(0).getPlayerTwo().getName());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testLoadGameManagerManyGamesInFile() {
        jsonReader = new JsonReader(MANY_GAMES_PATH);
        try {
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(2, gameManager.getGamesList().size());
            assertEquals("game1", gameManager.getGamesList().get(0).getGameName());
            assertEquals("bill", gameManager.getGamesList().get(0).getPlayerOne().getName());
            assertEquals("ash", gameManager.getGamesList().get(0).getPlayerTwo().getName());
            assertEquals("game2", gameManager.getGamesList().get(1).getGameName());
            assertEquals("jill", gameManager.getGamesList().get(1).getPlayerOne().getName());
            assertEquals("will", gameManager.getGamesList().get(1).getPlayerTwo().getName());
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }
}
