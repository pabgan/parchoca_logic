/**
 * 
 */

package game;

/**
 * Represents a square in the board. It is responsible of his own legal state.
 * 
 * @author pganuza
 */
public class Square {
    private final int number;
    private final Piece[] occupants;
    private final int penalty;

    public Square(final int number, final int penalty) {
        this.number = number;
        occupants = new Piece[2];
        this.penalty = penalty;
    }

    public int getNumber() {
        return number;
    }

    public int getPenalty() {
        return penalty;
    }

    /**
     * @return Returns the pieces occupying the position, null if it is empty.
     */
    public Piece[] getOccupants() {
        Piece[] occupantsClone = new Piece[occupants.length];

        for (int i = 0; i < occupants.length; i++) {
            occupantsClone[i] = occupants[i];
        }

        return occupantsClone;
    }

    /**
     * Moves the piece to this position consistently with game rules
     * 
     * @param piece
     * @return Returns the killed piece or null if position was either empty or occupied by other piece of the same
     *         color.
     */
    public Piece add(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece to add can't be null.");
        }

        // If position is empty we place the piece in the first spot
        if (isEmpty()) {
            occupants[0] = piece;

            return null;
        }

        // If position is a Wall we throw an exception, this movement should be checked before
        else if (isWall()) {
            throw new IllegalStateException("Illegal movement. Can not move to a position occupied by a wall.");
        }

        // If position is already occupied by that piece
        else if ((occupants[0] == piece) || (occupants[1] == piece)) {
            throw new IllegalStateException("Illegal movement. Can not move to the same position.");
        }

        // If position is not empty nor a wall there must be a piece in the first spot
        // If it is of the same color as the one willing to move then we insert it in the second spot to make a wall
        else if (occupants[0].getColor() == piece.getColor()) {
            occupants[1] = piece;

            return null;
        } else { // If they are not the same color then is a kill
            Piece killed = occupants[0];
            occupants[0] = piece;

            return killed;
        }
    }

    /**
     * @return The piece that was removed from this position, null if there was none
     */
    public Piece remove() {
        Piece ret = occupants[0];
        occupants[0] = occupants[1];
        occupants[1] = null;

        return ret;
    }

    /**
     * @return true if piece was present and removed successfully, false otherwise
     */
    public boolean remove(final Piece piece) {
        if (occupants[1] == piece) {
            occupants[1] = null;

            return true;
        } else if (occupants[0] == piece) {
            occupants[0] = occupants[1];
            occupants[1] = null;

            return true;
        }

        return false;
    }

    public boolean isEmpty() {
        return (occupants[0] == null);
    }

    public boolean isWall() {
        return (occupants[0] != null) && (occupants[1] != null);
    }
}
