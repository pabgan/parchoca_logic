
package game;

public class Parchoca {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String player0name = "Pablo0";
        Color color0 = Color.yellow;

        String player1name = "Juan1";
        Color color1 = Color.blue;

        String player2name = "Echegu2";
        Color color2 = Color.green;

        String player3name = "Kike3";
        Color color3 = Color.red;

        Player[] players = new Player[4];
        players[0] = new Player(player0name, color0);
        players[1] = new Player(player1name, color1);
        players[2] = new Player(player2name, color2);
        players[3] = new Player(player3name, color3);

        GameControl game = new GameControl(players);

        game.start();

        while (!game.isOver()) {
            Player nextPlayer = game.whoIsNextPlayer();
        }

    }
}
