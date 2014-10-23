
package game;

/**
 * Represents a player's piece on the game. It is just a Bean.
 * 
 * @author pganuza
 */
public class Piece {
    private Player player = null;
    private ISquare square = null;

    public Piece() {
    }

    public Piece(final Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

    public Color getColor() {
        return player.getColor();
    }

    public ISquare getSquare() {
        return square;
    }

    public void setSquare(final ISquare square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return getColor().toString().substring(0, 1);
    }

    public static enum Color {
        red, green, yellow, blue
    }
}
