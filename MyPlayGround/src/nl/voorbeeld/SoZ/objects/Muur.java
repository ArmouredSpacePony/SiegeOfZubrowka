package nl.voorbeeld.SoZ.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public class Muur extends GameObject {
	public static final String MUUR_IMAGE="muur";
	int leven;

	@Override
	public String getImageId() {
		return MUUR_IMAGE;
	}
	
	public Muur(){
		leven=100;
	}
	
	public Muur(int leven){
		this.leven=leven;
	}

	@Override
	public void onTouched(GameBoard gameBoard) {

	}
	
	public void muurDamaged(GameBoard gameBoard){
		leven =leven - 7;
		if (leven<0){
			gameBoard.removeObject(this);
		}
	}

}
