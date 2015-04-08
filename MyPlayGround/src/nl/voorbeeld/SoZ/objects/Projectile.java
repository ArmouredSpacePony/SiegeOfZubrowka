package nl.voorbeeld.SoZ.objects;

import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;

public abstract class Projectile extends GameObject {

	@Override
	public abstract String getImageId();
	
	public abstract void update (GameBoard gameBoard);

	@Override
	public abstract void onTouched(GameBoard gameBoard);
	
	public abstract boolean Bestaat();

}