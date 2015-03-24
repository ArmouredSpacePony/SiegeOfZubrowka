package nl.voorbeeld.coolgame.objects;

import android.util.Log;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;
import nl.voorbeeld.coolgame.CoolGame;

public class Player extends GameObject {
	public static final String PLAYER_IMAGE = "playertemplate";

	/** Returns the ImageId of the image to show. */
	@Override
	public String getImageId() {
		return PLAYER_IMAGE;
	}

	/** Called when the user touched this wombat. */
	@Override
	public void onTouched(GameBoard gameBoard) {
		Log.d(CoolGame.TAG, "Touched wombat");

		// Wombats always move a square to the right
		int newPosX = getPositionX();
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX >= gameBoard.getWidth()) {
			return;
		}

		// Check if there is a object on the new position
		GameObject objectAtNewPos = gameBoard.getObject(newPosX, newPosY);
		if (objectAtNewPos != null) {

			// Wombats can't move through rocks
			if (objectAtNewPos instanceof Enemy) {
				return;
			}

			// Caught a leaf? Score!
			if (objectAtNewPos instanceof Leaf) {
				gameBoard.removeObject(objectAtNewPos);
				((CoolGame) gameBoard.getGame()).changeScore();
			}
		}

		// Move wombat to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}

	public void moveLeft(GameBoard gameBoard) {
		Log.d(CoolGame.TAG, "Moved Player");

		// player moves to the left
		int newPosX = getPositionX() - 1;
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX < 0) {
			return;
		}

		// Move player to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}

	public void moveRight(GameBoard gameBoard) {
		Log.d(CoolGame.TAG, "Moved Player");

		// player moves to the right
		int newPosX = getPositionX() + 1;
		int newPosY = getPositionY();

		// If new position is over the edge of the board, do nothing
		if (newPosX > 8) {
			return;
		}

		// Move player to the new position and redraw the app
		gameBoard.moveObject(this, newPosX, newPosY);
		gameBoard.updateView();
	}

}
