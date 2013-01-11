package game;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the game board. It knows where all the pieces are.
 * 
 * @author pganuza
 * 
 */
public class Board {
    public final static int boardSize = 63;

    private final Piece[] pieces;
    // The board itself contains the pieces that are in every position.
    // In every position there could be 0, 1 or 2 (barrier) pieces
    private final Position[] board;
    private final Set<Piece> piecesAtHome;
    private final Set<Piece> piecesAtParchoca;

    public Board(final Piece[] pieces) {
        this.pieces = pieces;

        board = new Position[Board.boardSize];

        for (int i = 0; i < board.length; i++) {
            board[i] = new Position(i);
        }

        piecesAtHome = new HashSet<Piece>(pieces.length);

        for (Piece piece : pieces) {
            piecesAtHome.add(piece);
        }

        piecesAtParchoca = new HashSet<Piece>(pieces.length);
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
}
