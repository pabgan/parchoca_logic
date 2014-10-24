
package test;

import game.Board;
import game.Piece;
import game.Piece.Color;
import game.Player;
import game.StupidPlayerStrategy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author TCPsi
 */
public class PlayerTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerNullName() {
        Player player = new Player(null, null, 0);
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerEmptyName() {
        Player player = new Player("", null, 0);
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerNullColor() {
        Player player = new Player("Test", null, 0);
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerZeroPieces() {
        Player player = new Player("Test", Color.blue, 0);
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerNegativePieces() {
        Player player = new Player("Test", Color.blue, Integer.MIN_VALUE);
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int)}.
     */
    @Test
    public void testPlayerOnePiece() {
        String playerName = "Test";
        Player player = new Player(playerName, Color.blue, 1);
        Assert.assertEquals(player.getName(), playerName);
        Assert.assertEquals(Color.blue, player.getColor());
        Assert.assertEquals(0, player.getPenalty());
        Piece[] pieces = player.getPieces();
        Assert.assertNotNull(pieces);
        Assert.assertEquals(1, pieces.length);
        Assert.assertNotNull(pieces[0]);
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int)}.
     */
    @Test
    public void testPlayerTenPieces() {
        String playerName = "Test";
        Player player = new Player(playerName, Color.blue, 10);
        Assert.assertEquals(player.getName(), playerName);
        Assert.assertEquals(Color.blue, player.getColor());
        Assert.assertEquals(0, player.getPenalty());
        Piece[] pieces = player.getPieces();
        Assert.assertNotNull(pieces);
        Assert.assertEquals(10, pieces.length);

        for (Piece piece : pieces) {
            Assert.assertNotNull(piece);
        }
    }

    /**
     * Test method for {@link game.Player#Player(java.lang.String, game.Piece.Color, int, game.IPlayerStrategy)}.
     */
    @Test
    public void testPlayerStrategyNull() {
        String playerName = "Test";
        int numPieces = 4;
        Player player = new Player(playerName, Color.blue, numPieces, null);
        Assert.assertEquals(player.getName(), playerName);
        Assert.assertEquals(Color.blue, player.getColor());
        Assert.assertEquals(0, player.getPenalty());
        Piece[] pieces = player.getPieces();
        Assert.assertNotNull(pieces);
        Assert.assertEquals(numPieces, pieces.length);

        for (Piece piece : pieces) {
            Assert.assertNotNull(piece);
        }
    }

    /**
     * Test method for {@link game.Player#setPenalty(int)}.
     */
    @Test
    public void testSetPenaltyZero() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, null);

        player.setPenalty(0);
        Assert.assertEquals(0, player.getPenalty());
    }

    /**
     * Test method for {@link game.Player#setPenalty(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPenaltyMinimum() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, null);

        player.setPenalty(Integer.MIN_VALUE);
    }

    /**
     * Test method for {@link game.Player#setPenalty(int)}.
     */
    @Test
    public void testSetPenaltyMaximum() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, null);

        player.setPenalty(Integer.MAX_VALUE);
        Assert.assertEquals(Integer.MAX_VALUE, player.getPenalty());
    }

    @Test
    public void testDiscountAndGetPenalty() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, null);

        player.setPenalty(3);
        Assert.assertEquals(3, player.getPenaltyAndDiscount());
        Assert.assertEquals(2, player.getPenaltyAndDiscount());
        Assert.assertEquals(1, player.getPenaltyAndDiscount());
        Assert.assertEquals(0, player.getPenaltyAndDiscount());
    }

    /**
     * Test method for {@link game.Player#selectPieceToMove(game.Board, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectPieceToMoveNullBard() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, null);
        player.selectPieceToMove(null, 0);
    }

    /**
     * Test method for {@link game.Player#selectPieceToMove(game.Board, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectPieceToMove0Jumps() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, null);
        player.selectPieceToMove(new Board(), 0);
    }

    /**
     * Test method for {@link game.Player#selectPieceToMove(game.Board, int)}.
     */
    @Test
    public void testSelectPieceToMoveMaxJumps() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, new StupidPlayerStrategy());
        Assert.assertEquals(player.getPieces()[0], player.selectPieceToMove(new Board(), Integer.MAX_VALUE));
    }

    /**
     * Test method for {@link game.Player#selectPieceToMove(game.Board, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSelectPieceToMoveMinJumps() {
        String playerName = "Test";
        int numPieces = 1;
        Player player = new Player(playerName, Color.blue, numPieces, null);
        player.selectPieceToMove(new Board(), Integer.MIN_VALUE);
    }

}
