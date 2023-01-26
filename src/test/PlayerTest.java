import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.Piece;
import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player playerOne;
    Player playerTwo;
    Gameboard gameboard;
    Gameboard gameboardWithSomePiecesLost;
    Gameboard gameboardWithoutKing;

    @BeforeEach
    public void setUp() {
        playerOne = new Player("bob", Piece.PLAYER_1);
        playerTwo = new Player("jin", Piece.PLAYER_2);
        gameboard = new Gameboard();

        gameboardWithSomePiecesLost = new Gameboard();
        Piece enemyPiece = new Soldier(1, 0, Piece.PLAYER_2);
        Position newEnemyPiecePosition = new Position(6, 0);
        playerTwo.movePiece(enemyPiece, newEnemyPiecePosition, gameboardWithSomePiecesLost);

        gameboardWithoutKing = new Gameboard();
        Piece enemyPieceTwo = new Soldier(1, 0, Piece.PLAYER_2);
        Position newEnemyPiecePositionTwo = new Position(8, 4);
        playerTwo.movePiece(enemyPieceTwo, newEnemyPiecePositionTwo, gameboardWithoutKing);
    }

    @Test
    public void testConstructor() {
        playerOne = new Player("Matthias", Piece.PLAYER_1);
        assertEquals("Matthias", playerOne.getName());
        assertEquals(Piece.PLAYER_1, playerOne.getPlayingOrder());
        assertFalse(playerOne.getStatus());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        playerTwo = new Player("Balk", Piece.PLAYER_2);
        assertEquals("Balk", playerTwo.getName());
        assertEquals(Piece.PLAYER_2, playerTwo.getPlayingOrder());
        assertFalse(playerOne.getStatus());
    }

    @Test
    public void testGetPiecesInitialSetup() {
        List<Piece> playerPieces = playerOne.getPieces(gameboard);
        assertEquals(16 ,playerPieces.size());
        assertEquals("soldier", playerPieces.get(0).getName());
        assertEquals("soldier", playerPieces.get(1).getName());
        assertEquals("soldier", playerPieces.get(2).getName());
        assertEquals("soldier", playerPieces.get(3).getName());
        assertEquals("soldier", playerPieces.get(4).getName());
        assertEquals("cannon", playerPieces.get(5).getName());
        assertEquals("cannon", playerPieces.get(6).getName());
        assertEquals("king", playerPieces.get(7).getName());
        assertEquals("chariot", playerPieces.get(8).getName());
        assertEquals("knight", playerPieces.get(9).getName());
        assertEquals("elephant", playerPieces.get(10).getName());
        assertEquals("guard", playerPieces.get(11).getName());
        assertEquals("guard", playerPieces.get(12).getName());
        assertEquals("elephant", playerPieces.get(13).getName());
        assertEquals("knight", playerPieces.get(14).getName());
        assertEquals("chariot", playerPieces.get(15).getName());
    }

    @Test
    public void testGetPiecesWithoutAllPieces() {
        List<Piece> playerPieces = playerOne.getPieces(gameboardWithSomePiecesLost);
        assertEquals("soldier", playerPieces.get(0).getName());
        assertEquals("soldier", playerPieces.get(1).getName());
        assertEquals("soldier", playerPieces.get(2).getName());
        assertEquals("soldier", playerPieces.get(3).getName());
        assertEquals("cannon", playerPieces.get(4).getName());
        assertEquals("cannon", playerPieces.get(5).getName());
        assertEquals("king", playerPieces.get(6).getName());
        assertEquals("chariot", playerPieces.get(7).getName());
        assertEquals("knight", playerPieces.get(8).getName());
        assertEquals("elephant", playerPieces.get(9).getName());
        assertEquals("guard", playerPieces.get(10).getName());
        assertEquals("guard", playerPieces.get(11).getName());
        assertEquals("elephant", playerPieces.get(12).getName());
        assertEquals("knight", playerPieces.get(13).getName());
        assertEquals("chariot", playerPieces.get(14).getName());
        assertEquals(15, playerPieces.size());
    }

    @Test
    public void testMovePieceToSpace() {
        Piece dummyPiece = new Soldier(0, 1, Piece.PLAYER_2);
        Position newDummyPosition = new Position(1,1);

        playerTwo.movePiece(dummyPiece, newDummyPosition, gameboard);

        assertEquals("space", gameboard.getBoard().get(1).get(0).getName());
        assertEquals("soldier", gameboard.getBoard().get(1).get(1).getName());
        assertEquals(1, dummyPiece.getPosition().getPosY());
        assertEquals(1, dummyPiece.getPosition().getPosX());
    }

    @Test
    public void testMovePieceToEnemy() {
        Piece dummyPiece = new Soldier(5, 0, Piece.PLAYER_2);
        Position newDummyPosition = new Position(6,0);

        playerTwo.movePiece(dummyPiece, newDummyPosition, gameboard);

        assertEquals("space", gameboard.getBoard().get(5).get(0).getName());
        assertEquals("soldier", gameboard.getBoard().get(6).get(0).getName());
        assertEquals(Piece.PLAYER_2, gameboard.getBoard().get(6).get(0).getOwner());
        assertEquals(15, playerOne.getPieces(gameboard).size());
        assertEquals(6, dummyPiece.getPosition().getPosY());
        assertEquals(0, dummyPiece.getPosition().getPosX());
    }

    @Test
    public void testForfeitIfNotLost() {
        playerOne.forfeit();
        assertTrue(playerOne.getStatus());
    }

    @Test
    public void testForfeitIfAlreadyLost() {
        playerOne.forfeit();
        assertTrue(playerOne.getStatus());
        playerOne.forfeit();
        assertTrue(playerOne.getStatus());
    }

    @Test
    public void testCheckForKingIfKing() {
        playerOne.checkForKing(gameboard);
        assertEquals(false, playerOne.getStatus());
    }

    @Test
    public void testCheckForKingIfNoKing() {
        playerOne.checkForKing(gameboardWithoutKing);
        assertEquals(true, playerOne.getStatus());
    }
}