/**
 * 
 */

package test;

import static org.junit.Assert.fail;
import game.Board;
import game.Piece;
import game.Piece.Color;
import game.Player;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author pganuza
 */
public class BoardTest {
    private Board board;
    Player playerBlue = new Player("Blue", Color.blue, 4);
    Piece pieceBlue0 = playerBlue.getPieces()[0];
    Piece pieceBlue1 = playerBlue.getPieces()[1];
    Piece pieceBlue2 = playerBlue.getPieces()[2];
    Piece pieceBlue3 = playerBlue.getPieces()[3];
    Player playerGreen = new Player("Green", Color.green, 4);
    Piece pieceGreen0 = playerGreen.getPieces()[0];
    Piece pieceGreen1 = playerGreen.getPieces()[1];
    Piece pieceGreen2 = playerGreen.getPieces()[2];
    Piece pieceGreen3 = playerGreen.getPieces()[3];
    Player playerRed = new Player("Red", Color.red, 4);
    Piece pieceRed0 = playerRed.getPieces()[0];
    Piece pieceRed1 = playerRed.getPieces()[1];
    Piece pieceRed2 = playerRed.getPieces()[2];
    Piece pieceRed3 = playerRed.getPieces()[3];

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link game.Board#addPiece(game.Piece)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPieceNull() {
        Assert.assertFalse(board.addPiece(null));
    }

    /**
     * Test method for {@link game.Board#addPiece(game.Piece)}.
     */
    @Test
    public void testAddPiece() {
        Assert.assertTrue(board.addPiece(pieceBlue0));
    }

    /**
     * Test method for {@link game.Board#addPiece(game.Piece)}.
     */
    @Test
    public void testAddPiece2SameColor() {
        Assert.assertTrue(board.addPiece(pieceBlue0));
        Assert.assertTrue(board.addPiece(pieceBlue1));
    }

    /**
     * Test method for {@link game.Board#addPiece(game.Piece)}.
     */
    @Test
    public void testAddPiece2DifferentColor() {
        Assert.assertTrue(board.addPiece(pieceBlue0));
        Assert.assertTrue(board.addPiece(pieceGreen0));
    }

    /**
     * Test method for {@link game.Board#addPiece(game.Piece)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPieceTwice() {
        Assert.assertTrue(board.addPiece(pieceBlue0));
        board.addPiece(pieceBlue0);
    }

    /**
     * Test method for {@link game.Board#killPiece(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testKillPieceAtHome() {
        Assert.assertTrue(board.addPiece(pieceBlue0));
        board.killPiece(pieceBlue0);
    }

    /**
     * Test method for {@link game.Board#killPiece(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testKillPieceAtParchoca() {
        Assert.assertTrue(board.addPiece(pieceBlue0));
        board.move(pieceBlue0, 63);
        board.killPiece(pieceBlue0);
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMove() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareEmpty() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link game.Board#isSquareAWall(int)}.
     */
    @Test
    public void testIsSquareAWall() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link game.Board#whoIsAtHome()}.
     */
    @Test
    public void testWhoIsAtHome() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link game.Board#whoIsAtParchoca()}.
     */
    @Test
    public void testWhoIsAtParchoca() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link game.Board#whereIs(game.Piece)}.
     */
    @Test
    public void testWhereIs() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link game.Board#isAtHome(game.Piece)}.
     */
    @Test
    public void testIsAtHome() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link game.Board#isAtParchoca(game.Piece)}.
     */
    @Test
    public void testIsAtParchoca() {
        fail("Not yet implemented");
    }

}
