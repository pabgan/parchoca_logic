package game;

import java.util.HashSet;
import java.util.Set;

public class GameControl {
    private int turn;

    private Player[] players;
    private final Player[] endPositions;
    private final Board board;

    public GameControl() {
        this.board = null;
        this.players = null;
        this.endPositions = null;
    }

    public GameControl(Player[] players) {
        this.players = players;
        this.endPositions = new Player[players.length];
        this.players = players;

        Set<Piece> pieces = new HashSet<Piece>();

        for (Player player : players) {
            pieces.addAll(player.getPieces());
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
