package nl.voorbeeld.SoZ.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.voorbeeld.SoZ.SoZGame;

public class MachinegunBullet extends Projectile {
	
	

	@Override
	public String getImageId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GameBoard gameBoard) {
		int newposY = getPositionY()-1;
		if (newposY<0){
			gameBoard.removeObject(this);
		}else if (gameBoard.getObject(getPositionX(), newposY) instanceof Enemy){
			gameBoard.removeObject(this);
			gameBoard.removeObject(gameBoard.getObject(getPositionX(), newposY));
			((SoZGame)gameBoard.getGame()).RemoveEnemy((Enemy) gameBoard.getObject(getPositionX(), newposY));
		}else{
			gameBoard.moveObject(this, getPositionX(), newposY);
		}
		

	}

	@Override
	public void onTouched(GameBoard gameBoard) {
		// TODO Auto-generated method stub

	}

}
