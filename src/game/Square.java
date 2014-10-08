/**
 * 
 */

package game;

/**
 * Represents a square in the board. It is responsible of his own legal state.
 * 
 * @author pganuza
 */
public class Square implements ISquare {
    // Square number from 0-home to 63-parchoca
    protected final int number;
    protected final Piece[] occupants;
    protected final int penalty;
    protected final Square linkedSquare;

    public Square(final int number, final int penalty, final Square linkedSquare) {
        this.number = number;
        occupants = new Piece[2];
        this.penalty = penalty;
        this.linkedSquare = linkedSquare;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getPenalty() {
        return penalty;
    }

    @Override
    public Square getLinkedSquare() {
        return linkedSquare;
    }

    /**
     * @return Returns the pieces occupying the position, null if it is empty.
     */
    @Override
    public Piece[] getOccupants() {
        Piece[] occupantsClone = new Piece[occupants.length];

        for (int i = 0; i < occupants.length; i++) {
            occupantsClone[i] = occupants[i];
        }

        return occupantsClone;
    }

    @Override
    public boolean contains(final Piece piece) {
        return occupants[0] == piece || occupants[1] == piece;
    }

    @Override
    public Piece add(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece to add can't be null.");
        }

        // If position is empty we place the piece in the first spot
        if (isEmpty()) {
            occupants[0] = piece;
            piece.setSquare(this);

            return null;
        } else {
            if (isWall()) {
                throw new IllegalStateException("Illegal movement. Can not add another piece to a wall.");
            }

            // If position is already occupied by that piece
            else if ((occupants[0] == piece) || (occupants[1] == piece)) {
                throw new IllegalStateException("Illegal movement. Piece is already in this square.");
            }

            // If position is not empty nor a wall there must be a piece in the first spot
            // If it is of the same color as the one willing to move then we insert it in the second spot to make a
            // wall
            else if (occupants[0].getColor() == piece.getColor()) {
                occupants[1] = piece;
                piece.setSquare(this);

                return null;
            } else { // If they are not the same color then is a kill
                Piece killed = occupants[0];
                occupants[0] = piece;
                piece.setSquare(this);

                return killed;
            }
        }
    }

    /**
     * @return The piece that was removed from this position, null if there was none
     */
    @Override
    public Piece remove() {
        Piece ret = occupants[0];
        occupants[0] = occupants[1];
        occupants[1] = null;

        if (ret != null) {
            ret.setSquare(null);
        }

        return ret;
    }

    /**
     * @return true if piece was present and removed successfully, false otherwise
     */
    @Override
    public boolean remove(final Piece piece) {
        if (occupants[1] == piece) {
            occupants[1] = null;
            piece.setSquare(null);

            return true;
        } else if (occupants[0] == piece) {
            occupants[0] = occupants[1];
            occupants[1] = null;
            piece.setSquare(null);

            return true;
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return (occupants[0] == null);
    }

    @Override
    public boolean isWall() {
        return (occupants[0] != null) && (occupants[1] != null);
    }
}
