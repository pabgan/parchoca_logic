/**
 * 
 */

package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a square in the board. It is responsible of his own legal state.
 * 
 * @author pganuza
 */
public class Square implements ISquare {
    // Square number from 0-home to 63-parchoca
    protected final int number;
    protected final List<Piece> occupants;
    protected int penalty;
    protected ISquare linkedSquare;

    public Square(final int number) {
        this.number = number;
        occupants = new ArrayList<Piece>(2);
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
    public void setPenalty(final int penalty) {
        this.penalty = penalty;
    }

    @Override
    public ISquare getLinkedSquare() {
        return linkedSquare;
    }

    @Override
    public void setLinkedSquare(final ISquare linkedSquare) {
        this.linkedSquare = linkedSquare;
    }

    /**
     * @return Returns the pieces occupying the position, null if it is empty.
     */
    @Override
    public List<Piece> getOccupants() {
        return occupants;
    }

    @Override
    public boolean contains(final Piece piece) {
        return occupants.contains(piece);
    }

    @Override
    public Piece add(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece to add can't be null.");
        }

        // If position is empty we place the piece in the first spot
        if (isEmpty()) {
            occupants.set(0, piece);
            piece.setSquare(this);

            return null;
        } else {
            if (isWall()) {
                throw new IllegalStateException("Illegal movement. Can not add another piece to a wall.");
            }

            // If position is already occupied by that piece
            else if ((occupants.get(0) == piece) || (occupants.get(1) == piece)) {
                return null;
            }

            // If position is not empty nor a wall there must be a piece in the first spot
            // If it is of the same color as the one willing to move then we insert it in the second spot to make a
            // wall
            else if (occupants.get(0).getColor() == piece.getColor()) {
                occupants.set(1, piece);
                piece.setSquare(this);

                return null;
            } else { // If they are not the same color then is a kill
                Piece killed = occupants.get(0);
                occupants.set(0, piece);
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
        Piece ret = occupants.remove(0);
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
        occupants.remove(piece);

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
