package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.Elephant;
import model.pieces.Guard;
import model.pieces.Piece;
import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuardTest {
    Guard movingGuard;
    Guard stationaryGuard;
    Guard otherOwnerGuard;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Player player;
    List<Position> expectedPositionsEnemyAndSpacePositions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        movingGuard = new Guard(9, 5, Piece.PLAYER_1);
        stationaryGuard = new Guard(9, 3, Piece.PLAYER_1);
        otherOwnerGuard = new Guard(0, 3, Piece.PLAYER_2);

        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();
        player = new Player("Bob", Piece.PLAYER_1);

        Piece blockingAllyOne = new Soldier(8, 3, Piece.PLAYER_1);
        Position blockingAllyPositionOne = new Position(8, 3);
        Piece blockingAllyTwo = new Soldier(9, 4, Piece.PLAYER_1);
        Position blockingAllyPositionTwo = new Position(9, 4);

        Position stationaryGuardPosition = new Position(9, 3);
        player.movePiece(stationaryGuard, stationaryGuardPosition, gameboard);
        player.movePiece(blockingAllyOne, blockingAllyPositionOne, gameboard);
        player.movePiece(blockingAllyTwo, blockingAllyPositionTwo, gameboard);

        Position newGuardPosition = new Position(9, 5);
        player.movePiece(movingGuard, newGuardPosition, anotherGameboard);

        player.movePiece(otherOwnerGuard, new Position(0,3), gameboard);
    }

    @BeforeEach
    public void setUpExpectedPositionsEmptySpacesAndEnemy() {
        Position PositionOne = new Position(8, 5);
        Position PositionTwo = new Position(9, 4);
        expectedPositionsEnemyAndSpacePositions.add(PositionOne);
        expectedPositionsEnemyAndSpacePositions.add(PositionTwo);
    }

    @Test
    public void testConstructor() {
        movingGuard = new Guard(1, 1, Piece.PLAYER_1);
        assertEquals(1, movingGuard.getPosition().getPosY());
        assertEquals(1, movingGuard.getPosition().getPosX());
        assertEquals("guard", movingGuard.getName());
        assertEquals(Piece.PLAYER_1, movingGuard.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        movingGuard = new Guard(2, 3, Piece.PLAYER_2);
        assertEquals(2, movingGuard.getPosition().getPosY());
        assertEquals(3, movingGuard.getPosition().getPosX());
        assertEquals("guard", movingGuard.getName());
        assertEquals(Piece.PLAYER_2, movingGuard.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositions() {
        List<Position> possiblePositions = stationaryGuard.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void getTestGeneratePossiblePositionsEmptySpacesAndEnemyPositions() {
        List<Position> possiblePositions = movingGuard.generatePossiblePositions(anotherGameboard);

        for (int i = 0; i < expectedPositionsEnemyAndSpacePositions.size(); i++) {
            Position currentExpectedPosition = expectedPositionsEnemyAndSpacePositions.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
        assertEquals(expectedPositionsEnemyAndSpacePositions.size(), possiblePositions.size());
    }

    @Test
    public void getTestGeneratePossiblePositionsOtherOwner() {
        List<Position> possiblePositions = otherOwnerGuard.generatePossiblePositions(gameboard);
        assertEquals(2, possiblePositions.size());
        assertEquals(0 ,possiblePositions.get(0).getPosY());
        assertEquals(4 ,possiblePositions.get(0).getPosX());
        assertEquals(1 ,possiblePositions.get(1).getPosY());
        assertEquals(3 ,possiblePositions.get(1).getPosX());
    }
}
