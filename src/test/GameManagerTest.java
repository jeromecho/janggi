import model.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {
    GameManager gameManager;
    GameManager manyGamesGameManager;

    @BeforeEach
    public void setUp() {
        gameManager = new GameManager();
        manyGamesGameManager = new GameManager();
        manyGamesGameManager.addGame("A", "aa", "bb");
        manyGamesGameManager.addGame("B", "bb", "bb");
        manyGamesGameManager.addGame("C", "cc", "dd");
    }

    @Test
    public void testConstructor() {
        gameManager = new GameManager();
        assertEquals(0, gameManager.getGamesList().size());
        assertEquals(null, gameManager.getCurrentGame());
    }

    @Test
    public void testAddGameOnce() {
        gameManager.addGame("A", "a", "b");

        assertEquals(1, gameManager.getGamesList().size());
        assertEquals("A", gameManager.getGamesList().get(0).getGameName());
        assertEquals("a", gameManager.getGamesList().get(0).getPlayerOne().getName());
        assertEquals("b", gameManager.getGamesList().get(0).getPlayerTwo().getName());
    }

    @Test
    public void testAddGameMultipleTimes() {
        gameManager.addGame("A", "a", "b");
        gameManager.addGame("B", "b", "c");

        assertEquals(2, gameManager.getGamesList().size());
        assertEquals("A", gameManager.getGamesList().get(0).getGameName());
        assertEquals("a", gameManager.getGamesList().get(0).getPlayerOne().getName());
        assertEquals("b", gameManager.getGamesList().get(0).getPlayerTwo().getName());
        assertEquals("B", gameManager.getGamesList().get(1).getGameName());
        assertEquals("b", gameManager.getGamesList().get(1).getPlayerOne().getName());
        assertEquals("c", gameManager.getGamesList().get(1).getPlayerTwo().getName());
    }

    @Test
    public void testRemoveGameUntilNoGames() {
        gameManager.addGame("Regional Tourney", "Chris", "Joe");
        gameManager.removeGame(0);
        assertEquals(0, gameManager.getGamesList().size());
    }

    @Test
    public void testRemoveGameOnlyOneGame() {
        manyGamesGameManager.removeGame(0);
        assertEquals(2, manyGamesGameManager.getGamesList().size());
        assertEquals("B", manyGamesGameManager.getGamesList().get(0).getGameName());
        assertEquals("C", manyGamesGameManager.getGamesList().get(1).getGameName());
    }

    @Test
    public void testRemoveGameAnotherGame() {
        manyGamesGameManager.removeGame(1);
        assertEquals(2, manyGamesGameManager.getGamesList().size());
        assertEquals("A", manyGamesGameManager.getGamesList().get(0).getGameName());
        assertEquals("C", manyGamesGameManager.getGamesList().get(1).getGameName());
    }

    @Test
    public void testRemoveGameMutltipleTimes() {
        manyGamesGameManager.removeGame(0);
        manyGamesGameManager.removeGame(0);
        assertEquals(1, manyGamesGameManager.getGamesList().size());
        assertEquals("C", manyGamesGameManager.getGamesList().get(0).getGameName());
    }

    @Test
    public void testChooseCurrentGame() {
        manyGamesGameManager.chooseCurrentGame(1);
        assertEquals("B", manyGamesGameManager.getCurrentGame().getGameName());
    }

    @Test
    public void testChooseCurrentGameWithAnotherGame() {
        manyGamesGameManager.chooseCurrentGame(2);
        assertEquals("C", manyGamesGameManager.getCurrentGame().getGameName());
    }

    @Test
    public void testToJsonNoGames() {
        // TODO
    }

    @Test
    public void testToJsonWithGames() {
        // TODO
    }
}
