package game;

public class GameControl {
    private final Board board;
    private Player[] players;
    private final int piecesPerPlayer = 4;
    private final Player[] endPositions;
    private int turn;

    public GameControl() {
        this.board = null;
        this.players = null;
        this.endPositions = null;
    }

    public GameControl(Player[] players) {
        this.players = players;
        this.endPositions = new Player[players.length];
        this.players = players;

        
        Piece[] pieces = new Piece;
        
        for (Player player : players) {
            piecesplayer.getPieces();
        }
        this.board = new Board(pieces);
    }

    public void addPlayer(Player player) {

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
