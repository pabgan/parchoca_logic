
package game;

import java.util.ArrayList;
import java.util.Iterator;

public class GameControl {
    private final Board board;
    private final PlayerIterator playerIterator;

    public GameControl() {
        this.board = new Board();
        this.playerIterator = new PlayerIterator();
    }

    public void addPlayer(final Player player) {
        playerIterator.addPlayer(player);
        for (Piece piece : player.getPieces()) {
            board.addPiece(piece);
        }
    }

    public Player start() {
        for (int i = 0; i < Dice.throwDice() - 1; i++) {
            playerIterator.nextPlayer();
        }

        Player playerCurrent = null;
        Piece pieceToMove = null;
        boolean gameOver = false;
        int jumps6 = 0;
        int jumps = 0;
        // TODO ronda de la humillación
        // TODO 6 con todas las fichas fueras es 7
        // TODO penalizaciones
        while (!gameOver) {
            playerCurrent = playerIterator.nextPlayer();

            if (playerCurrent.canMove()) {
                do {
                    jumps = Dice.throwDice();
                    if (jumps == 6) {
                        jumps6++;
                    }
                    System.out.println("Player: " + playerCurrent.getName() + "\nDice: " + jumps + "\n" + board);

                    if (jumps6 == 3) {
                        board.killPiece(pieceToMove);
                        jumps = 0;
                    } else {
                        int extraJumps;

                        do {
                            pieceToMove = playerCurrent.selectPieceToMove(board, jumps);
                            extraJumps = executeMove(pieceToMove, jumps);

                            gameOver = true;
                            for (Piece piece : playerCurrent.getPieces()) {
                                if (!board.isAtParchoca(piece)) {
                                    gameOver = false;
                                    break;
                                }
                            }
                        } while (extraJumps < 0);

                        playerCurrent.setPenalty(extraJumps);
                    }
                } while (jumps == 6 && !gameOver);
            }
        }

        return playerCurrent;
    }

    private int executeMove(final Piece pieceToMove, final int jumps) {
        if (board.move(pieceToMove, jumps)) {
            return 20;
        }

        if (board.isAtParchoca(pieceToMove)) {
            return 10;
        }

        return pieceToMove.getSquare().getPenalty();
    }

    public void printState() {
        System.out.println(board);
    }

    private class PlayerIterator {
        private Iterator<Player> playerIterator;
        private final ArrayList<Player> players;

        public PlayerIterator() {
            players = new ArrayList<Player>();
        }

        public void addPlayer(final Player player) {
            if (players.contains(player)) {
                throw new IllegalArgumentException("Can't add same player twice");
            }
            players.add(player);

        }

        public Player nextPlayer() {
            if (players.isEmpty()) {
                throw new IllegalStateException("Can't select next player beacuse there ara no players");
            }
            if (playerIterator == null) {
                this.playerIterator = players.iterator();
            }
            if (playerIterator.hasNext()) {
                return playerIterator.next();
            } else {
                playerIterator = players.iterator();

                return playerIterator.next();
            }
        }
    }
}
