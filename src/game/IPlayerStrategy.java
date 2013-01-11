/**
 * 
 */
package game;

/**
 * @author pganuza
 * 
 */
public interface IPlayerStrategy {
    /**
     * @param player
     * @param board
     *            Board containing the state of the game.
     * @param enemies
     *            Players still playing.
     * @param jumps
     *            Number of jumps to move the piece
     * @return The piece that the player has to move
     */
    public Piece selectMove(Player player, Board board, Player[] enemies, int jumps);

}
