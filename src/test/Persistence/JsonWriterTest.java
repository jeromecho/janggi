package Persistence;

import model.GameManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonWriterTest {
    private static final String EMPTY_PATH = "./data/json_write_empty.json";
    private static final String NON_EMPTY_PATH = "./data/json_write_non_empty.json";
    private static final String NON_EXISTENT_PATH = "./data/non_existent.json";
    private static final String MANY_TIMES_PATH = "./data/json_write_many_times.json";
    private static final String MANY_GAMES_PATH = "./data/json_write_many_games.json";
    private static final String A_GAME_PATH = "./data/json_write_a_game.json";

    static JsonWriter jsonWriter;
    static JsonReader jsonReader;
    static GameManager gameManagerEmpty;
    static GameManager gameManagerOneGame;
    static GameManager gameManagerManyGames;

    @BeforeAll
    public static void setUp() {
        gameManagerEmpty = new GameManager();

        gameManagerOneGame = new GameManager();
        gameManagerOneGame.addGame("game1", "sally", "joe");

        gameManagerManyGames = new GameManager();
        gameManagerManyGames.addGame("chu-han", "chu", "han");
        gameManagerManyGames.addGame("western-campaign",
                "genghis", "khwarazmian-empire");
    }

    @Test
    public void testConstructor() {
        jsonWriter = new JsonWriter(EMPTY_PATH);
        assertEquals(EMPTY_PATH, jsonWriter.getPath());
    }

    @Test
    public void testConstructorDifferentPath() {
        jsonWriter = new JsonWriter(NON_EMPTY_PATH);
        assertEquals(NON_EMPTY_PATH, jsonWriter.getPath());
    }

    @Test
    public void testWriteGameManagerNonExistentPath() {
        jsonWriter = new JsonWriter(NON_EXISTENT_PATH);
        jsonReader = new JsonReader(NON_EXISTENT_PATH);
        try {
            jsonWriter.writeGameManager(gameManagerEmpty);
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(0, gameManager.getGamesList().size());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testWriteGameManagerNoGames() {
        jsonWriter = new JsonWriter(NON_EMPTY_PATH);
        jsonReader = new JsonReader(NON_EMPTY_PATH);
        try {
            jsonWriter.writeGameManager(gameManagerEmpty);
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(0, gameManager.getGamesList().size());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testWriteGameManagerAGame() {
        jsonWriter = new JsonWriter(A_GAME_PATH);
        jsonReader = new JsonReader(A_GAME_PATH);
        try {
            jsonWriter.writeGameManager(gameManagerOneGame);
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(1, gameManager.getGamesList().size());
            assertEquals("game1", gameManager.getGamesList().get(0).getGameName());
            assertEquals("sally", gameManager.getGamesList().get(0).getPlayerOne().getName());
            assertEquals("joe", gameManager.getGamesList().get(0).getPlayerTwo().getName());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testWriteGameManagerManyGames() {
        jsonWriter = new JsonWriter(MANY_GAMES_PATH);
        jsonReader = new JsonReader(MANY_GAMES_PATH);
        try {
            jsonWriter.writeGameManager(gameManagerManyGames);
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(2, gameManager.getGamesList().size());
            assertEquals("chu-han", gameManager.getGamesList().get(0).getGameName());
            assertEquals("chu", gameManager.getGamesList().get(0).getPlayerOne().getName());
            assertEquals("han", gameManager.getGamesList().get(0).getPlayerTwo().getName());
            assertEquals("western-campaign", gameManager.getGamesList().get(1).getGameName());
            assertEquals("genghis", gameManager.getGamesList().get(1).getPlayerOne().getName());
            assertEquals("khwarazmian-empire", gameManager.getGamesList().get(1).getPlayerTwo().getName());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

    @Test
    public void testWriteGameManagerManyTimes() {
        jsonWriter = new JsonWriter(MANY_TIMES_PATH);
        jsonReader = new JsonReader(MANY_TIMES_PATH);
        try {
            jsonWriter.writeGameManager(gameManagerEmpty);
            jsonWriter.writeGameManager(gameManagerOneGame);
            jsonWriter.writeGameManager(gameManagerManyGames);
            GameManager gameManager = jsonReader.loadGameManager();
            assertEquals(2, gameManager.getGamesList().size());
            assertEquals("chu-han", gameManager.getGamesList().get(0).getGameName());
            assertEquals("chu", gameManager.getGamesList().get(0).getPlayerOne().getName());
            assertEquals("han", gameManager.getGamesList().get(0).getPlayerTwo().getName());
            assertEquals("western-campaign", gameManager.getGamesList().get(1).getGameName());
            assertEquals("genghis", gameManager.getGamesList().get(1).getPlayerOne().getName());
            assertEquals("khwarazmian-empire", gameManager.getGamesList().get(1).getPlayerTwo().getName());
        } catch (FileNotFoundException e) {
            fail("Unexpected FileNotFoundException");
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
    }

}
