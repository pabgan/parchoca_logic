package game;

public class Player {
    private final String name;
    private final Color color;
    private final Piece[] pieces = new Piece[GameControl.piecesPerPlayer];
    private int penalty;
    private final IPlayerStrategy strategy;

    public Player(String name, Color color, IPlayerStrategy strategy) {
        this.name = name;
        this.color = color;

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
}
