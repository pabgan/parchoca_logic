
package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameControl {
    private final Board board;
    private final PlayerManager playerManager;

    // private final List<Player> finishedPlayers;

    public GameControl() {
        this.board = new Board();
        this.playerManager = new PlayerManager();
        // this.finishedPlayers = new ArrayList<Player>();
    }

    public void addPlayer(final Player player) {
        playerManager.addPlayer(player);
        for (Piece piece : player.getPieces()) {
            board.addPiece(piece);
        }
    }

    public Player start() {
        for (int i = 0; i < Dice.throwDice() - 1; i++) {
            playerManager.nextPlayer();
        }

        Player playerCurrent = null;
        Piece pieceToMove = null;
        boolean gameOver = false;
        // TODO ronda de la humillación
        // TODO 6 con todas las fichas fueras es 7
        // TODO penalizaciones
        // TODO no más tiradas si te sucede algo malo
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
                        boolean anotherTurn = false;

                        do {
                            pieceToMove = playerCurrent.selectPieceToMove(board, jumps);
                            int moveResult = board.move(pieceToMove, jumps);
                            
                            switch (moveResult) {
							case 21:
								anotherTurn = true;
								extraJumps = 20;
								break;
							case 11:
								anotherTurn = true;
								extraJumps = 10;
								break;

							default:
								break;
							}
                            extraJumps = 

                            if (extraJumps == 10 || extraJumps == 11) {
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
}
