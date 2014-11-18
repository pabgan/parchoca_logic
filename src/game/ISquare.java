/**
 * 
 */

package game;

import java.util.List;

/**
 * @author TCPsi
 */
public interface ISquare {
	/**
	 * @return the square position in the board
	 */
	public int getNumber();

	/**
	 * @return the penalty, positive (jumps) or negative (turns without moving)
	 */
	public int getPenalty();

	/**
	 * Sets the penalty
	 */
	public void setPenalty(int penalty);

	/**
	 * @return the square to where the piece has to jump from here
	 */
	public ISquare getLinkedSquare();

	/**
	 * Sets the square to where the piece has to jump from here
	 */
	public void setLinkedSquare(ISquare linkedSquare);

	/**
	 * @return an array containing the pieces that are in this square
	 */
	public List<Piece> getOccupants();

	/**
	 * @param piece
	 * @return true if the piece is in this square
	 */
	public boolean contains(final Piece piece);

	/**
	 * Adds the piece to this position consistently with game rules
	 * 
	 * @param piece
	 * @return Returns the killed piece or null in any other case
	 */
	public Piece add(final Piece piece);

	/**
	 * @return The piece that was removed from this position that should be the
	 *         last one added, null if none was removed
	 */
	public Piece remove();

	/**
	 * @return true if piece was present and removed successfully, false
	 *         otherwise
	 */
	public boolean remove(final Piece piece);

	/**
	 * @return true if there are no pieces in this square
	 */
	public boolean isEmpty();

	/**
	 * @return true if a piece cannot go through this square at the moment
	 */
	public boolean isWall();
}
