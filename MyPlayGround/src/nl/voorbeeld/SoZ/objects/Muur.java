package nl.voorbeeld.SoZ.objects;

import android.util.Log;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Muur extends GameObject {
	public static final String MUUR_IMAGE="muur";
	public static final String MUUR_IMAGE2="muur2";
	public static final String MUUR_IMAGE3="muur3";
	private String plaatje = MUUR_IMAGE;

	@Override
	public String getImageId() {
		return plaatje;
	}
	
	public void muurDamagedCheck(GameBoard gameBoard){
		int leven=(gameBoard.getGame()).savegame.getMuur(getPositionX());
		if (leven<0){
			gameBoard.removeObject(this);
		}else if(leven<15){
			plaatje = MUUR_IMAGE3;
		}else if (leven <=60){
			plaatje = MUUR_IMAGE2;
		}else{
			plaatje = MUUR_IMAGE;
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
		}else if(leven<15){
			plaatje = MUUR_IMAGE3;
		}else if (leven <=60){
			plaatje = MUUR_IMAGE2;
		}else{
			plaatje = MUUR_IMAGE;
		}
	}

}
