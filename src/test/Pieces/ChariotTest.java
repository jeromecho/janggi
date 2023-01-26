package Pieces;

import model.Gameboard;
import model.Player;
import model.Position;
import model.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChariotTest {
    Chariot movingChariot;
    Chariot stationaryChariot;
    Chariot surroundedByAlliesChariot;
    Chariot surroundedByEnemiesChariot;
    Chariot surroundedBySpaceChariot;
    Chariot differentOwnerChariot;
    Gameboard gameboard;
    Gameboard anotherGameboard;
    Gameboard differentOwnerGameboard;
    Gameboard surroundedByAlliesChariotGameboard;
    Gameboard surroundedByEnemiesChariotGameboard;
    Gameboard surroundedBySpaceChariotGameboard;
    Player player = new Player("Bob", Piece.PLAYER_1);
    List<Position> expectedPositionsAnotherGameboardSetup = new ArrayList<>();
    List<Position> expectedPositionsSurroundedByEnemiesSetup = new ArrayList<>();
    List<Position> expectedPositionsSurroundedBySpaceSetup = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        movingChariot = new Chariot(4, 4, Piece.PLAYER_1);
        stationaryChariot = new Chariot(9, 0, Piece.PLAYER_1);
        differentOwnerChariot = new Chariot(0, 0, Piece.PLAYER_2);
        Piece blockingAlly = new Soldier(1, 1, Piece.PLAYER_1);

        gameboard = new Gameboard();
        anotherGameboard = new Gameboard();
        differentOwnerGameboard = new Gameboard();
        player.movePiece(differentOwnerChariot, new Position(0, 0), differentOwnerGameboard);

        Position stationaryChariotPosition = new Position(9, 0);
        Position blockingPosition = new Position(8, 0);
        player.movePiece(stationaryChariot, stationaryChariotPosition, gameboard);
        player.movePiece(blockingAlly, blockingPosition, gameboard);

        Position newChariotPosition = new Position(4, 4);
        player.movePiece(movingChariot, newChariotPosition, anotherGameboard);
    }

    @BeforeEach
    public void setUpSurroundedByEnemies() {
        surroundedByEnemiesChariot = new Chariot(3, 1, Piece.PLAYER_1);
        surroundedByEnemiesChariotGameboard = new Gameboard();
        Piece blockingEnemyOne = new Soldier(2,1, Piece.PLAYER_2);
        Piece blockingEnemyTwo = new Soldier(4,1, Piece.PLAYER_2);
        player.movePiece(surroundedByEnemiesChariot, new Position(3, 1),
                surroundedByEnemiesChariotGameboard);
        player.movePiece(blockingEnemyOne, new Position(2, 1),
                surroundedByEnemiesChariotGameboard);
        player.movePiece(blockingEnemyTwo, new Position(4, 1),
                surroundedByEnemiesChariotGameboard);
    }

    @BeforeEach
    public void setUpSurroundedBySpace() {
        surroundedBySpaceChariot = new Chariot(4, 5, Piece.PLAYER_1);
        surroundedBySpaceChariotGameboard = new Gameboard();
        player.movePiece(surroundedBySpaceChariot, new Position(4, 5),
                surroundedBySpaceChariotGameboard);
    }

    @BeforeEach
    public void setUpExpectedPositionsAnotherGameboard() {
        Position PositionOne = new Position(3, 4);
        Position PositionTwo = new Position(4, 5);
        Position PositionThree = new Position(4, 6);
        Position PositionFour = new Position(4, 7);
        Position PositionFive = new Position(4,8);
        Position PositionSix = new Position(5, 4);
        Position PositionSeven = new Position(4, 3);
        Position PositionEight = new Position(4 ,2);
        Position PositionNine = new Position(4, 1);
        Position PositionTen = new Position(4, 0);
        expectedPositionsAnotherGameboardSetup.add(PositionOne);
        expectedPositionsAnotherGameboardSetup.add(PositionTwo);
        expectedPositionsAnotherGameboardSetup.add(PositionThree);
        expectedPositionsAnotherGameboardSetup.add(PositionFour);
        expectedPositionsAnotherGameboardSetup.add(PositionFive);
        expectedPositionsAnotherGameboardSetup.add(PositionSix);
        expectedPositionsAnotherGameboardSetup.add(PositionSeven);
        expectedPositionsAnotherGameboardSetup.add(PositionEight);
        expectedPositionsAnotherGameboardSetup.add(PositionNine);
        expectedPositionsAnotherGameboardSetup.add(PositionTen);
    }

    @BeforeEach
    public void setUpExpectedPositionsSurroundedByEnemiesGameboard() {
        Position PositionOne = new Position(2, 1);
        Position PositionTwo = new Position(3, 2);
        Position PositionThree = new Position(4, 1);
        Position PositionFour = new Position(3, 0);
        expectedPositionsSurroundedByEnemiesSetup.add(PositionOne);
        expectedPositionsSurroundedByEnemiesSetup.add(PositionTwo);
        expectedPositionsSurroundedByEnemiesSetup.add(PositionThree);
        expectedPositionsSurroundedByEnemiesSetup.add(PositionFour);
    }

    @BeforeEach
    public void setUpExpectedPositionsSurroundedBySpaceGameboard() {
        Position PositionOne = new Position(3, 5);
        Position PositionTwo = new Position(2, 5);
        Position PositionThree = new Position(1, 5);
        Position PositionFour = new Position(0, 5);
        Position PositionFive = new Position(4, 6);
        Position PositionSix = new Position(4, 7);
        Position PositionSeven = new Position(4, 8);
        Position PositionEight = new Position(5, 5);
        Position PositionNine = new Position(6, 5);
        Position PositionTen = new Position(7, 5);
        Position PositionEleven = new Position(8, 5);
        Position PositionTwelve = new Position(4, 4);
        Position PositionThirteen = new Position(4, 3);
        Position PositionFourteen = new Position(4, 2);
        Position PositionFifteen = new Position(4, 1);
        Position PositionSixteen = new Position(4, 0);

        expectedPositionsSurroundedBySpaceSetup.add(PositionOne);
        expectedPositionsSurroundedBySpaceSetup.add(PositionTwo);
        expectedPositionsSurroundedBySpaceSetup.add(PositionThree);
        expectedPositionsSurroundedBySpaceSetup.add(PositionFour);
        expectedPositionsSurroundedBySpaceSetup.add(PositionFive);
        expectedPositionsSurroundedBySpaceSetup.add(PositionSix);
        expectedPositionsSurroundedBySpaceSetup.add(PositionSeven);
        expectedPositionsSurroundedBySpaceSetup.add(PositionEight);
        expectedPositionsSurroundedBySpaceSetup.add(PositionNine);
        expectedPositionsSurroundedBySpaceSetup.add(PositionTen);
        expectedPositionsSurroundedBySpaceSetup.add(PositionEleven);
        expectedPositionsSurroundedBySpaceSetup.add(PositionTwelve);
        expectedPositionsSurroundedBySpaceSetup.add(PositionThirteen);
        expectedPositionsSurroundedBySpaceSetup.add(PositionFourteen);
        expectedPositionsSurroundedBySpaceSetup.add(PositionFifteen);
        expectedPositionsSurroundedBySpaceSetup.add(PositionSixteen);
    }

    @BeforeEach
    public void setUpSurroundedChariot() {
        surroundedByAlliesChariot = new Chariot(8, 1, Piece.PLAYER_1);
        surroundedByAlliesChariotGameboard = new Gameboard();
        Piece allyPieceOne = new Soldier(8, 0, Piece.PLAYER_1);
        Piece allyPieceTwo = new Soldier(8, 2, Piece.PLAYER_1);
        player.movePiece(surroundedByAlliesChariot, new Position(8, 1), surroundedByAlliesChariotGameboard);
        player.movePiece(allyPieceOne, new Position(8, 0), surroundedByAlliesChariotGameboard);
        player.movePiece(allyPieceTwo, new Position(8, 2), surroundedByAlliesChariotGameboard);
    }

    @Test
    public void testConstructor() {
        movingChariot = new Chariot(1, 1, Piece.PLAYER_1);
        assertEquals(1, movingChariot.getPosition().getPosY());
        assertEquals(1, movingChariot.getPosition().getPosX());
        assertEquals(Piece.PLAYER_1, movingChariot.getOwner());
    }

    @Test
    public void testConstructorWithDifferentInitialValues() {
        movingChariot = new Chariot(2, 3, Piece.PLAYER_2);
        assertEquals(2, movingChariot.getPosition().getPosY());
        assertEquals(3, movingChariot.getPosition().getPosX());
        assertEquals(Piece.PLAYER_2, movingChariot.getOwner());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositions() {
        List<Position> possiblePositions = stationaryChariot.generatePossiblePositions(gameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsNoPossiblePositionsAlliesOnAllSides() {
        List<Position> possiblePositions = surroundedByAlliesChariot
                .generatePossiblePositions(surroundedByAlliesChariotGameboard);
        assertEquals(0, possiblePositions.size());
    }

    @Test
    public void testGeneratePossiblePositionsPossiblePositionsInAllDirections() {
        List<Position> possiblePositions = movingChariot.generatePossiblePositions(anotherGameboard);

        assertEquals(expectedPositionsAnotherGameboardSetup.size(), possiblePositions.size());
        for (int i = 0; i < expectedPositionsAnotherGameboardSetup.size(); i++) {
            Position currentExpectedPosition = expectedPositionsAnotherGameboardSetup.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
    }

    @Test
    public void testGeneratePossiblePositionsSurroundedByEnemies() {
        List<Position> possiblePositions = surroundedByEnemiesChariot
                .generatePossiblePositions(surroundedByEnemiesChariotGameboard);

        assertEquals(expectedPositionsSurroundedByEnemiesSetup.size(), possiblePositions.size());
        for (int i = 0; i < expectedPositionsSurroundedByEnemiesSetup.size(); i++) {
            Position currentExpectedPosition = expectedPositionsSurroundedByEnemiesSetup.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
    }

    @Test
    public void testGeneratePossiblePositionsSurroundedBySpace() {
        List<Position> possiblePositions = surroundedBySpaceChariot
                .generatePossiblePositions(surroundedBySpaceChariotGameboard);

        assertEquals(expectedPositionsSurroundedBySpaceSetup.size(), possiblePositions.size());
        for (int i = 0; i < expectedPositionsSurroundedBySpaceSetup.size(); i++) {
            Position currentExpectedPosition = expectedPositionsSurroundedBySpaceSetup.get(i);
            Position currentActualPosition = possiblePositions.get(i);
            assertEquals(currentExpectedPosition.getPosY(), currentActualPosition.getPosY());
            assertEquals(currentExpectedPosition.getPosX(), currentActualPosition.getPosX());
        }
    }

    @Test
    public void testGeneratePossiblePositionsPossibleWithDifferentOwner() {
        List<Position> possiblePositions = differentOwnerChariot.generatePossiblePositions(differentOwnerGameboard);
        assertEquals(2, possiblePositions.size());
        assertEquals(1, possiblePositions.get(0).getPosY());
        assertEquals(0, possiblePositions.get(0).getPosX());
        assertEquals(2, possiblePositions.get(1).getPosY());
        assertEquals(0, possiblePositions.get(1).getPosX());
    }
}
