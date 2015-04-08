package nl.voorbeeld.SoZ.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.voorbeeld.SoZ.SoZGame;

public class ShotgunBullet extends Projectile {
	public static final String SHOTGUN_IMAGE = "Shotgun";
	public boolean bestaat= true;

	/** Returns the ImageId of the image to show. */
	@Override
	public String getImageId() {
		return SHOTGUN_IMAGE;
	}
	

	@Override
	public void update(GameBoard gameBoard) {
		int newposY = getPositionY() - 1;
		if (newposY < 0) {
			gameBoard.removeObject(this);
			bestaat =false;
		} else if (gameBoard.getObject(getPositionX(), newposY) instanceof Enemy) {
			gameBoard.moveObject(this, getPositionX(), newposY);
			gameBoard.removeObject(gameBoard.getObject(getPositionX(), newposY));
			((SoZGame)gameBoard.getGame()).RemoveEnemy((Enemy) gameBoard.getObject(getPositionX(), newposY));
		} else {
			gameBoard.moveObject(this, getPositionX(), newposY);
		}

	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// TODO Auto-generated method stub

	}


	@Override
	public boolean Bestaat() {
		// TODO Auto-generated method stub
		return bestaat;
	}

}
