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
	private static int lastId=0;
	private int id;

	/**
	 * Constructs an enemy.
	 */
	public Enemy() {
		super();
		int getal = (int) (Math.random() * 3);

		id = lastId;
		lastId++;
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
	}

	public void callMovement(GameBoard gameBoard) {

		Log.d(SoZGame.TAG, "Moved Enemy");

		int newPosX = getPositionX();
		int newPosY = getPositionY() + 1;

		if (gameBoard.getObject(newPosX, newPosY) == null) {
			gameBoard.moveObject(this, newPosX, newPosY);
			// Move player to the new position and redraw the app

			gameBoard.updateView();
			if (newPosY >= gameBoard.getHeight()-2) {
				((SoZGame) gameBoard.getGame()).gameOver();
				((SoZGame) gameBoard.getGame()).getActivity().finish();
			}
		} else if (gameBoard.getObject(newPosX, newPosY) instanceof Muur) {
			((Muur) gameBoard.getObject(newPosX, newPosY))
					.muurDamaged(gameBoard);
		} else if (gameBoard.getObject(newPosX, newPosY) instanceof MachinegunBullet){
			gameBoard.removeObject(this);
			((SoZGame)gameBoard.getGame()).RemoveEnemy(this);
			gameBoard.removeObject(gameBoard.getObject(newPosX, newPosY));
		} else if (gameBoard.getObject(newPosX, newPosY) instanceof ShotgunBullet){
			gameBoard.removeObject(this);
			((SoZGame)gameBoard.getGame()).RemoveEnemy(this);
			gameBoard.removeObject(gameBoard.getObject(newPosX, newPosY));
		}

	}

	@Override
	public void run() {
		callMovement(Game.gameBoard);

	}
	
	public int getId(){
		return id;
	}
}