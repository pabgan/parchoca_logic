
package game;

import game.Piece.Color;

/**
 * Represents a player in the game. It knows its pieces and decides moves.
 * 
 * @author pganuza
 */
public class Player {
    private final String name;
    private final Color color;
    private Piece[] pieces = null;
    private int penalty;
    private IPlayerStrategy strategy = null;

    public Player(final String name, final Color color, final int numPieces) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can not be empty");
        }

        if (numPieces < 1) {
            throw new IllegalArgumentException("At least one piece is needed for a player.");

        }

        this.name = name;
        this.color = color;
        this.pieces = new Piece[numPieces];

        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = new Piece(this);
        }
    }

    public Player(final String name, final Color color, final int numPieces, final IPlayerStrategy strategy) {
        this(name, color, numPieces);

        this.strategy = strategy;
    }

	public int getPenalty() {
        return penalty;
    }

    public void setPenalty(final int penalty) {
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public Piece[] getPieces() {
        return pieces;
    }

    /**
     * 
     */
    public Piece selectPieceToMove(final Board board, final int jumps) {
        if (board == null) {
            throw new IllegalArgumentException("Board is needed to select the piece to move.");
        }
        if (jumps < 1) {
            throw new IllegalArgumentException("Can't jump less than one square.");
        }
        if (strategy == null) {
            throw new IllegalStateException("Strategy not defined for this player");
        }

        return strategy.selectMove(this, board, jumps);
    }
}
