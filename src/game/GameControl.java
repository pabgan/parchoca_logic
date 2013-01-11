package game;

public class GameControl {
    public static final int piecesPerPlayer = 4;
    public final int maximumNumberOfPlayers = 6;

    private final Board board;
    private final Player[] players;
    private final Player[] endPositions;
    private int turn;

    public GameControl() {
        this.players = new Player[maximumNumberOfPlayers];
        this.endPositions = new Player[maximumNumberOfPlayers];
    }

    public void start() {
        // TODO Auto-generated method stub
        turn = selectInitialPlayer();

    }

    public void play() {
        while (!isGameOver()) {
            players[turn].move();
        }
    }

    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
    }

    private int selectInitialPlayer() {
        // TODO Auto-generated method stub
    }

    public Player whoIsNextPlayer() {
        // TODO Auto-generated method stub
        if (turn >= players.length) {
            turn = 0;
        } else {
            turn++;
        }

        // if (players[turn].ge) {
        // return null;
        // }
    }
}
