
package game;

import java.util.ArrayList;

/**
 * Represents the Home in the board, where every piece starts from.
 * 
 * @author pganuza
 */

public class ParchocaSquare implements ISquare {
    private final ArrayList<Piece> occupants;
    private final Square linkedSquare;

    public ParchocaSquare(final Square linkedSquare) {
        this.occupants = new ArrayList<Piece>();
        this.linkedSquare = linkedSquare;
    }

    @Override
    public Piece add(final Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece to add can't be null.");
        }

        // If position is already occupied by that piece
        else if (occupants.contains(piece)) {
            throw new IllegalStateException("Illegal movement. Can not move to the same position.");
        }

        occupants.add(piece);
        piece.setSquare(this);

        return null;
    }

    @Override
    public Piece remove() {
        return occupants.remove(occupants.size());
    }

    @Override
    public boolean remove(final Piece piece) {
        return occupants.remove(piece);
    }

    @Override
    public boolean isEmpty() {
        return occupants.isEmpty();
    }

    @Override
    public boolean isWall() {
        return false;
    }

    @Override
    public boolean contains(final Piece piece) {
        return occupants.contains(piece);
    }

    @Override
    public int getNumber() {
        return Board.BOARD_SIZE;
    }

    @Override
    public int getPenalty() {
        return 10;
    }

    @Override
    public Square getLinkedSquare() {
        return linkedSquare;
    }

    @Override
    public Piece[] getOccupants() {
        return (Piece[]) occupants.toArray();
    }
}
