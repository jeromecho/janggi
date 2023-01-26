package model;

import model.pieces.Piece;
import model.pieces.Space;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents a player with a name, order in which they are playing (first or second), and a
   state that tells whether or not the player has lost or not
 */
public class Player {
    private String playerName;
    private int playerOrder;
    private boolean isLost;

    // EFFECTS: constructs a player with a name and order (first or second player) who has
    //          yet to forfeit the game
    public Player(String name, int order) {
        playerName = name;
        playerOrder = order;
        isLost = false;
    }

    // EFFECTS: returns the pieces of the player found
    //          on the given gameboard
    public List<Piece> getPieces(Gameboard gameboard) {
        ArrayList<Piece> playerPieces = new ArrayList<Piece>();
        for (List<Piece> row: gameboard.getBoard()) {
            for (Piece piece: row) {
                if (piece.getOwner() == playerOrder) {
                    playerPieces.add(piece);
                }
            }
        }
        return playerPieces;
    }

    // REQUIRES: position given must be a valid position to move to. Valid is defined as:
    //          1) there exist no pieces blocking the piece's path, 2) the positions
    //          the piece wants to go to are reachable via valid piece movement, and 3)
    //          the position is not occupied by an ally piece
    // MODIFIES: gameboard
    // EFFECTS: moves a piece to a position then updates its position. If an enemy piece exists
    //          in the position the piece wants to go to, the enemy piece is removed from the board.
    //          The position the piece previously occupied is replaced by an empty space
    public void movePiece(Piece piece, Position pos, Gameboard gameboard) {
        int posY = piece.getPosition().getPosY();
        int posX = piece.getPosition().getPosX();
        int newPosY = pos.getPosY();
        int newPosX = pos.getPosX();
        Piece space = new Space(posY, posX);

        gameboard.getBoard().get(posY).set(posX, space);
        gameboard.getBoard().get(newPosY).set(newPosX, piece);
        piece.getPosition().setPosY(newPosY);
        piece.getPosition().setPosX(newPosX);
    }

    // MODIFIES: this
    // EFFECTS: sets the state of the player to having lost
    public void forfeit() {
        isLost = true;
    }

    // MODIFIES: this
    // EFFECTS: sets the state of the player to having lost if the king of the
    //          player is no longer on the board
    public void checkForKing(Gameboard gameboard) {
        boolean isThereKing = false;

        for (ArrayList<Piece> row: gameboard.getBoard()) {
            for (Piece piece: row) {
                if (piece.getName().equals("king") && piece.getOwner() == playerOrder) {
                    isThereKing = true;
                }
            }
        }
        if (!isThereKing) {
            isLost = true;
        }
    }

    // EFFECTS: returns the name of the player
    public String getName() {
        return playerName;
    }

    // EFFECTS: returns the playing order of the player
    public int getPlayingOrder() {
        return playerOrder;
    }

    // EFFECTS: returns true if the player has lost, else false
    public boolean getStatus() {
        return isLost;
    }
}
