
package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents the game board. It knows where all the pieces are.
 * 
 * @author pganuza
 */
public class Board {
    // Board has 63 positions, from number 1 to 63
    public final static int BOARD_SIZE = 63;

    // The board itself contains the pieces that are in every position.
    // In every position there could be 0, 1 or 2 (barrier) pieces
    // To know where is a piece
    private final HashMap<Piece, Square> piecesOnTheRoad;
    // To know what pieces are in a position
    private final Square[] board;
    // To know what pieces are not yet in the track
    private final List<Piece> piecesAtHome;
    // To know what pieces reached the goal
    private final List<Piece> piecesAtParchoca;

    public Board() {
        this.piecesOnTheRoad = new HashMap<Piece, Square>();
        this.board = new Square[Board.BOARD_SIZE];
        this.piecesAtHome = new ArrayList<Piece>();
        this.piecesAtParchoca = new ArrayList<Piece>();

        for (int i = 0; i < board.length; i++) {
            board[i] = new Square(i + 1, 0);
        }
    }

    public boolean addPiece(final Piece piece) {
        return piecesAtHome.add(piece);
    }

    public boolean killPiece(final Piece piece) {
        boolean removed = piece.getSquare().remove(piece);
        return removed && piecesAtHome.add(piece);
    }

    public boolean isSquareEmpty(final int square) {
        return board[square].isEmpty();
    }

    public boolean isSquareAWall(final int square) {
        return board[square].isWall();
    }

    public List<Piece> whoIsAtHome() {
        return piecesAtHome;
    }

    public List<Piece> whoIsAtParchoca() {
        return piecesAtParchoca;
    }

    /**
     * @param piece
     * @return The square where the piece is, or null if it is at Home or Parchoca or if it does not exist.
     */
    public Square whereIs(final Piece piece) {
        if (!piecesOnTheRoad.containsKey(piece)) {
            throw new IllegalArgumentException("Piece is not on the road.");
        }

        return piecesOnTheRoad.get(piece);
    }

    public boolean isAtHome(final Piece piece) {
        return piecesAtHome.contains(piece);
    }

    public boolean isAtParchoca(final Piece piece) {
        return piecesAtParchoca.contains(piece);
    }

    /**
     * Is responsible of executing the movement in case it is valid.
     * 
     * @param piece
     * @param jumps
     * @return true if move was killer, false otherwise.
     */
    public boolean move(final Piece piece, final int jumps) {
        return false;
    }

    // public int whereWouldItEnd(final Piece piece, final int jumps) {
    // // First we find the piece
    // if (piecesAtHome.contains(piece)) {
    // Square wall = whereIsTheWall(0, jumps);
    // } else if (piecesAtParchoca.contains(piece)) {
    // throw new IllegalArgumentException("Can not move a piece that is already at Parchoca.");
    // } else if (piecesOnTheRoad.containsKey(piece)) {
    //
    // } else {
    // throw new IllegalArgumentException("Piece not found.");
    // }
    //
    // }

    // private int whereWouldItEnd(final Piece piece, final int jumps, final boolean forward) {
    //
    // }
    @Override
    public String toString() {
        // Line 0
        String ret = "+----+----+----+----+----+----+----+----+----+----+";
        ret += "\n| 01 | 02 | 03 | 04 | 05 | 06 | 07 | 08 | 09 | 10 |";
        ret += "\n|" + occupantsToString(1) + "|" + occupantsToString(2) + "|" + occupantsToString(3) + "|"
                + occupantsToString(4) + "|" + occupantsToString(5) + "|" + occupantsToString(6) + "|"
                + occupantsToString(7) + "|" + occupantsToString(8) + "|" + occupantsToString(9) + "|"
                + occupantsToString(10) + "|";

        // Line 1
        ret += "\n+----+----+----+----+----+----+----+----+----+----+";
        ret += "\n| 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 |";
        ret += "\n|" + occupantsToString(11) + "|" + occupantsToString(12) + "|" + occupantsToString(13) + "|"
                + occupantsToString(14) + "|" + occupantsToString(15) + "|" + occupantsToString(16) + "|"
                + occupantsToString(17) + "|" + occupantsToString(18) + "|" + occupantsToString(19) + "|"
                + occupantsToString(20) + "|";

        // Line 2
        ret += "\n+----+----+----+----+----+----+----+----+----+----+";
        ret += "\n| 21 | 22 | 23 | 24 | 25 | 26 | 27 | 28 | 29 | 30 |";
        ret += "\n|" + occupantsToString(21) + "|" + occupantsToString(22) + "|" + occupantsToString(23) + "|"
                + occupantsToString(24) + "|" + occupantsToString(25) + "|" + occupantsToString(26) + "|"
                + occupantsToString(27) + "|" + occupantsToString(28) + "|" + occupantsToString(29) + "|"
                + occupantsToString(30) + "|";

        // Line 3
        ret += "\n+----+----+----+----+----+----+----+----+----+----+";
        ret += "\n| 31 | 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 | 40 |";
        ret += "\n|" + occupantsToString(31) + "|" + occupantsToString(32) + "|" + occupantsToString(33) + "|"
                + occupantsToString(34) + "|" + occupantsToString(35) + "|" + occupantsToString(36) + "|"
                + occupantsToString(37) + "|" + occupantsToString(38) + "|" + occupantsToString(39) + "|"
                + occupantsToString(40) + "|";

        // Line 4
        ret += "\n+----+----+----+----+----+----+----+----+----+----+";
        ret += "\n| 41 | 42 | 43 | 44 | 45 | 46 | 47 | 48 | 49 | 50 |";
        ret += "\n|" + occupantsToString(41) + "|" + occupantsToString(42) + "|" + occupantsToString(43) + "|"
                + occupantsToString(44) + "|" + occupantsToString(45) + "|" + occupantsToString(46) + "|"
                + occupantsToString(47) + "|" + occupantsToString(48) + "|" + occupantsToString(49) + "|"
                + occupantsToString(50) + "|";

        // Line 5
        ret += "\n+----+----+----+----+----+----+----+----+----+----+";
        ret += "\n| 51 | 52 | 53 | 54 | 55 | 56 | 57 | 58 | 59 | 60 |";
        ret += "\n|" + occupantsToString(51) + "|" + occupantsToString(52) + "|" + occupantsToString(53) + "|"
                + occupantsToString(54) + "|" + occupantsToString(55) + "|" + occupantsToString(56) + "|"
                + occupantsToString(57) + "|" + occupantsToString(58) + "|" + occupantsToString(59) + "|"
                + occupantsToString(60) + "|";

        // Line 6
        ret += "\n+----+----+----+----+----+----+----+----+----+----+";
        ret += "\n| 61 | 62 | 63 |++++|++++|++++|++++|++++|++++|++++|";
        ret += "\n|" + occupantsToString(61) + "|" + occupantsToString(62) + "|" + occupantsToString(63)
                + "|++++|++++|++++|++++|++++|++++|++++|";

        ret += "\n+----+----+----+----+----+----+----+----+----+----+";

        ret += "\nEn casa: ";
        for (Piece piece : piecesAtHome) {
            ret += piece + " ";
        }
        ret += "\nEn Parchoca: ";
        for (Piece piece : piecesAtParchoca) {
            ret += piece + " ";
        }

        return ret;
    }

    private String occupantsToString(final int i) {
        Piece[] occupants = board[i - 1].getOccupants();

        if (occupants[0] == null) {
            return "    ";
        } else if (occupants[1] == null) {
            return " " + occupants[0] + "  ";
        } else {
            return " " + occupants[0] + occupants[1] + " ";
        }
    }
}
