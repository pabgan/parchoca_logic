
package game;

import java.util.Random;

public class RandomPlayerStrategy implements IPlayerStrategy {

    @Override
    public Piece selectMove(final Player player, final Board board, final int jumps) {
        Piece[] pieces = player.getPieces();
        Random rand = new Random();

        return pieces[rand.nextInt(4)];
    }
}
