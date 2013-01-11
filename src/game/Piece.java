package game;

/**
 * Represents a player's piece on the game. It is just a Bean.
 * 
 * @author pganuza
 * 
 */
public class Piece {
    private final Player player;
    private final Color color;

    public Piece(Player player, Color color) {
        this.player = player;
        this.color = color;
    }

    public Player getPlayer() {
        return player;
    }

    public Color getColor() {
        return color;
    }
}
