package game;

public class StupidPlayerStrategy implements IPlayerStrategy {

	@Override
	public Piece selectMove(final Player player, final Board board, final int jumps) {
		Piece[] pieces = player.getPieces();

		for (Piece piece : pieces) {
			if (!board.isAtParchoca(piece)) {
				return piece;
			}
		}

		return null;
	}
}
