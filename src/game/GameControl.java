
package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameControl {
    private final Board board;
    private final PlayerIterator playerIterator;
    private final List<Player> finishedPlayers;

    public GameControl() {
        this.board = new Board();
        this.playerIterator = new PlayerIterator();
        this.finishedPlayers = new ArrayList<Player>();
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
        // TODO ronda de la humillación
        // TODO 6 con todas las fichas fueras es 7
        // TODO penalizaciones
        // TODO no más tiradas si te sucede algo mal
        // TODO caer en oca es tirada extra
        do {
            playerCurrent = playerIterator.nextPlayer();
            int jumps6 = 0;
            int jumps = 0;

            if (!finishedPlayers.contains(playerCurrent)) {
                do {
                    jumps = Dice.throwDice();
                    if (jumps == 6) {
                        jumps6++;
                    }

                    if (jumps6 == 3) {
                        board.killPiece(pieceToMove);
                        jumps = 0;
                    } else {
                        int extraJumps;

                        do {
                            pieceToMove = playerCurrent.selectPieceToMove(board, jumps);
                            extraJumps = board.move(pieceToMove, jumps);

                            if (extraJumps == 10) {
                                finishedPlayers.add(playerCurrent);
                                for (Piece piece : playerCurrent.getPieces()) {
                                    if (!board.isAtParchoca(piece)) {
                                        finishedPlayers.remove(playerCurrent);
                                    }
                                }
                                gameOver = gameOver();
                            }
                        } while (extraJumps > 0);

                        playerCurrent.setPenalty(extraJumps);
                    }
                    System.out.println("Player: " + playerCurrent.getName() + " - " + playerCurrent.getColor()
                            + "\nDice: " + jumps + "\n" + board);
                } while (jumps == 6 && !gameOver);
            }
        } while (!gameOver);

        return playerCurrent;
    }

    private boolean gameOver() {
        return finishedPlayers.size() == playerIterator.size();
    }

    private boolean playerCanMove(final Player player) {
        boolean canMove = !finishedPlayers.contains(player);

        if (canMove) {
            int penalty = player.getPenalty();

            if (penalty > 0) {
                canMove = false;
                player.setPenalty(penalty - 1);
            }
        }

        return canMove;
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

        public ArrayList<Player> getPlayers() {
            return players;
        }

        public int size() {
            return players.size();
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
                players.removeAll(finishedPlayers);
                playerIterator = players.iterator();

                return playerIterator.next();
            }
        }
    }
}
