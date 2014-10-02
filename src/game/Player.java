
package game;

import game.Piece.Color;

/**
 * Represents a player in the game. It knows its pieces and decides moves.
 * 
 * @author pganuza
 */
public class Player {
    private String name;
    private Color color;
    private Piece[] pieces = null;
    private int penalty;
    private IPlayerStrategy strategy = null;

    public Player() {

    }

    public Player(final String name, final Color color, final int numPieces) {
        this.name = name;
        this.color = color;
        this.pieces = new Piece[numPieces];

        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = new Piece(this);
        }
    }

    public Player(final String name, final Color color, final int numPieces, final IPlayerStrategy strategy) {
        this.name = name;
        this.pieces = new Piece[numPieces];

        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = new Piece(this);
        }

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

    public IPlayerStrategy getStrategy() {
        return strategy;
    }

    /**
     * @return
     */
    public boolean canMove() {
        if (penalty > 0) {
            penalty--;
            return false;
        }

        return true;
    }

    /**
     * 
     */
    public Piece selectPieceToMove(final Board board, final int jumps) {
        // TODO Auto-generated method stub
        if (penalty > 0) {
            penalty--;
        } else {
            return strategy.selectMove(this, board, jumps);

        }

        return null;
    }
}
