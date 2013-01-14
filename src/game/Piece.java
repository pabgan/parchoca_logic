package game;

/**
 * Represents a player's piece on the game. It is just a Bean.
 * 
 * @author pganuza
 * 
 */
public class Piece {
    private Player player = null;
    private Color color = null;

    public Piece() {
    }

    public Piece(Player player, Color color) {
        this.player = player;
        this.color = color;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
