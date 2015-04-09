package nl.voorbeeld.SoZ.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.voorbeeld.SoZ.SoZGame;

public class MachinegunBullet extends Projectile {
	public static final String MACHINEGUN_IMAGE = "MachineGun";
	public boolean bestaat= true;

	/** Returns the ImageId of the image to show. */
	@Override
	public String getImageId() {
		return MACHINEGUN_IMAGE;
	}
	
	@Override
	public void update(GameBoard gameBoard) {
		int newposY = getPositionY()-1;
		if (newposY<0){
			gameBoard.removeObject(this);
			bestaat =false;
		}else if (gameBoard.getObject(getPositionX(), newposY) instanceof Enemy){
			((SoZGame)gameBoard.getGame()).RemoveEnemy((Enemy) gameBoard.getObject(getPositionX(), newposY));
			gameBoard.removeObject(this);
			gameBoard.removeObject(gameBoard.getObject(getPositionX(), newposY));
			bestaat =false;
			((SoZGame)gameBoard.getGame()).checkLevelComp();
		}else{
			gameBoard.moveObject(this, getPositionX(), newposY);
		}
		

	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// TODO Auto-generated method stub

	}
	
	public boolean Bestaat(){
		return bestaat;
	}

}
