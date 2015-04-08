package nl.saxion.act.playground.model;

import nl.voorbeeld.SoZ.Savegame;

/**
 * Superclass for all games. 
* 
 * You should subclass this for your own game. In that subclass you will keep
 * track of all game-related state, like the score, who's turn it is, etc.
 * 
 * @author Team Wild Ponies
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

	/*public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		g.drawImage(image, 0, 0, this); Pak locatie 

	}

	/*public void paint(Graphics g) {
		//g.drawImage(character, Player.getCenterX() - 61,
				//Player.getCenterY() - 63, this); locatie pakken
		ArrayList projectiles = Player.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 10, 5);
		}
		
	}*/
}
