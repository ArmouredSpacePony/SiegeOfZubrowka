package nl.voorbeeld.SoZ;

import nl.saxion.act.playground.model.GameBoard;

/**
 * The game board for Siege of Zubrowka.
 * 
 * @author team Wild Ponies
 */
public class SoZBoard extends GameBoard {
	private static final int GAMEBOARD_WIDTH = 9;
	private static final int GAMEBOARD_HEIGHT = 18;

	/**
	 * Create a new game board.
	 */
	public SoZBoard() {
		super(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);
	}

	@Override
	public void onEmptyTileClicked(int x, int y) {
		// Nothing to do in this game.
	}
	
	@Override
	public String getBackgroundImg(int mx, int my) {
		return null;
	}

	
}
