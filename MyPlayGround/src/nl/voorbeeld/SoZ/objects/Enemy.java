package nl.voorbeeld.SoZ.objects;

import android.util.Log;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;
import nl.voorbeeld.SoZ.SoZGame;
import nl.saxion.act.playground.model.Game;

public class Enemy extends GameObject implements Runnable {
	public static final String Enemy1_IMAGE = "civilian3";
	public static final String Enemy2_IMAGE = "civilian4";
	public static final String Enemy3_IMAGE = "civilian5";
	private int currentImage;

	/**
	 * Constructs an enemy.
	 */
	public Enemy() {
		super();
		int getal = (int) (Math.random() * 3);

		currentImage = getal;
	}

	/** Returns the ImageId of the image to show. */

	@Override
	public String getImageId() {
		if (currentImage == 1) {
			return Enemy1_IMAGE;
		} else if (currentImage == 2) {
			return Enemy2_IMAGE;
		} else {
			return Enemy3_IMAGE;
		}

	}

	/** Called when the user touched this rock. */
	@Override
	public void onTouched(GameBoard gameBoard) {
		// placeholder until movement functions
		Log.d(SoZGame.TAG, "Touched Enemy");
		callMovement(gameBoard);

		// redBlock = !redBlock;
		gameBoard.updateView();
	}

	public void callMovement(GameBoard gameBoard) {

		Log.d(SoZGame.TAG, "Moved Enemy");

		int newPosX = getPositionX();
		int newPosY = getPositionY() + 1;

		if (gameBoard.getObject(newPosX, newPosY) == null) {
			gameBoard.moveObject(this, newPosX, newPosY);
			// Move player to the new position and redraw the app

			gameBoard.updateView();
			if (newPosY > gameBoard.getHeight()-2) {
				((SoZGame) gameBoard.getGame()).gameOver();
			}
		} else if (gameBoard.getObject(newPosX, newPosY) instanceof Muur) {
			((Muur) gameBoard.getObject(newPosX, newPosY))
					.muurDamaged(gameBoard);
		}

	}

	@Override
	public void run() {
		callMovement(Game.gameBoard);

	}
}
