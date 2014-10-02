/**
 * 
 */

package game;

/**
 * The strategy to follow on deciding which piece to move
 * 
 * @author pganuza
 */
public interface IPlayerStrategy {
    /**
     * @param player
     * @param board Board containing the state of the game.
     * @param jumps Number of jumps to move the piece
     * @return The piece that the player has to move
     */
    public Piece selectMove(Player player, Board board, int jumps);

}
