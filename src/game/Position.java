/**
 * 
 */
package game;

/**
 * Represents a position in the board. It is responsible of his own legal state.
 * 
 * @author pganuza
 * 
 */
public class Position {
    private final int number;
    private final Piece[] occupants;

    public Position(int number) {
        this.number = number;
        occupants = new Piece[2];
    }

    public int getNumber() {
        return number;
    }

    public boolean isPositionEmpty() {
        return (occupants[0] == null);
    }

    public boolean isPositionWall() {
        return (occupants[0] != null) && (occupants[1] != null);
    }

    /**
     * @return Returns the color of the piece occupying the position, null if it is empty.
     */
    public Color whoIsIn() {
        if (occupants[0] != null) {
            return occupants[0].getColor();
        }

        return null;
    }

    /**
     * @param piece
     * @return Returns the killed piece or null if position was either empty or occupied by other piece of the same color.
     */
    public Piece move(Piece piece) {

        // If position is empty we place the piece in the first spot
        if (isPositionEmpty()) {
            occupants[0] = piece;

            return null;
        }

        // If position is a Wall we throw an exception, this movement should be checked before
        if (isPositionWall()) {
            throw new IllegalStateException("Illegal movement. Can not move to a position occupied by a wall.");
        }

        // If position is already held by that piece
        if ((occupants[0] == piece) || (occupants[1] == piece)) {
            throw new IllegalArgumentException("Illegal movement. Can not move to the same position.");
        }

        // If position is not empty nor a wall there must be a piece in the first spot
        // If it is of the same color as the one willing to move then we insert it in the second spot
        if (occupants[0].getColor() == piece.getColor()) {
            occupants[1] = piece;

            return null;
        } else { // If they are not the same color then is a kill
            Piece killed = occupants[0];
            occupants[0] = piece;

            return killed;
        }
    }

    /**
     * @return The piece that was removed from this position
     */
    public Piece remove() {
        Piece ret = null;

        if (isPositionEmpty()) {
            throw new IllegalStateException("Can not remove any piece from an empty position.");
        }

        if (isPositionWall()) {
            ret = occupants[1];

            occupants[1] = null;
        } else {
            ret = occupants[0];

            occupants[0] = null;
        }

        return ret;
    }
}
