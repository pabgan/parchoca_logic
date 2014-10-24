
package game;

import game.Piece.Color;

public class Parchoca {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        String player0name = "Pablo0";
        Color color0 = Color.yellow;

        String player1name = "Juan1";
        Color color1 = Color.blue;

        String player2name = "Echegu2";
        Color color2 = Color.green;

        String player3name = "Kike3";
        Color color3 = Color.red;

        Player[] players = new Player[4];
        players[0] = new Player(player0name, color0, 4, new StupidPlayerStrategy());
        players[1] = new Player(player1name, color1, 4);
        players[2] = new Player(player2name, color2, 4);
        players[3] = new Player(player3name, color3, 4);

        Board board = new Board();
        Player player0 = players[0];
        Piece piece0 = player0.getPieces()[0];
        Piece piece1 = player0.getPieces()[1];
        Piece piece2 = player0.getPieces()[2];
        Piece piece3 = player0.getPieces()[3];

        GameControl gameControl = new GameControl();
        gameControl.addPlayer(players[0]);
        // gameControl.addPlayer(players[1]);
        // gameControl.addPlayer(players[2]);
        // gameControl.addPlayer(players[3]);
        // gameControl.printState();

        gameControl.start();
    }
}
