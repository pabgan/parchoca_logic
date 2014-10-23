package game;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerManager {
    private Iterator<Player> playersCurrentlyPlayingIterator;
    private Player currentPlayer;
    private final ArrayList<Player> players;
    private final ArrayList<Player> playersCurrentlyPlaying;

    public PlayerManager() {
        players = new ArrayList<Player>();
        playersCurrentlyPlaying = new ArrayList<Player>();
    }

    public void addPlayer(final Player player) {
        if (players.contains(player)) {
            throw new IllegalArgumentException("Can't add same player twice");
        }
        players.add(player);
        playersCurrentlyPlaying.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int size() {
        return players.size();
    }
    
    public boolean playerHasFinished() {
    	int currentPlayerIndex = playersCurrentlyPlaying.indexOf(currentPlayer);
    	playersCurrentlyPlaying.remove(currentPlayer);
    	
    	if (playersCurrentlyPlaying.isEmpty()) {
    		return true;
    	} else {
    		playersCurrentlyPlayingIterator = playersCurrentlyPlaying.iterator();
    		
    		for (int i = 0; i < currentPlayerIndex; i++) {
    			currentPlayer = playersCurrentlyPlayingIterator.next();
    		}
    		
    		return false;
    	}
    }

    public Player nextPlayer() {
        if (players.isEmpty()) {
            throw new IllegalStateException("Can't select next player beacuse there ara no players");
        }
        if (playersCurrentlyPlayingIterator == null) {
            this.playersCurrentlyPlayingIterator = playersCurrentlyPlaying.iterator();
        }
        if (playersCurrentlyPlayingIterator.hasNext()) {
        	currentPlayer = playersCurrentlyPlayingIterator.next();
        } else {
            playersCurrentlyPlayingIterator = players.iterator();
            currentPlayer = playersCurrentlyPlayingIterator.next();
        }
        
        return currentPlayer;
    }
}
