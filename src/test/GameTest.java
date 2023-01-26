import model.Game;
import model.Gameboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    Game game;

    @BeforeEach
    public void setUp() {
        game = new Game("Default", "Lee", "Cho");
    }

    @Test
    public void testConstructor() {
        game = new Game("Name", "Bob", "Will");
        assertEquals("Name", game.getGameName());
        assertEquals("Bob", game.getPlayerOne().getName());
        assertEquals("Will", game.getPlayerTwo().getName());
        assertEquals("Bob", game.getCurrentPlayer().getName());
        assertEquals(false, game.getGameStatus());
        assertTrue(game.getGameboard() instanceof Gameboard);
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        game = new Game("Jabba", "Milk", "Toast");
        assertEquals("Jabba", game.getGameName());
        assertEquals("Milk", game.getPlayerOne().getName());
        assertEquals("Toast", game.getPlayerTwo().getName());
        assertEquals("Milk", game.getCurrentPlayer().getName());
        assertEquals(false, game.getGameStatus());
        assertTrue(game.getGameboard() instanceof Gameboard);
    }

    @Test
    public void testChangeTurnsToPlayerTwo() {
        game.changeTurns();
        assertEquals("Cho", game.getCurrentPlayer().getName());
    }

    @Test
    public void testChangeTurnsToBackToPlayerOne() {
        game.changeTurns();
        game.changeTurns();
        assertEquals("Lee", game.getCurrentPlayer().getName());
    }

    @Test
    public void testUpdateGameStatusIfGameNotOver() {
        game.updateGameStatus();
        assertEquals(false, game.getGameStatus());
    }

    @Test
    public void testUpdateGameStatusIfGameIsOver() {
        game.getCurrentPlayer().forfeit();
        game.updateGameStatus();
        assertEquals(true, game.getGameStatus());
        assertEquals(game.getPlayerTwo().getName(), game.getWinnerName());
    }

    @Test
    public void testGetWinnerNameIfPlayerOneForfeits() {
        game.getCurrentPlayer().forfeit();
        game.updateGameStatus();
        assertEquals(game.getPlayerTwo().getName(), game.getWinnerName());
    }

    @Test
    public void testGetWinnerNameIfPlayerTwoForfeits() {
        game.changeTurns();
        game.getCurrentPlayer().forfeit();
        game.updateGameStatus();
        assertEquals(game.getPlayerOne().getName(), game.getWinnerName());
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
