package game;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the game board. It knows where all the pieces are.
 * 
 * @author pganuza
 * 
 */
public class Board {
    // Board has 63 positions, from number 0 to 62
    public final static int boardSize = 63;

    // The board itself contains the pieces that are in every position.
    // In every position there could be 0, 1 or 2 (barrier) pieces
    // To know where is a piece
    private final HashMap<Piece, Integer> piecesOnTheRoad;
    // To know what pieces are in a position
    private final Position[] board;
    // To know what pieces are not yet in the track
    private final Set<Piece> piecesAtHome;
    // To know what pieces reached the goal
    private final Set<Piece> piecesAtParchoca;

    public Board(final Piece[] pieces) {
        this.piecesOnTheRoad = new HashMap<Piece, Integer>();
        this.board = new Position[Board.boardSize];
        this.piecesAtHome = new HashSet<Piece>(pieces.length);
        this.piecesAtParchoca = new HashSet<Piece>(pieces.length);

        for (int i = 0; i < board.length; i++) {
            board[i] = new Position(i);
        }

        for (Piece piece : pieces) {
            piecesAtHome.add(piece);
        }
    }

    public Color whoIsInPosition(int x) {
        return board[x].whoIsIn();
    }

    public boolean isPositionEmpty(int x) {
        return board[x].isPositionEmpty();
    }

    public boolean isPositionAWall(int x) {
        return board[x].isPositionWall();
    }

    public Piece[] whoIsAtHome() {
        return (Piece[]) piecesAtHome.toArray();
    }

    public Piece[] whoIsAtParchoca() {
        return (Piece[]) piecesAtParchoca.toArray();
    }

    /**
     * @param piece
     * @return The position of the piece, or null if it is at Home or Parchoca or if it does not exist.
     */
    public int whereIsOnTheRoad(Piece piece) {
        if (!piecesOnTheRoad.containsKey(piece)) {
            throw new IllegalArgumentException("Piece is not on the road.");
        }

        return piecesOnTheRoad.get(piece).intValue();
    }

    public boolean isAtHome(Piece piece) {
        return piecesAtHome.contains(piece);
    }

    public boolean isAtParchoca(Piece piece) {
        return piecesAtParchoca.contains(piece);
    }

    /**
     * Is responsible of executing the movement in case it is valid.
     * 
     * @param piece
     * @param jumps
     */
    public void move(Piece piece, int jumps) {
    }

    public int whereWouldItEnd(Piece piece, int jumps) {
        // First we find the piece
        if (piecesAtHome.contains(piece)) {
            Position wall = whereIsTheWall(0, jumps);
        } else if (piecesAtParchoca.contains(piece)) {
            throw new IllegalArgumentException("Can not move a piece that is already at Parchoca.");
        } else if (piecesOnTheRoad.containsKey(piece)) {

        } else {
            throw new IllegalArgumentException("Piece not found.");
        }

    }

    private int whereWouldItEnd(Piece piece, int jumps, boolean forward) {

    }
}
