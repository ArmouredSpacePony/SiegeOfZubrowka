package nl.voorbeeld.coolgame.objects;



import java.util.Random;

import android.util.Log;
import nl.saxion.act.playground.model.GameBoard;
import nl.saxion.act.playground.model.GameObject;
import nl.voorbeeld.coolgame.CoolGame;
import nl.saxion.act.playground.model.Game;

public class Enemy extends GameObject implements Runnable {
	public static final String Enemy1_IMAGE = "civilian3";
	public static final String Enemy2_IMAGE = "civilian4";
	public static final String Enemy3_IMAGE = "civilian5";
	private String currentImage;
	
	/**
	 * Constructs an enemy.
	 */
	public Enemy() {
		super();
		Random r = new Random();
		int getal = r.nextInt(3)+2;
		currentImage=("Enemy"+(getal)+"_IMAGE");
	}
	
	  /** Returns the ImageId of the image to show. */
	
	@Override
	public String getImageId() {
		if(currentImage.equals("civilian3")){
			return Enemy1_IMAGE;}
		else if(currentImage.equals("civilian4")){
			return Enemy2_IMAGE;
		}else{
			return Enemy3_IMAGE;
		}
		
	}

	/** Called when the user touched this rock. */
	@Override
	public void onTouched(GameBoard gameBoard) {
		// placeholder until movement functions
		Log.d(CoolGame.TAG, "Touched Enemy");
		callMovement(gameBoard);
		
		//redBlock = !redBlock;
		gameBoard.updateView();
	}
	
	public void callMovement(GameBoard gameBoard){
		
			Log.d(CoolGame.TAG, "Moved Enemy");
			// player moves to the right
			int newPosX = getPositionX();
			int newPosY = getPositionY()+1;

			// If new position crosses border, call gameover
			if(newPosY > 16){
				((CoolGame)gameBoard.getGame()).gameOver();
			}
			// Move player to the new position and redraw the app
			gameBoard.moveObject(this, newPosX, newPosY);
			gameBoard.updateView();
		
	}

	@Override
	public void run() {
		callMovement(Game.gameBoard);
		
	}
}
