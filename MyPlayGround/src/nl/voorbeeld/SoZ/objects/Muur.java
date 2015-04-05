package nl.voorbeeld.SoZ.objects;

import android.util.Log;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;
import nl.voorbeeld.SoZ.SoZGame;

public class Muur extends GameObject {
	public static final String MUUR_IMAGE="muur";
	

	@Override
	public String getImageId() {
		return MUUR_IMAGE;
	}
	
	public void muurDamagedCheck(GameBoard gameBoard){
		int leven=(gameBoard.getGame()).savegame.getMuur(getPositionX());
		if (leven<0){
			gameBoard.removeObject(this);
		}
	}

	@Override
	public void onTouched(GameBoard gameBoard) {

	}
	
	public void muurDamaged(GameBoard gameBoard){
		int leven=(gameBoard.getGame()).savegame.getMuur(getPositionX());
		Log.i("muur before", ""+leven);
		leven = leven -7;
		(gameBoard.getGame()).savegame.setMuur(getPositionX(),leven);
		
		if (leven<0){
			gameBoard.removeObject(this);
		}
	}

}
