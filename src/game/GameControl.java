
package game;

public class GameControl {
    private final Board board;
    private final PlayerManager playerManager;

    public GameControl() {
        this.board = new Board();
        this.playerManager = new PlayerManager();
    }

    public void addPlayer(final Player player) {
        playerManager.addPlayer(player);
        for (Piece piece : player.getPieces()) {
            board.addPiece(piece);
        }
    }

    public void start() {
        for (int i = 0; i < Dice.throwDice() - 1; i++) {
            playerManager.nextPlayer();
        }

        while (!gameOver()) {
            // How many 6 in a row has the player had
            int result6 = 0;

            Player playerCurrent = playerManager.nextPlayer();
            Piece pieceSelected = null;
            boolean tiraOtraVez = false;

            do {
                int diceResult = Dice.throwDice();
                int jumps = diceResult;

                if (diceResult == 6) {
                    tiraOtraVez = true;

                    if (++result6 == 3) {
                        board.killPiece(pieceSelected);
                        tiraOtraVez = false;
                        jumps = 0;
                    } else if (playerCurrent.allPiecesOut()) {
                        jumps = 7;
                    }
                } else {
                    result6 = 0;
                }

                while (jumps > 0) {
                    pieceSelected = playerCurrent.selectPieceToMove(board, jumps);
                    int moveResult = board.move(pieceSelected, diceResult);

                    switch (moveResult) {
                        case 21:
                            tiraOtraVez = true;
                        case 20:
                            jumps = 20;
                            break;
                        case 11:
                            tiraOtraVez = true;
                        case 10:
                            jumps = 10;
                            break;
                        case 1:
                            tiraOtraVez = true;
                            jumps = 0;
                            break;
                        default:
                            tiraOtraVez = false;
                            jumps = 0;
                            playerCurrent.setPenalty(moveResult);
                    }

                    System.out.println("Player: " + playerCurrent.getName() + "." + playerCurrent.getColor()
                            + "\nDice: " + diceResult + "\nJumps: " + jumps + "\n" + board);
                }
            } while (tiraOtraVez);
        }
    }

    private boolean gameOver() {
        return playerManager.numberOfPlayersStillPlaying() > 0;
    }

    public void printState() {
        System.out.println(board);
    }
}
