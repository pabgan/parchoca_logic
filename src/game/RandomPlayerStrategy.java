package game;

import java.util.Random;

public class RandomPlayerStrategy implements IPlayerStrategy {

	@Override
	public Piece selectMove(final Player player, final Board board, final int jumps) {
		Piece[] pieces = player.getPieces();
		Piece[] electablePieces = new Piece[pieces.length];
		int numElectablePieces = 0;

		for (int i = 0; i < pieces.length; i++) {
			if (!board.isAtParchoca(pieces[i])) {
				electablePieces[numElectablePieces] = pieces[i];
				numElectablePieces++;
			}
		}

		if (numElectablePieces > 0) {
			Random rand = new Random();
			return electablePieces[rand.nextInt(numElectablePieces)];
		} else {
			return null;
		}
	}
}
