
package test;

import game.Board;
import game.Piece;
import game.Piece.Color;
import game.Player;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author pganuza
 */
public class BoardTest {
    private Board board;
    Player playerBlue;
    Piece pieceBlue0;
    Piece pieceBlue1;
    Piece pieceBlue2;
    Piece pieceBlue3;
    Player playerGreen;
    Piece pieceGreen0;
    Piece pieceGreen1;
    Piece pieceGreen2;
    Piece pieceGreen3;
    Player playerRed;
    Piece pieceRed0;
    Piece pieceRed1;
    Piece pieceRed2;
    Piece pieceRed3;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        board = new Board();
        playerBlue = new Player("Blue", Color.blue, 4);
        pieceBlue0 = playerBlue.getPieces()[0];
        pieceBlue1 = playerBlue.getPieces()[1];
        pieceBlue2 = playerBlue.getPieces()[2];
        pieceBlue3 = playerBlue.getPieces()[3];
        playerGreen = new Player("Green", Color.green, 4);
        pieceGreen0 = playerGreen.getPieces()[0];
        pieceGreen1 = playerGreen.getPieces()[1];
        pieceGreen2 = playerGreen.getPieces()[2];
        pieceGreen3 = playerGreen.getPieces()[3];
        playerRed = new Player("Red", Color.red, 4);
        pieceRed0 = playerRed.getPieces()[0];
        pieceRed1 = playerRed.getPieces()[1];
        pieceRed2 = playerRed.getPieces()[2];
        pieceRed3 = playerRed.getPieces()[3];
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
        Assert.assertEquals(63, pieceBlue0.getSquare().getNumber());
        Assert.assertTrue(board.isAtParchoca(pieceBlue0));
        board.killPiece(pieceBlue0);
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMoveNullPiece() {
        board.move(null, 0);
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMove0Jumps() {
        board.move(pieceBlue0, 0);
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMoveNegativeJumps() {
        board.move(pieceBlue0, -1);
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMoveToParchoca() {
        board.addPiece(pieceBlue0);
        board.move(pieceBlue0, 63);
        Assert.assertEquals(63, pieceBlue0.getSquare().getNumber());
        Assert.assertTrue(board.isAtParchoca(pieceBlue0));
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMovePieceAtParchoca() {
        board.addPiece(pieceBlue0);
        board.move(pieceBlue0, 63);
        board.move(pieceBlue0, 1);
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMovePieceToLinkedSquare() {
        board.addPiece(pieceBlue0);
        board.move(pieceBlue0, 1);
        Assert.assertEquals(5, pieceBlue0.getSquare().getNumber());
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMovePieceToLinkedSquareBackwards() {
        board.addPiece(pieceBlue0);
        board.move(pieceBlue0, 12);
        Assert.assertEquals(6, pieceBlue0.getSquare().getNumber());
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMovePieceToLinkedSquareThrougWall() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        board.addPiece(pieceBlue2);
        board.move(pieceBlue0, 2);
        board.move(pieceBlue1, 2);
        board.move(pieceBlue2, 2);
        Assert.assertEquals(5, pieceBlue2.getSquare().getNumber());
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMoveBounceBetweenWalls() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        board.addPiece(pieceBlue2);
        board.addPiece(pieceGreen0);
        board.addPiece(pieceGreen1);
        board.move(pieceBlue0, 4);
        board.move(pieceBlue1, 4);
        board.move(pieceBlue2, 3);
        board.move(pieceGreen0, 2);
        board.move(pieceGreen1, 2);
        board.move(pieceBlue2, 5);
        Assert.assertEquals(3, pieceBlue2.getSquare().getNumber());
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMoveBounceBetweenWallsAndKill() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        board.addPiece(pieceBlue2);
        board.addPiece(pieceGreen0);
        board.addPiece(pieceGreen1);
        board.addPiece(pieceGreen2);
        board.addPiece(pieceGreen3);
        board.addPiece(pieceRed0);
        board.move(pieceBlue0, 4);
        board.move(pieceBlue1, 4);
        board.move(pieceBlue2, 3);
        board.move(pieceRed0, 2);
        board.move(pieceGreen0, 1);
        board.move(pieceGreen1, 1);
        board.move(pieceGreen2, 1);
        board.move(pieceGreen3, 1);
        Assert.assertEquals(5, pieceGreen0.getSquare().getNumber());
        Assert.assertEquals(5, pieceGreen1.getSquare().getNumber());
        Assert.assertEquals(1, pieceGreen2.getSquare().getNumber());
        Assert.assertEquals(1, pieceGreen3.getSquare().getNumber());
        Assert.assertEquals(20, board.move(pieceBlue2, 3));
        Assert.assertEquals(2, pieceBlue2.getSquare().getNumber());
        Assert.assertTrue(board.isAtHome(pieceRed0));
        Assert.assertEquals(0, pieceRed0.getSquare().getNumber());
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMoveBounceBetweenHomeAndWall() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        board.addPiece(pieceBlue2);
        board.addPiece(pieceGreen0);
        board.addPiece(pieceGreen1);
        board.move(pieceBlue0, 2);
        board.move(pieceBlue1, 2);
        board.move(pieceBlue2, 2);
        Assert.assertEquals(5, pieceBlue2.getSquare().getNumber());
    }

    /**
     * Test method for {@link game.Board#move(game.Piece, int)}.
     */
    @Test
    public void testMoveBounceBetweenWallAndParchoca() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        board.addPiece(pieceBlue2);
        board.move(pieceBlue0, 62);
        board.move(pieceBlue1, 61);
        board.move(pieceBlue2, 61);
        board.move(pieceBlue0, 2);
        Assert.assertEquals(62, pieceBlue0.getSquare().getNumber());
        board.move(pieceBlue0, 4);
        Assert.assertEquals(62, pieceBlue0.getSquare().getNumber());
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsSquareEmptyNegativeNumber() {
        board.isSquareEmpty(-1);
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsSquareEmptyOutOfBoard() {
        board.isSquareEmpty(64);
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareEmptyNoPiecesOnBoard() {
        Assert.assertTrue(board.isSquareEmpty(0));
        Assert.assertTrue(board.isSquareEmpty(10));
        Assert.assertTrue(board.isSquareEmpty(63));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareEmpty0Pieces() {
        board.addPiece(pieceBlue0);
        Assert.assertFalse(board.isSquareEmpty(0));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareEmpty1PieceMovesOut() {
        board.addPiece(pieceBlue0);
        Assert.assertFalse(board.isSquareEmpty(0));
        Assert.assertTrue(board.isSquareEmpty(10));
        board.move(pieceBlue0, 10);
        Assert.assertTrue(board.isSquareEmpty(0));
        Assert.assertFalse(board.isSquareEmpty(10));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareEmptyDisolvingWall() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        Assert.assertFalse(board.isSquareEmpty(0));
        Assert.assertTrue(board.isSquareEmpty(10));
        board.move(pieceBlue0, 10);
        Assert.assertFalse(board.isSquareEmpty(10));
        board.move(pieceBlue1, 10);
        Assert.assertTrue(board.isSquareEmpty(0));
        Assert.assertFalse(board.isSquareEmpty(10));
        board.move(pieceBlue0, 10);
        Assert.assertFalse(board.isSquareEmpty(10));
        board.move(pieceBlue1, 10);
        Assert.assertTrue(board.isSquareEmpty(10));
        Assert.assertFalse(board.isSquareEmpty(20));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareEmptyAfterKill() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceGreen0);
        Assert.assertFalse(board.isSquareEmpty(0));
        Assert.assertTrue(board.isSquareEmpty(10));
        board.move(pieceBlue0, 10);
        Assert.assertFalse(board.isSquareEmpty(0));
        Assert.assertFalse(board.isSquareEmpty(10));
        board.move(pieceGreen0, 10);
        Assert.assertFalse(board.isSquareEmpty(0));
        Assert.assertFalse(board.isSquareEmpty(10));
        board.move(pieceGreen0, 10);
        Assert.assertFalse(board.isSquareEmpty(0));
        Assert.assertTrue(board.isSquareEmpty(10));
        Assert.assertFalse(board.isSquareEmpty(20));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsSquareAWallNegativeNumber() {
        board.isSquareAWall(-1);
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsSquareAWallOutOfBoard() {
        board.isSquareAWall(64);
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareAWallNoPiecesOnBoard() {
        Assert.assertFalse(board.isSquareAWall(0));
        Assert.assertFalse(board.isSquareAWall(10));
        Assert.assertFalse(board.isSquareAWall(63));
    }

    /**
     * Test method for {@link game.Board#isSquareAWall(int)}.
     */
    @Test
    public void testIsSquareAWallEmptySquare() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        Assert.assertFalse(board.isSquareAWall(0));
        Assert.assertFalse(board.isSquareAWall(10));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareAWallDisolvingWall() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        Assert.assertFalse(board.isSquareAWall(0));
        Assert.assertFalse(board.isSquareAWall(10));
        board.move(pieceBlue0, 10);
        Assert.assertFalse(board.isSquareAWall(10));
        board.move(pieceBlue1, 10);
        Assert.assertFalse(board.isSquareAWall(0));
        Assert.assertTrue(board.isSquareAWall(10));
        board.move(pieceBlue0, 10);
        Assert.assertFalse(board.isSquareAWall(10));
        board.move(pieceBlue1, 10);
        Assert.assertFalse(board.isSquareAWall(10));
        Assert.assertTrue(board.isSquareAWall(20));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsSquareAWallAfterKill() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceGreen0);
        Assert.assertFalse(board.isSquareAWall(0));
        Assert.assertFalse(board.isSquareAWall(10));
        board.move(pieceBlue0, 10);
        Assert.assertFalse(board.isSquareAWall(10));
        board.move(pieceGreen0, 10);
        Assert.assertFalse(board.isSquareAWall(0));
        Assert.assertFalse(board.isSquareAWall(10));
        board.move(pieceGreen0, 10);
        Assert.assertFalse(board.isSquareAWall(10));
        Assert.assertFalse(board.isSquareAWall(20));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsHomeSquareAWall() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        Assert.assertFalse(board.isSquareAWall(0));
    }

    /**
     * Test method for {@link game.Board#isSquareEmpty(int)}.
     */
    @Test
    public void testIsParchocaSquareAWall() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceBlue1);
        board.move(pieceBlue0, 63);
        board.move(pieceBlue1, 63);
        Assert.assertFalse(board.isSquareAWall(63));
    }

    /**
     * Test method for {@link game.Board#isAtHome(game.Piece)}.
     */
    @Test
    public void testIsAtHome() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceGreen0);
        Assert.assertTrue(board.isAtHome(pieceBlue0));
        Assert.assertTrue(board.isAtHome(pieceGreen0));
        board.move(pieceBlue0, 1);
        Assert.assertFalse(board.isAtHome(pieceBlue0));
        Assert.assertTrue(board.isAtHome(pieceGreen0));
        board.move(pieceGreen0, 1);
        Assert.assertTrue(board.isAtHome(pieceBlue0));
        Assert.assertFalse(board.isAtHome(pieceGreen0));
    }

    /**
     * Test method for {@link game.Board#isAtParchoca(game.Piece)}.
     */
    @Test
    public void testIsAtParchoca() {
        board.addPiece(pieceBlue0);
        board.addPiece(pieceGreen0);
        Assert.assertFalse(board.isAtParchoca(pieceBlue0));
        Assert.assertFalse(board.isAtParchoca(pieceGreen0));
        board.move(pieceBlue0, 63);
        Assert.assertTrue(board.isAtParchoca(pieceBlue0));
        Assert.assertFalse(board.isAtParchoca(pieceGreen0));
        board.move(pieceGreen0, 63);
        Assert.assertTrue(board.isAtParchoca(pieceBlue0));
        Assert.assertTrue(board.isAtParchoca(pieceGreen0));
    }
}
