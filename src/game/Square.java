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
			occupants.add(piece);
			piece.setSquare(this);

			return null;
		} else {
			if (isWall()) {
				throw new IllegalStateException("Illegal movement. Can not add another piece to a wall.");
			}

			// If position is already occupied by that piece
			else if (occupants.contains(piece)) {
				return null;
			}

			// If position is not empty nor a wall there must be a piece in the
			// first spot
			// If it is of the same color as the one willing to move then we
			// insert it in the second spot to make a
			// wall
			else if (occupants.get(0).getColor() == piece.getColor()) {
				occupants.add(piece);
				piece.setSquare(this);

				return null;
			} else { // If they are not the same color then is a kill
				Piece killed = occupants.remove(0);
				occupants.add(piece);
				piece.setSquare(this);

				return killed;
			}
		}
	}

	/**
	 * @return The piece that was removed from this position, null if there was
	 *         none
	 */
	@Override
	public Piece remove() {
		Piece ret = null;

		if (!occupants.isEmpty()) {
			ret = occupants.remove(occupants.size() - 1);
			ret.setSquare(null);
		}

		return ret;
	}

	/**
	 * @return true if piece was present and removed successfully, false
	 *         otherwise
	 */
	@Override
	public boolean remove(final Piece piece) {
		if (occupants.remove(piece)) {
			piece.setSquare(null);
			return true;
		}

		return false;
	}

	@Override
	public boolean isEmpty() {
		return occupants.isEmpty();
	}

	@Override
	public boolean isWall() {
		return occupants.size() == 2;
	}
}
