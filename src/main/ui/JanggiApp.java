package ui;

import model.*;
import model.pieces.Piece;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/*
 * Represents a janggi application that processes inputs from console and that has a
 *  system for managing multiple janggi games
 */
public class JanggiApp {
    private static final String DATA_PATH = "./data/gamemanager.json";

    private Scanner input;
    private GameManager gameManager;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: constructs a janggi app with a game manager, JSON reader/writer, and a
    //          scanner for taking user input
    public JanggiApp() {
        input = new Scanner(System.in);
        gameManager = new GameManager();
        jsonReader = new JsonReader(DATA_PATH);
        jsonWriter = new JsonWriter(DATA_PATH);
    }

    // EFFECTS: processes user commands
    public void runGameManager() {
        String userResponse;

        System.out.println("Welcome! Let's play a game of janggi");
        while (true) {
            System.out.println("You currently have " + gameManager.getGamesList().size() + " games.");
            System.out.println("Here are your options:");
            printOptions(); // ASSDA
            userResponse = input.nextLine();

            while (!(userResponse.equals("a") || userResponse.equals("d") || userResponse.equals("p")
                    || userResponse.equals("q") || userResponse.equals("l") || userResponse.equals("s"))) {
                System.out.println("Here are your ONLY options:");
                printOptions();
                userResponse = input.nextLine();
            }
            executeOnChosenOption(userResponse);
        }
    }

    // REQUIRES: given option must be one of "a", "d", "p", "q", "l", or "s"
    // EFFECTS: either adds, removes, chooses, quits, loads, or saves the game manager
    //          depending on user response
    private void executeOnChosenOption(String userResponse) {
        if (userResponse.equals("a")) {
            addGame();
        } else if (userResponse.equals("d")) {
            removeGame();
        } else if (userResponse.equals("p")) {
            chooseGame();
        } else if (userResponse.equals("l")) {
            loadGameManager();
        } else if (userResponse.equals("s")) {
            saveGameManager();
        } else {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game manager from source file
    private void loadGameManager() {
        try {
            gameManager = jsonReader.loadGameManager();
            System.out.println("Loaded game manager from file");
        } catch (IOException ioError) {
            System.out.println("There was an error loading your file");
            ioError.printStackTrace();
        }
    }

    // EFFECTS: saves game manager to source file
    private void saveGameManager() {
        try {
            jsonWriter.writeGameManager(gameManager);
            System.out.println("Saved game manager to file");
        } catch (IOException ioError) {
            System.out.println("There was an error saving your game manager to file : O");
            ioError.printStackTrace();
        }
    }

    // EFFECTS: takes user input and creates a game
    private void addGame() {
        System.out.println("ADDING A GAME:");
        System.out.println("Please enter a name for your game.");
        String gameName = input.nextLine();
        System.out.println("Please enter a name for Player One");
        String playerOneName = input.nextLine();
        System.out.println("Please enter a name for Player Two");
        String playerTwoName = input.nextLine();

        gameManager.addGame(gameName, playerOneName, playerTwoName);
        System.out.println("Successfully added game - " + gameName);
    }

    // EFFECTS: takes user input and deletes a specific game
    private void removeGame() {
        System.out.println("ADDING A GAME:");
        System.out.println("Remove a game by entering in its index.");
        printGameNamesList();
        String response = input.nextLine();
        Integer humanIndex = Integer.parseInt(response);
        Integer computerIndex = humanIndex - 1;
        gameManager.removeGame(computerIndex);
        System.out.println("Successfully removed game.");
    }

    // EFFECTS: takes user input and plays a game
    private void chooseGame() {
        System.out.println("PLAY A GAME:");
        System.out.println("Play a game by entering in its index.");
        printGameNamesList();
        String response = input.nextLine();
        Integer humanIndex = Integer.parseInt(response);
        Integer computerIndex = humanIndex - 1;
        gameManager.chooseCurrentGame(computerIndex);
        playGame(gameManager.getCurrentGame());
    }

    // EFFECTS: plays the given game
    private void playGame(Game game) {
        Gameboard gameboard = game.getGameboard();

        System.out.println("Currently playing game " + game.getGameName());
        while (true) {
            printGameboard(game);
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println("The current player is " + currentPlayer.getName());
            runForfeitSequence(currentPlayer);

            currentPlayer.checkForKing(gameboard);
            game.updateGameStatus();
            if (game.getGameStatus()) {
                String winner = game.getWinnerName();
                System.out.println("The winner is " + winner);
                cleanUp(game);
                return;
            }
            runPlayerTurn(currentPlayer, gameboard);
            game.changeTurns();
        }
    }

    // EFFECTS: prints a one-indexed list of game names
    private void printGameNamesList() {
        List<Game> gamesList = gameManager.getGamesList();

        for (int i = 0; i < gamesList.size(); i++) {
            Integer humanIndex = i + 1;
            System.out.println(humanIndex + " - " + gamesList.get(i).getGameName());
        }
    }

    // EFFECTS: prints a one-indexed list of moveable pieces owned by a player
    private void printPlayerPieces(List<Piece> pieces) {
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.get(i);
            int humanIndex = i + 1;
            int posY = piece.getPosition().getPosY();
            int posX = piece.getPosition().getPosX();
            System.out.println(humanIndex + ": " + piece.getName() + " - " + "y-pos: " + posY + ", x-pos:" + posX);
        }
    }

    // EFFECTS: prints the next possible positions of a given piece
    private void printPossiblePositions(Piece piece, Gameboard gameboard) {
        List<Position> possiblePositions = piece.generatePossiblePositions(gameboard);

        for (int i = 0; i < possiblePositions.size(); i++) {
            int humanIndex = i + 1;
            Position currentPosition = possiblePositions.get(i);
            System.out.println(humanIndex + ": "
                    + "(y-pos:" + currentPosition.getPosY() + ", x-pos:" + currentPosition.getPosX() + ")");
        }
    }

    // MODIFIES: gameManager
    // EFFECTS: removes a finished game from the list of games
    private void cleanUp(Game game) {
        int index = gameManager.getGamesList().indexOf(game);
        gameManager.removeGame(index);
    }

    // EFFECTS: Prints game manager options available to player
    private void printOptions() {
        System.out.println("Type `a` to add a game");
        System.out.println("Type `d` to delete a game");
        System.out.println("Type `p` to play a game");
        System.out.println("Type `q` to exit the app");
        System.out.println("Type `l` to load games from file");
        System.out.println("Type `s` to save games to file");
    }

    // EFFECTS: run forfeit sequence - asking player if they want to forfeit
    private void runForfeitSequence(Player currentPlayer) {
        String response;
        while (true) {
            System.out.println("Would you like to forfeit? - `y` or `n`");
            response = input.nextLine();

            if (response.equals("y")) {
                currentPlayer.forfeit();
                break;
            } else if (response.equals("n")) {
                break;
            } else {
                continue;
            }
        }
    }

    // EFFECTS: runs a player's turn - letting them move pieces
    private void runPlayerTurn(Player currentPlayer, Gameboard gameboard) {
        while (true) {
            System.out.println("Your current pieces are:");
            printPlayerPieces(currentPlayer.getPieces(gameboard));
            System.out.println("Select a piece via its index");
            int humanPieceIndex = Integer.parseInt(input.nextLine());
            int computerPieceIndex = humanPieceIndex - 1;
            Piece piece = currentPlayer.getPieces(gameboard).get(computerPieceIndex);
            printPossiblePositions(piece, gameboard);
            System.out.println("Select a possible position via its index. Enter `q` to select a different piece");
            String nextPositionResponse = input.nextLine();
            if (nextPositionResponse.equals("q")) {
                continue;
            }
            int humanPositionIndex = Integer.parseInt(nextPositionResponse);
            int computerPositionIndex = humanPositionIndex - 1;
            if (humanPositionIndex > piece.generatePossiblePositions(gameboard).size()) {
                continue;
            }
            Position nextPosition = piece.generatePossiblePositions(gameboard).get(computerPositionIndex);
            currentPlayer.movePiece(piece, nextPosition, gameboard);
            break;
        }
    }

    // EFFECTS: prints the gameboard
    private void printGameboard(Game game) {
        ArrayList<ArrayList<Piece>> gameboard = game.getGameboard().getBoard();
        HashMap<String, String> map = new HashMap<String, String>();
        for (ArrayList<Piece> row: gameboard) {
            for (Piece piece: row) {
                String name = piece.getName();
                if (piece.getOwner() == Piece.PLAYER_1) {
                    name = name.toUpperCase();
                }
                String charToPrint = " " + name.substring(0, 1) + " ";
                if (name.equalsIgnoreCase("king") || name.equalsIgnoreCase("cannon")) {
                    charToPrint = " " + name.substring(0,1) + "* ";
                } else if (name.equals("space")) {
                    charToPrint = "   ";
                }

                System.out.print(charToPrint);
            }
            System.out.println("");
        }
    }
}
