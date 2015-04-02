package nl.saxion.act.playground.model;

import nl.voorbeeld.SoZ.Savegame;

/**
 * Superclass for all games. 
* 
 * You should subclass this for your own game. In that subclass you will keep
 * track of all game-related state, like the score, who's turn it is, etc.
 * 
 * @author Paul de Groot
 */
public abstract class Game {
	public static GameBoard gameBoard;
	public static Savegame savegame;

	/**
	 * Called when you create a new game.
	 * @param gameBoard
	 */
	public Game(GameBoard gameBoard, Savegame savegame) {
		Game.savegame=savegame;
		Game.gameBoard = gameBoard;
		gameBoard.setGame(this);
	}

	/** Returns a reference to the game board. */
	public GameBoard getGameBoard() {
		return gameBoard;
	}
}
