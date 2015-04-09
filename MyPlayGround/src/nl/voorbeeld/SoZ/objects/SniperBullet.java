package nl.voorbeeld.SoZ.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.voorbeeld.SoZ.SoZGame;

public class SniperBullet extends Projectile {
	private int enemyPen;
	
	public static final String SHOTGUN_IMAGE = "Shotgun";
	public boolean bestaat= true;

	public SniperBullet() {
		enemyPen=0;
	}
	public SniperBullet(int i) {
		enemyPen=i;
	}
	
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
			((SoZGame)gameBoard.getGame()).RemoveEnemy((Enemy) gameBoard.getObject(getPositionX(), newposY));
			gameBoard.removeObject(gameBoard.getObject(getPositionX(), newposY));
			gameBoard.moveObject(this, getPositionX(), newposY);
			enemyPen++;
			if (enemyPen>=4){
				gameBoard.removeObject(this);
				
			}
			if (((SoZGame)gameBoard.getGame()).getEnemiesToSpawn()<1&&((SoZGame)gameBoard.getGame()).getEnemiesAantal()<1){
				((SoZGame)gameBoard.getGame()).levelCompleted();
			}
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