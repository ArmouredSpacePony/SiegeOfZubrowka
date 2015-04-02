package nl.voorbeeld.SoZ.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;
import nl.voorbeeld.SoZ.SoZGame;

public class Muur extends GameObject {
	public static final String MUUR_IMAGE="muur";
	

	@Override
	public String getImageId() {
		return MUUR_IMAGE;
	}
	
	

	@Override
	public void onTouched(GameBoard gameBoard) {

	}
	
	public void muurDamaged(GameBoard gameBoard){
		int leven=((SoZGame)gameBoard.getGame()).getSavegame().getMuur(getPositionX());
		leven =leven -7;
		((SoZGame)gameBoard.getGame()).getSavegame().setMuur(getPositionX(),leven);
		
		if (leven<0){
			gameBoard.removeObject(this);
		}
	}

}
