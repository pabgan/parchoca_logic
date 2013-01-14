/**
 * 
 */
package tests;

import game.Color;
import game.Piece;
import game.Player;
import game.Position;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author pganuza
 * 
 */
public class PositionTest {

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

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
     * Test method for {@link game.Position#Position(int)}.
     */
    @Test
    public void testPosition0() {
        Position p = new Position(0);

        Assert.assertEquals(0, p.getNumber());
    }

    /**
     * Test method for {@link game.Position#Position(int)}.
     */
    @Test
    public void testPositionMinInt() {
        Position p = new Position(Integer.MIN_VALUE);

        Assert.assertEquals(Integer.MIN_VALUE, p.getNumber());
    }

    /**
     * Test method for {@link game.Position#Position(int)}.
     */
    @Test
    public void testPositionMaxInt() {
        Position p = new Position(Integer.MAX_VALUE);

        Assert.assertEquals(Integer.MAX_VALUE, p.getNumber());
    }

    /**
     * Test method for {@link game.Position#isPositionEmpty()}.
     */
    @Test
    public void testIsPositionEmptyZeroPieces() {
        Position p = new Position(0);

        Assert.assertTrue(p.isPositionEmpty());
    }

    /**
     * Test method for {@link game.Position#isPositionEmpty()}.
     */
    @Test
    public void testIsPositionEmptyOnePiece() {
        Position po = new Position(0);
        Piece pi = new Piece();
        po.move(pi);

        Assert.assertFalse(po.isPositionEmpty());
    }

    /**
     * 
     * Test method for {@link game.Position#isPositionEmpty()}.
     */
    @Test
    public void testIsPositionEmptyTwoPieces() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);

        po.move(pi1);
        po.move(pi2);

        Assert.assertFalse(po.isPositionEmpty());
    }

    /**
     * 
     * Test method for {@link game.Position#isPositionEmpty()}.
     */
    @Test
    public void testIsPositionEmptyAfterKill() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.yellow);

        po.move(pi1);
        po.move(pi2);

        Assert.assertFalse(po.isPositionEmpty());
    }

    /**
     * Test method for {@link game.Position#isPositionEmpty()}.
     */
    @Test
    public void testIsPositionEmptyRemovingPieces() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);

        po.move(pi1);
        po.move(pi2);
        po.remove();

        Assert.assertFalse(po.isPositionEmpty());

        po.remove();

        Assert.assertTrue(po.isPositionEmpty());
    }

    /**
     * Test method for {@link game.Position#isPositionWall()}.
     */
    @Test
    public void testIsPositionWallZeroPieces() {
        Position po = new Position(0);

        Assert.assertFalse(po.isPositionWall());
    }

    /**
     * Test method for {@link game.Position#isPositionWall()}.
     */
    @Test
    public void testIsPositionWallOnePiece() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);

        po.move(pi1);

        Assert.assertFalse(po.isPositionWall());
    }

    /**
     * Test method for {@link game.Position#isPositionWall()}.
     */
    @Test
    public void testIsPositionWallTwoPieces() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);

        po.move(pi1);
        po.move(pi2);

        Assert.assertTrue(po.isPositionWall());
    }

    /**
     * Test method for {@link game.Position#isPositionWall()}.
     */
    @Test
    public void testIsPositionWallAfterKill() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.yellow);

        po.move(pi1);
        po.move(pi2);

        Assert.assertFalse(po.isPositionWall());
    }

    /**
     * Test method for {@link game.Position#isPositionWall()}.
     */
    @Test
    public void testIsPositionWallRemovingPieces() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);

        po.move(pi1);
        po.move(pi2);
        po.remove();

        Assert.assertFalse(po.isPositionWall());

        po.remove();

        Assert.assertFalse(po.isPositionWall());
    }

    /**
     * Test method for {@link game.Position#whoIsIn()}.
     */
    @Test
    public void testWhoIsInEmpty() {
        Position po = new Position(0);

        Assert.assertNull(po.whoIsIn());
    }

    /**
     * Test method for {@link game.Position#whoIsIn()}.
     */
    @Test
    public void testWhoIsInOnePiece() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);

        po.move(pi1);

        Assert.assertEquals(Color.blue, po.whoIsIn());
    }

    /**
     * Test method for {@link game.Position#whoIsIn()}.
     */
    @Test
    public void testWhoIsInTwoPieces() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);

        po.move(pi1);
        po.move(pi2);

        Assert.assertEquals(Color.blue, po.whoIsIn());
    }

    /**
     * Test method for {@link game.Position#whoIsIn()}.
     */
    @Test
    public void testWhoIsInAfterKill() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.yellow);

        po.move(pi1);
        po.move(pi2);

        Assert.assertEquals(Color.yellow, po.whoIsIn());
    }

    /**
     * Test method for {@link game.Position#whoIsIn()}.
     */
    @Test
    public void testWhoIsInRemovingPieces() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);

        po.move(pi1);
        po.move(pi2);
        po.remove();
        Assert.assertEquals(Color.blue, po.whoIsIn());

        po.remove();
        Assert.assertNull(po.whoIsIn());
    }

    /**
     * Test method for {@link game.Position#move(game.Piece)}.
     */
    @Test
    public void testMoveToEmptyPosition() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = null;

        pi2 = po.move(pi1);

        Assert.assertNull(pi2);
        Assert.assertFalse(po.isPositionEmpty());
        Assert.assertFalse(po.isPositionWall());
    }

    /**
     * Test method for {@link game.Position#move(game.Piece)}.
     */
    @Test
    public void testMoveToCreateWall() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);
        Piece pi3 = null;

        po.move(pi1);
        pi3 = po.move(pi2);

        Assert.assertNull(pi3);
        Assert.assertFalse(po.isPositionEmpty());
        Assert.assertTrue(po.isPositionWall());
    }

    /**
     * Test method for {@link game.Position#move(game.Piece)}.
     */
    @Test
    public void testMoveToKill() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.yellow);
        Piece pi3 = null;

        po.move(pi1);
        pi3 = po.move(pi2);

        Assert.assertEquals(pi1, pi3);
        Assert.assertFalse(po.isPositionEmpty());
        Assert.assertFalse(po.isPositionWall());
        Assert.assertEquals(Color.yellow, po.whoIsIn());
    }

    /**
     * Test method for {@link game.Position#move(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testMoveToWallSameColor() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);
        Piece pi3 = new Piece(new Player(), Color.blue);

        po.move(pi1);
        po.move(pi2);
        po.move(pi3);
    }

    /**
     * Test method for {@link game.Position#move(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testMoveToWallDifferentColor() {
        Position po = new Position(0);
        Piece pi1 = new Piece(new Player(), Color.blue);
        Piece pi2 = new Piece(new Player(), Color.blue);
        Piece pi3 = new Piece(new Player(), Color.yellow);

        po.move(pi1);
        po.move(pi2);
        po.move(pi3);
    }

    /**
     * Test method for {@link game.Position#move(game.Piece)}.
     */
    @Test(expected = IllegalStateException.class)
    public void testRemoveEmpty() {
        Position po = new Position(0);

        po.remove();
    }
}
