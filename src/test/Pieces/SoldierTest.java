package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.King;
import model.pieces.Piece;
import model.pieces.Soldier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierTest {

    Soldier movingSoldier;
    Soldier stationarySoldier;
    Soldier soldierTopRight;
    Soldier soldierTopLeft;
    Soldier differentOwnerSoldier;
    Soldier soldierBottomLeft;
    Soldier soldierBottomRight;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Gameboard topLeftGameboard;
    Gameboard topRightGameboard;
    Gameboard differentOwnerGameboard;
    Player player = new Player("Bob", Piece.PLAYER_1);
    List<Position> expectedPositionsEnemyAndSpacePositions = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        movingSoldier = new Soldier(4, 2, Piece.PLAYER_1);
        stationarySoldier = new Soldier(6, 0, Piece.PLAYER_1);

        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();

        Piece blockingAllyOne = new Soldier(5, 0, Piece.PLAYER_1);
        Position blockingAllyPositionOne = new Position(5, 0);
        Piece blockingAllyTwo = new Soldier(7, 0, Piece.PLAYER_1);
        Position blockingAllyPositionTwo = new Position(7, 0);
        Piece blockingAllyThree = new Soldier(6, 1, Piece.PLAYER_1);
        Position blockingAllyPositionThree = new Position(6, 1);

        Position stationarySoldierPosition = new Position(6, 0);
        player.movePiece(stationarySoldier, stationarySoldierPosition, gameboard);
        player.movePiece(blockingAllyOne, blockingAllyPositionOne, gameboard);
        player.movePiece(blockingAllyTwo, blockingAllyPositionTwo, gameboard);
        player.movePiece(blockingAllyThree, blockingAllyPositionThree, gameboard);

        Position newSoldierPosition = new Position(4, 2);
        player.movePiece(movingSoldier,  newSoldierPosition, anotherGameboard);
    }

    @BeforeEach
    public void setUpDifferentPlayerOwnerCase() {
        differentOwnerSoldier = new Soldier(3, 2, Piece.PLAYER_2);
        differentOwnerGameboard = new Gameboard();
        player.movePiece(differentOwnerSoldier, new Position(3, 2), differentOwnerGameboard);
    }

    @BeforeEach
    public void setUpForGameboardBoundaryCases() {
        topLeftGameboard = new Gameboard();
        topRightGameboard = new Gameboard();
        soldierTopLeft = new Soldier(0, 0, Piece.PLAYER_1);
        soldierTopRight = new Soldier(0, 8, Piece.PLAYER_1);
        soldierBottomLeft = new Soldier(9, 0, Piece.PLAYER_2);
        soldierBottomRight = new Soldier(9, 8, Piece.PLAYER_2);
        player.movePiece(soldierTopLeft, new Position(0, 0), topLeftGameboard);
        player.movePiece(soldierTopRight, new Position(0, 8), topRightGameboard);
        player.movePiece(soldierBottomLeft, new Position(9, 0), gameboard);
        player.movePiece(soldierBottomRight, new Position(9, 8), gameboard);
    }

    @BeforeEach
    public void setUpExpectedPositionsEmptySpacesAndEnemy() {
        Position PositionOne = new Position(3, 2);
        Position PositionTwo = new Position(4, 1);
        Position PositionThree = new Position(4, 3);
        expectedPositionsEnemyAndSpacePositions.add(PositionOne);
        expectedPositionsEnemyAndSpacePositions.add(PositionTwo);
        expectedPositionsEnemyAndSpacePositions.add(PositionThree);
    }

    @Test
    public void testConstructor() {
        movingSoldier = new Soldier(1, 1, Piece.PLAYER_1);
        assertEquals(1, movingSoldier.getPosition().getPosY());
        assertEquals(1, movingSoldier.getPosition().getPosX());
        assertEquals("soldier", movingSoldier.getName());
        assertEquals(Piece.PLAYER_1, movingSoldier.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        movingSoldier = new Soldier(2, 3, Piece.PLAYER_2);
        assertEquals(2, movingSoldier.getPosition().getPosY());
        assertEquals(3, movingSoldier.getPosition().getPosX());
        assertEquals("soldier", movingSoldier.getName());
        assertEquals(Piece.PLAYER_2, movingSoldier.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositions() {
        List<Position> possiblePositions = stationarySoldier.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsEmptySpacesAndEnemyPositions() {
        List<Position> possiblePositions = movingSoldier.generatePossiblePositions(anotherGameboard);

        for (int i = 0; i < expectedPositionsEnemyAndSpacePositions.size(); i++) {
            Position currentExpectedPosition = expectedPositionsEnemyAndSpacePositions.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
        assertEquals(expectedPositionsEnemyAndSpacePositions.size(), possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsTopLeft() {
        List<Position> possiblePositions = soldierTopLeft.generatePossiblePositions(topLeftGameboard);
        assertEquals(0, possiblePositions.get(0).getPosY());
        assertEquals(1, possiblePositions.get(0).getPosX());
        assertEquals(1, possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsTopRight() {
        List<Position> possiblePositions = soldierTopRight.generatePossiblePositions(topRightGameboard);
        assertEquals(0, possiblePositions.get(0).getPosY());
        assertEquals(7, possiblePositions.get(0).getPosX());
        assertEquals(1, possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsBottomLeft() {
        List<Position> possiblePositions = soldierBottomLeft.generatePossiblePositions(topLeftGameboard);
        assertEquals(1, possiblePositions.size());
        assertEquals(9, possiblePositions.get(0).getPosY());
        assertEquals(1, possiblePositions.get(0).getPosX());
    }

    @Test
    public void testGeneratePossiblePositionsBottomRight() {
        List<Position> possiblePositions = soldierBottomRight.generatePossiblePositions(topRightGameboard);
        assertEquals(1, possiblePositions.size());
        assertEquals(9, possiblePositions.get(0).getPosY());
        assertEquals(7, possiblePositions.get(0).getPosX());
    }

    @Test
    public void testGeneratePossiblePositions() {
        List<Position> possiblePositions = differentOwnerSoldier.generatePossiblePositions(differentOwnerGameboard);

        assertEquals(4, possiblePositions.get(0).getPosY());
        assertEquals(2, possiblePositions.get(0).getPosX());
        assertEquals(3, possiblePositions.get(1).getPosY());
        assertEquals(1, possiblePositions.get(1).getPosX());
        assertEquals(3, possiblePositions.get(2).getPosY());
        assertEquals(3, possiblePositions.get(2).getPosX());
        assertEquals(3, possiblePositions.size());
    }
}
