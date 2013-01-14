package game;

public class Player {
    private String name;
    private Color color;
    private Piece[] pieces = null;
    private int penalty;
    private IPlayerStrategy strategy = null;

    public Player() {

    }

    public Player(String name, Color color, int numPieces, IPlayerStrategy strategy) {
        this.name = name;
        this.color = color;
        this.pieces = new Piece[numPieces];

        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = new Piece(this, this.color);
        }

        this.strategy = strategy;
    }

    /**
     * 
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
    public Piece selectMove(Board board, Player[] players, int jumps) {
        // TODO Auto-generated method stub
        if (penalty > 0) {
            penalty--;
        } else {
            return strategy.selectMove(this, board, players, jumps);

        }

        return null;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
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
}
