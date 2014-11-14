
package test;

import game.Piece.Color;
import game.Player;
import game.PlayerManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;

public class PlayerManagerTest {
    Player playerBlue;
    Player playerGreen;
    Player playerRed;

    @Before
    public void setUp() throws Exception {
        playerBlue = new Player("Blue", Color.BLUE, 4);
        playerGreen = new Player("Green", Color.GREEN, 4);
        playerRed = new Player("Red", Color.RED, 4);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testNextPlayer1Player() {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer(playerBlue);
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
    }

    @Test
    public void testNextPlayer3Player() {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer(playerBlue);
        playerManager.addPlayer(playerGreen);
        playerManager.addPlayer(playerRed);
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerRed, playerManager.nextPlayer());
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerRed, playerManager.nextPlayer());
    }

    @Test
    public void testPlayerHasFinished() {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer(playerBlue);
        playerManager.addPlayer(playerGreen);
        playerManager.addPlayer(playerRed);
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerRed, playerManager.nextPlayer());
        Assert.assertFalse(playerManager.playerHasFinished());
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertFalse(playerManager.playerHasFinished());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertTrue(playerManager.playerHasFinished());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void testAddPlayerInopportune() {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer(playerBlue);
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        playerManager.addPlayer(playerGreen);
        playerManager.nextPlayer();
    }

    @Test
    public void testNextPlayerWithPenalties() {
        PlayerManager playerManager = new PlayerManager();
        playerManager.addPlayer(playerBlue);
        playerManager.addPlayer(playerGreen);
        playerManager.addPlayer(playerRed);
        playerBlue.setPenalty(3);
        playerGreen.setPenalty(2);

        Assert.assertEquals(playerRed, playerManager.nextPlayer());
        Assert.assertEquals(playerRed, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerRed, playerManager.nextPlayer());
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerRed, playerManager.nextPlayer());
        Assert.assertEquals(playerBlue, playerManager.nextPlayer());
        Assert.assertEquals(playerGreen, playerManager.nextPlayer());
        Assert.assertEquals(playerRed, playerManager.nextPlayer());

    }
}
