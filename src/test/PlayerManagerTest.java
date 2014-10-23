package test;

import static org.junit.Assert.fail;
import game.Piece.Color;
import game.Player;
import game.PlayerManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerManagerTest {
	Player playerBlue;
	Player playerGreen;
	Player playerRed;

	@Before
	public void setUp() throws Exception {
		playerBlue = new Player("Blue", Color.blue, 4);
		playerGreen = new Player("Green", Color.green, 4);
		playerRed = new Player("Red", Color.red, 4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
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
		Assert.assertEquals(playerRed, playerManager.nextPlayer());
		Assert.assertEquals(playerBlue, playerManager.nextPlayer());
		Assert.assertFalse(playerManager.playerHasFinished());
		Assert.assertEquals(playerGreen, playerManager.nextPlayer());
		Assert.assertEquals(playerGreen, playerManager.nextPlayer());
		Assert.assertTrue(playerManager.playerHasFinished());
		
	}
}
